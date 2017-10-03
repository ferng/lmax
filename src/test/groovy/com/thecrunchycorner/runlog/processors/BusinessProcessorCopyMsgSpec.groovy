package com.thecrunchycorner.lmax.processors

import com.thecrunchycorner.lmax.msgstore.RingBufferStore
import com.thecrunchycorner.lmax.msgstore.Message
import com.thecrunchycorner.lmax.ringbufferaccess.PosControllerFactory
import com.thecrunchycorner.lmax.workflow.ProcessorId
import com.thecrunchycorner.lmax.services.SystemProperties

import spock.lang.Specification

class BusinessProcessorCopyMsgSpec extends Specification {

    def 'test'() {
        given:
        def random = new Random()
        def newValue = random.nextInt()
        def newMsg = new Message(newValue)

        def bufferSize = Integer.parseInt(SystemProperties.get("threshold.buffer.minimum.size"))

        def inLeadProcID = ProcessorId.IN_UNMARSHALER
        def inPosCtrlr = PosControllerFactory.getController()
        inPosCtrlr.setPos(inLeadProcID, 0);
        def inRingStore = new RingBufferStore(bufferSize)

        def outLeadProcID = ProcessorId.OUT_Q_SENDER
        def outPosCtrlr = PosControllerFactory.getController()
        outPosCtrlr.setPos(outLeadProcID, 10);
        def outRingStore = new RingBufferStore(bufferSize)

        def proc = new RingBusinessProcessor(inRingStore,outRingStore)

        when:
        inRingStore.set(0, newMsg)
        inPosCtrlr.incrPos(inLeadProcID)

        proc.getAndProcessMsg()

        then:
        ((Message) outRingStore.get(0)).getPayload() == newMsg.getPayload()
    }



}
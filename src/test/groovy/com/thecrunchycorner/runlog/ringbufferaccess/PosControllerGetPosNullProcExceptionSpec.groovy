package com.thecrunchycorner.runlog.ringbufferaccess

import com.thecrunchycorner.runlog.ringbufferaccess.enums.ProcessorID

import spock.lang.Specification

class PosControllerGetPosNullProcExceptionSpec extends Specification {

    def 'test'() {
        given:
        def PosController ctrl = PosControllerFactory.getController()
        ctrl.setPos ProcessorID.BUSINESS_PROCESSOR, 0

        when:
        ctrl.getPos(null)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == "Arguments cannot be null"
    }

}

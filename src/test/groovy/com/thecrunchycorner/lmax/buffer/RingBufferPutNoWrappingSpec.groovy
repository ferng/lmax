package com.thecrunchycorner.lmax.buffer

import com.thecrunchycorner.lmax.services.SystemProperties
import spock.lang.Specification

class RingBufferPutNoWrappingSpec extends Specification {

    def 'test'() {
        given:
        def thresholdSize = SystemProperties.getThresholdBufferSize()
        def buffer = new RingBuffer<>(1, thresholdSize)
        def testInt1 = -1


        when:
        buffer.set(0, new Message(testInt1))


        then:
        buffer.get(0).getPayload() == testInt1
    }

}

package com.thecrunchycorner.lmax.processorproperties

import com.thecrunchycorner.lmax.buffer.BufferReader
import com.thecrunchycorner.lmax.testHelpers.IdGenerator
import spock.lang.Specification

import java.util.function.UnaryOperator

class ProcPropertiesBuilderNegativeProcIdSpec extends Specification {
    def reader = Mock(BufferReader.class)
    def process = Mock(UnaryOperator)

    def test() {
        when:
        def props = new ProcProperties.Builder()
                .setId(IdGenerator.id)
                .setProcId(-1)
                .setPriority(1)
                .setReader(reader)
                .setInitialHead(12)
                .setProcess(process)
                .build()

        then:
        IllegalArgumentException ex1 = thrown()
        ex1.message == "Processor ID cannot be negative"
    }
}
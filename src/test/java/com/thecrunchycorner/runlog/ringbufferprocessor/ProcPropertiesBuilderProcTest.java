package com.thecrunchycorner.runlog.ringbufferprocessor;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.thecrunchycorner.runlog.ringbuffer.RingBuffer;
import com.thecrunchycorner.runlog.ringbuffer.enums.BufferType;
import com.thecrunchycorner.runlog.ringbufferaccess.enums.ProcessorType;
import com.thecrunchycorner.runlog.services.SystemProperties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcPropertiesBuilderProcTest {

    RingBuffer<Integer> buffer;
    ProcProperties procProps;
    int initialHead = 20;

    @Before
    public void setup() {
        buffer = new RingBuffer(Integer.parseInt(SystemProperties.get("threshold.buffer.minimum.size")), BufferType.INPUT);

        procProps = new ProcPropertiesBuilder()
                .setBuffer(buffer)
                .setProcessor(ProcessorType.BUSINESS_PROCESSOR)
                .setLeadProc(ProcessorType.INPUT_PROCESSOR)
                .setInitialHead(initialHead)
                .createProcProperties();
    }


    @After
    public void tearDOWN() {

    }


    @Test
    public void Test() {
        assertThat(procProps.getProc(), is(ProcessorType.BUSINESS_PROCESSOR));
    }

}
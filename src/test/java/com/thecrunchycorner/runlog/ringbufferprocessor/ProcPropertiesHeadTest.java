package com.thecrunchycorner.runlog.ringbufferprocessor;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.thecrunchycorner.runlog.msgstore.RingBufferStore;
import com.thecrunchycorner.runlog.processors.ProcessorWorkflow;
import com.thecrunchycorner.runlog.ringbufferaccess.enums.ProcessorID;
import com.thecrunchycorner.runlog.services.SystemProperties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProcPropertiesHeadTest {

    private RingBufferStore<Integer> buffer;
    private ProcProperties procProps;
    private int initialHead = 20;

    @Before
    public void setup() {
        buffer = new RingBufferStore(Integer.parseInt(SystemProperties.get("threshold.buffer.minimum.size")));
        ProcessorID trailProc = ProcessorID.IN_BUSINESS_PROCESSOR;
        ProcessorID leadProc = ProcessorWorkflow.getLeadProc(trailProc);

        procProps = new ProcProperties(buffer, trailProc, leadProc, initialHead);
    }


    @After
    public void tearDOWN() {

    }


    @Test
    public void Test() {
        assertThat(procProps.getInitialHead(), is(initialHead));
    }

}
package com.thecrunchycorner.lmax.processorproperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.thecrunchycorner.lmax.msgstore.RingBufferStore;
import com.thecrunchycorner.lmax.services.SystemProperties;
import com.thecrunchycorner.lmax.storehandler.BufferHandler;
import com.thecrunchycorner.lmax.storehandler.BufferReaderWriter;
import com.thecrunchycorner.lmax.workflow.ProcessorId;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProcPropertiesBuilderNullTest {
    private ProcProperties procProps;
    private int initialBufferSize = SystemProperties.getThresholdBufferSize();
    private int initialHead = SystemProperties.getThresholdBufferSize() - 2;
    private final RingBufferStore<Integer> buffer = new RingBufferStore<>(initialBufferSize);
    private final BufferReaderWriter<Object> handler = new BufferReaderWriter<>();

    @Rule public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        thrown.expect(IllegalArgumentException.class);
    }


    @Test
    public void testNullProcId() {
        thrown.expectMessage("Argument cannot be null");
        procProps = new ProcProperties.Builder()
                .setProcessorId(null)
                .setBuffer(buffer)
                .setHandler(handler)
                .setInitialHead(initialHead)
                .createProcProperties();
    }


    @Test
    public void TestNullBuffer() {
        thrown.expectMessage("Argument cannot be null");
        procProps = new ProcProperties.Builder()
                .setProcessorId(ProcessorId.BUSINESS_PROCESSOR)
                .setBuffer(null)
                .setHandler(handler)
                .setInitialHead(initialHead)
                .createProcProperties();
    }


    @Test
    public void TestNullHandler() {
        thrown.expectMessage("Argument cannot be null");
        procProps = new ProcProperties.Builder()
                .setProcessorId(ProcessorId.BUSINESS_PROCESSOR)
                .setBuffer(buffer)
                .setHandler(null)
                .setInitialHead(initialHead)
                .createProcProperties();
    }


    @Test
    public void TestNegativeInitialHead() {
        thrown.expectMessage("Argument cannot be < 0");
        procProps = new ProcProperties.Builder()
                .setProcessorId(ProcessorId.BUSINESS_PROCESSOR)
                .setBuffer(buffer)
                .setHandler(handler)
                .setInitialHead(-1)
                .createProcProperties();
    }
}

package com.thecrunchycorner.lmax.msgstore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.thecrunchycorner.lmax.services.SystemProperties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RingBufferPutWrappingTest {

    private RingBufferStore<Integer> buffer;
    //if the test crashes because there is no int then it's a failure I'm happy to highlight
    private int bufferSize = SystemProperties.getAsInt("threshold.buffer.minimum.size").getAsInt();


    @Before
    public void setup() {
        buffer = new RingBufferStore<>(bufferSize);
    }


    @After
    public void tearDOWN() {
    }


    @Test
    public void Test() {
        Integer testInt1 = 23;

        for (int i = 0; i < bufferSize; i++) {
            buffer.set(i, i);
        }

        buffer.set(bufferSize, testInt1);

        assertThat(buffer.get(bufferSize), is(testInt1));
        assertThat(buffer.get(0), is(testInt1));

    }
}

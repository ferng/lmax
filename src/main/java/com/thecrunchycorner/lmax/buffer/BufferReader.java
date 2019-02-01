package com.thecrunchycorner.lmax.buffer;

import com.thecrunchycorner.lmax.handlers.Reader;
import java.util.Objects;

/**
 * Provides client classes with the means to read from a buffer. Each reader is uniquely owned by
 * the processor using it.
 */
public class BufferReader implements Reader {
    private RingBuffer buffer;


    /**
     * Each BufferReader is unique to a processor.
     *
     * @param buffer the buffer we will be reading from
     */
    public BufferReader(RingBuffer buffer) {
        Objects.requireNonNull(buffer, "Missing buffer");
        this.buffer = buffer;
    }

    @Override
    public int getBufferId() {
        return buffer.getId();
    }

    /**
     * Retrieves an object from its buffer.
     *
     * @param pos the position on the buffer to read from. It is up to the client to ensure the
     * position being read is not beyond the leading processor's position thereby reading invalid
     * data.
     * @throws IllegalArgumentException if the position is negative or the message is null
     */
    @Override
    public Message read(int pos) {
        return buffer.get(pos);
    }
}

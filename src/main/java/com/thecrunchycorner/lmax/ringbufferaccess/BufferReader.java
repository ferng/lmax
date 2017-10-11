//package com.thecrunchycorner.lmax.ringbufferaccess;
//
//import com.thecrunchycorner.lmax.msgstore.RingBufferStore;
//import com.thecrunchycorner.lmax.workflow.ProcessorId;
//import com.thecrunchycorner.lmax.processorproperties.ProcProperties;
//import java.util.Optional;
//
///**
// * Provides client classes with the means to read from a buffer.
// * Each reader is owned by the processor using it and keeps track of:
// * 1) Which buffer it's reading from
// * 2) where it has read to
// * 3) where it can read up to
// */
//public class BufferReader<E> implements BufferAccess {
//    private PosController posController = PosControllerFactory.getController();
//
//    private RingBufferStore buffer;
//    private ProcessorId processor;
//    private ProcessorId myLead;
//    private int head;
//
//
//    /**
//     * Each BufferReader is unique to a processor (although a processor can
//     * have multiple readers / writers)
//     *
//     * @param props details for this reader:
//     * 1) Which buffer is to be accessed
//     * 2) ID for the processor that owns us and
//     * 3) ID for the processor we follow
//     * 4) Initial head position, should be buffer size for lead processor,
//     * 0 for all others
//     */
//    public BufferReader(ProcProperties props) {
//        buffer = props.getBuffer();
//        processor = props.getProc();
//        myLead = props.getLeadProc();
//        head = props.getInitialHead();
//
//        posController.setPos(processor, 0);
//    }
//
//
//    /**
//     * Retrieves an object from its buffer or null if none, it is up to the
//     * client to wait an appropriate amount of time before retrying.
//     */
//    public final Object read() {
//        int pos = posController.getPos(processor);
//
//        if (pos == head) {
//            head = posController.getPos(myLead);
//            if (pos == head) {
//                return null;
//            }
//        }
//
//        posController.incrPos(processor);
//        return buffer.get(pos);
//    }
//
//
//    /**
//     * Retrieves an object from its buffer if any within a specified time (ms)
//     * or null upon time out.
//     *
//     * @param timeout millisesonds to wait until we give up
//     * @throws InterruptedException if interrupted while waiting
//     */
//    public final Object read(long timeout) throws InterruptedException {
//        int pos = posController.getPos(processor);
//
//        if (pos == head) {
//            Thread.sleep(timeout);
//            head = posController.getPos(myLead);
//            if (pos == head) {
//                return null;
//            }
//        }
//
//        posController.incrPos(processor);
//        return buffer.get(pos);
//    }
//
//
//    /**
//     * Retrieves an object from its buffer if any within a number of attempts,
//     * waiting the specified time (ms) between each attempt or null upon retry exhaustion.
//     *
//     * @param attempts how many times to retry the read before giving up
//     * @param timeout millisesonds to wait until we give up
//     */
//    public final Object read(long timeout, long attempts) throws InterruptedException {
//        int pos = posController.getPos(processor);
//        long attemptsLeft = attempts;
//
//        while (attemptsLeft > 0) {
//            if (pos == head) {
//                Thread.sleep(timeout);
//                head = posController.getPos(myLead);
//                attemptsLeft--;
//            } else {
//                posController.incrPos(processor);
//                return buffer.get(pos);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Optional process() {
//        return null;
//    }
//}

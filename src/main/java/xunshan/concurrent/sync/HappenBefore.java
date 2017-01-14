package xunshan.concurrent.sync;

/**
 * Synchronization is built around an internal entity known as the intrinsic lock or monitor lock
 * When a thread releases an intrinsic lock, a happens-before relationship
 * is established between that action and any subsequent acquisition of the same lock.
 */
public class HappenBefore {
    // one specific statement are visible to another specific statement.
    // synchronized
    // check paper Visibility and Happen-before ordering by Micheal Hicks
}

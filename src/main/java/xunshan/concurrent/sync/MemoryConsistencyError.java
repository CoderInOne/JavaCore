package xunshan.concurrent.sync;

/**
 * different threads have inconsistent views of what should be the same data.
 * happens-before relationship:
 * guarantee that memory writes by one specific statement are visible to another specific statement.
 * See Interference.Shared.SharedMethod
 * See also: http://docs.oracle.com/javase/tutorial/essential/concurrency/syncrgb.html
 */
public class MemoryConsistencyError {
}

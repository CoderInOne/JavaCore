package xunshan.concurrent.hack;

/**
 * thread synchronization byte code
 *
 * every thread has a stack
 *
 *         t1                  t2
 *   | local_var1 |      | local_var1 |
 *   | local_var2 |      | local_var2 |
 *   | local_var3 |      | local_var3 |
 *   | ...        |      | ...        |
 *
 * where share data?
 *
 * 1. All Share data in object, including primitive type,
 * cause it's impossible for primitive variables standing alone, which must
 * be in object. And Object always in HEAP.
 * A heap for a JVM, so how big and how to split heap in JVM?
 *
 * 2. Method area store class variable[static], so they can be shared by threads.
 *
 * Lock, lock not in stack, where?
 * Class lock impl by class obj
 */
public class PackageInfo {
}

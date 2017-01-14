package xunshan.concurrent.hack;

/**
 * synchronized
 */
public class KitchenSync {
    private int[] intArray = new int[10];

    /*
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter <<<<<<<<<---
         4: aload_1
         5: monitorexit  <<<<<<<<<<---
         6: goto          14
         9: astore_2
        10: aload_1
        11: monitorexit <<<<<<<<<--
        12: aload_2
        13: athrow
        14: return
    Exception table:
    from    to  target type
             4     6     9   any
             9    12     9   any*/

    void reverseOrder() {
        synchronized (KitchenSync.class) {
//            int halfWay = intArray.length / 2;
//            for (int i = 0; i < halfWay; ++i) {
//                int upperIndex = intArray.length - 1 - i;
//                int save = intArray[upperIndex];
//                intArray[upperIndex] = intArray[i];
//                intArray[i] = save;
//            }
        }
    }

     /*
     synchronized void reverseOrder0();
    descriptor: ()V
    flags: ACC_SYNCHRONIZED <<<<<-----
    Code:
      stack=1, locals=2, args_size=1
         0: aload_0
         1: getfield      #2                  // Field intArray:[I
         4: astore_1
         5: return
      LineNumberTable:
        line 51: 0
        line 52: 5
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       6     0  this   Lxunshan/concurrent/hack/KitchenSync;
            5       1     1   tmp   [I


         */

    synchronized void reverseOrder0() {
//            int halfWay = intArray.length / 2;
//            for (int i = 0; i < halfWay; ++i) {
//                int upperIndex = intArray.length - 1 - i;
//                int save = intArray[upperIndex];
//                intArray[upperIndex] = intArray[i];
//                intArray[i] = save;
//            }
        int[] tmp = this.intArray;
    }

    public static void main(String[] args) {
        new KitchenSync().reverseOrder();
    }
}

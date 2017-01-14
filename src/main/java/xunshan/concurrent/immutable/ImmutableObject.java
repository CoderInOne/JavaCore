package xunshan.concurrent.immutable;

/**
 * Immutable Object, like String
     public final class String ... {
             // The value is used for character storage.
             private final char value[];
     }

 See: http://docs.oracle.com/javase/tutorial/essential/concurrency/syncrgb.html
 See: http://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
 */
// 0. class is final, not allow to be override
public final class ImmutableObject {
    // 1. all field private and final
    final private int id;
    final private String name;

    public ImmutableObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    // 2. no setter
    // 3. only one field refer to an obj
    public ImmutableObject modifiedName(String name) {
        return new ImmutableObject(this.id, name);
    }
}

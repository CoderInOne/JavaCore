package xunshan.di;

/**
 * DI App as application code.
 */
public class App {
    @Inject
    public Shape shape;

    /**
     * inject dependency explicitly
     * @param shape object to be injected
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Thinking about App entry point
     */
    public void main() {
        this.run(this);

        this.shape.draw();
    }

    /**
     * start app, this part job can be done by annotations
     *
     * @param app here Object and Class both ok
     *            is it a great idea?
     */
    private void run(Object app) {
        // 1. register class to be injected into context
        ClassInjectList list = new ClassInjectList();
        list.addClass(Circle.class);
        // 2. process annotation, find field should be injected
        // 3. find object in context, set field. and done
        Binder.bind(app, list);
    }
}

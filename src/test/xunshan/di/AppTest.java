package xunshan.di;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testShapeShouldNullBeforeDi() {
        App app = new App();
        assertNull(app.shape);
    }

    @Test
    public void testShapeShouldNotBeNullAfterDi() {
        App app = new App();
        app.setShape(new Circle());
        assertNotNull(app.shape);
    }

    @Test
    public void testShapeShouldNotBeNullAfterAnnoDi() {
        App app = new App();
        app.main();
        assertNotNull(app.shape);
    }
}
import bridges.base.Color;
import org.junit.Assert;
import org.junit.Test;

public class MarkTest {
    private static final Color B = new Color("black");
    private static final Color W = new Color("white");
    private static final Color U = new Color("blue");

    @Test
    public void isColorTest() {
        Mark m1 = new Point(0, 0, W);
        Mark m2 = new HorizontalLine(1, 1, 2, U);
        Assert.assertTrue(m1.isColor(W));
        Assert.assertFalse(m1.isColor(B));
        Assert.assertFalse(m1.isColor(U));
        Assert.assertFalse(m2.isColor(W));
        Assert.assertFalse(m2.isColor(B));
        Assert.assertTrue(m2.isColor(U));
    }
}
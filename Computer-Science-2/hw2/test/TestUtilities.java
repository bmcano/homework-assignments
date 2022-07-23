import bridges.base.Color;
import bridges.base.ColorGrid;
import org.junit.Assert;

public class TestUtilities {
    // A helpful method for checking if a ColorGrid contains the
    // expected pixels
    public static void checkCG(Color[][] expected, ColorGrid actual) {
        // is the height the same?
        Assert.assertEquals(expected.length, actual.getHeight());

        // is the width the same?
        Assert.assertEquals(expected[0].length, actual.getWidth());

        // are the colors of each pixel the same?
        for (int y = 0; y < expected.length; y++) {
            for (int x = 0; x < expected[0].length; x++) {
                Assert.assertEquals("At x=" + x + " y=" + y, expected[y][x], actual.get(y, x));
            }
        }
    }
}
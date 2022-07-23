import bridges.base.Color;
import bridges.base.ColorGrid;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VerticalLineTest {

    private static final Color B = new Color("black");
    private static final Color W = new Color("white");
    private static final Color U = new Color("blue");

    @Test
    public void testShortLine() {
        ColorGrid cg = new ColorGrid(3, 3);
        VerticalLine p = new VerticalLine(1, 1, 2, U);
        p.draw(cg);
        TestUtilities.checkCG(new Color[][] {
                {B, B, B},
                {B, B, U},
                {B, B, B}}, cg);
    }

    @Test
    public void testLongLine() {
        ColorGrid cg = new ColorGrid(3, 3);
        VerticalLine p = new VerticalLine(0, 1, 2, U);
        p.draw(cg);
        TestUtilities.checkCG(new Color[][] {
                {B, B, U},
                {B, B, U},
                {B, B, B}}, cg);
    }

    @Test
    public void testMultipleLines() {
        ColorGrid cg = new ColorGrid(4, 4);
        VerticalLine p = new VerticalLine(1, 3, 2, U);
        p.draw(cg);
        VerticalLine q = new VerticalLine(2, 3, 0, W);
        q.draw(cg);
        TestUtilities.checkCG(new Color[][] {
                {B, B, B, B},
                {B, B, U, B},
                {W, B, U, B},
                {W, B, U, B}}, cg);
    }
}
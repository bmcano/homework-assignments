import bridges.base.Color;
import bridges.base.ColorGrid;
import org.junit.Test;

public class HorizonalLineTest {

    private static final Color B = new Color("black");
    private static final Color W = new Color("white");
    private static final Color U = new Color("blue");

    @Test
    public void testShortLine() {
        ColorGrid cg = new ColorGrid(3, 3);
        HorizontalLine p = new HorizontalLine(1, 1, 2, U);
        p.draw(cg);
        TestUtilities.checkCG(new Color[][] {
                {B, B, B},
                {B, B, B},
                {B, U, B}}, cg);
    }

    @Test
    public void testLongLine() {
        ColorGrid cg = new ColorGrid(3, 3);
        HorizontalLine p = new HorizontalLine(0, 1, 2, U);
        p.draw(cg);
        TestUtilities.checkCG(new Color[][] {
                {B, B, B},
                {B, B, B},
                {U, U, B}}, cg);
    }

    @Test
    public void testMultipleLines() {
        ColorGrid cg = new ColorGrid(4, 4);
        HorizontalLine p = new HorizontalLine(1, 3, 2, U);
        p.draw(cg);
        HorizontalLine q = new HorizontalLine(2, 3, 0, W);
        q.draw(cg);
        TestUtilities.checkCG(new Color[][] {
                {B, B, W, W},
                {B, B, B, B},
                {B, U, U, U},
                {B, B, B, B}}, cg);
    }
}
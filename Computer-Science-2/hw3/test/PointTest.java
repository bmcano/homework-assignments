import bridges.base.Color;
import bridges.base.ColorGrid;
import org.junit.Test;

public class PointTest {

    private static final Color B = new Color("black");
    private static final Color W = new Color("white");
    private static final Color U = new Color("blue");

    @Test
    public void testOnePoint() {
        ColorGrid cg = new ColorGrid(3, 3);
        Point p = new Point(1, 2, W);
        p.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {B, B, B},
                {B, B, B},
                {B, W, B}}, cg);
    }

    @Test
    public void testTwoPoints() {
        ColorGrid cg = new ColorGrid(3, 3);
        Point p = new Point(1, 2, U);
        p.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {B, B, B},
                {B, B, B},
                {B, U, B}}, cg);
        Point q = new Point(2, 0, W);
        q.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {B, B, W},
                {B, B, B},
                {B, U, B}}, cg);
    }

    @Test
    public void testMultiplePoints() {
        ColorGrid cg = new ColorGrid(3, 3);
        for (int x = 0; x<3; x++) {
            for (int y=0; y<3; y++) {
                if (x!=y) {
                    Point p = new Point(x, y, U);
                    p.draw(cg);
                } else {
                    Point p = new Point(x, y, W);
                    p.draw(cg);
                }
            }
        }
        TestUtilities.checkCG(new Color[][]{
                {W, U, U},
                {U, W, U},
                {U, U, W}}, cg);
    }
}
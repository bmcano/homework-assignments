import bridges.base.Color;
import bridges.base.ColorGrid;
import org.junit.Test;

public class SceneTest {
    private static final Color Y = new Color("yellow");
    private static final Color B = new Color("black");
    private static final Color G = new Color("green");
    private static final Color C = new Color("cyan");

    @Test
    public void backgroundTest() {
        Scene s = new Scene(2, Y);
        ColorGrid cg = new ColorGrid(3, 3);
        s.draw(cg);

        TestUtilities.checkCG(new Color[][]{
                {Y, Y, Y},
                {Y, Y, Y},
                {Y, Y, Y}}, cg);
    }

    @Test
    public void multipleScenesTest() {
        Scene s = new Scene(2, Y);
        Scene s2 = new Scene(3, C);
        ColorGrid cg = new ColorGrid(3, 3);
        ColorGrid cg2 = new ColorGrid(2, 3);
        s.draw(cg);
        s2.draw(cg2);

        TestUtilities.checkCG(new Color[][]{
                {Y, Y, Y},
                {Y, Y, Y},
                {Y, Y, Y}}, cg);
        TestUtilities.checkCG(new Color[][]{
                {C, C, C},
                {C, C, C}}, cg2);
    }

    @Test
    public void pointTest() {
        Scene s = new Scene(2, B);
        s.addMark(new Point(1, 2, Y));

        ColorGrid cg = new ColorGrid(3, 3);
        s.draw(cg);

        TestUtilities.checkCG(new Color[][]{
                {B, B, B},
                {B, B, B},
                {B, Y, B}}, cg);
    }

    @Test
    public void pointsTest() {
        Scene s = new Scene(2, B);
        s.addMark(new Point(1, 2, Y));
        s.addMark(new Point(0, 2, G));

        ColorGrid cg = new ColorGrid(3, 3);
        s.draw(cg);

        TestUtilities.checkCG(new Color[][]{
                {B, B, B},
                {B, B, B},
                {G, Y, B}}, cg);
    }

    @Test
    public void multipleDrawsTest() {
        Scene s = new Scene(4, G);
        s.addMark(new Point(0, 0, Y));
        ColorGrid cg = new ColorGrid(1, 3);
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{{Y, G, G}}, cg);
        s.addMark(new Point(1, 0, C));
        s.addMark(new Point(0, 0, B));
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{{B, C, G}}, cg);
    }


    @Test
    public void multipleScenesPointsTest() {
        Scene s = new Scene(2, B);
        Scene s2 = new Scene(1, C);
        s.addMark(new Point(1, 2, Y));
        s2.addMark(new Point(0, 2, G));

        ColorGrid cg = new ColorGrid(3, 3);
        s.draw(cg);
        ColorGrid cg2 = new ColorGrid(3, 1);
        s2.draw(cg2);

        TestUtilities.checkCG(new Color[][]{
                {B, B, B},
                {B, B, B},
                {B, Y, B}}, cg);
        TestUtilities.checkCG(new Color[][]{
                {C},
                {C},
                {G}}, cg2);
    }

    @Test
    public void pointAndLineTest() {
        Scene s = new Scene(2, B);
        s.addMark(new Point(1, 2, Y));
        s.addMark(new HorizontalLine(0, 1, 0, G));

        ColorGrid cg = new ColorGrid(3, 3);
        s.draw(cg);

        TestUtilities.checkCG(new Color[][]{
                {G, G, B},
                {B, B, B},
                {B, Y, B}}, cg);
    }

    // This test passes if the code throws the expected
    // exception. It fails if the code finishes with no exception.
    @Test(expected = IllegalStateException.class)
    public void exceededCapacityTest() {
        Scene s = new Scene(3, Y);
        s.addMark(new Point(0, 0, G));
        s.addMark(new Point(0, 0, G));
        s.addMark(new Point(0, 0, G));
        s.addMark(new Point(0, 0, G));
    }

    @Test
    public void deleteFrontTest() {
        Scene s = new Scene(3, Y);
        s.addMark(new Point(0, 0, B));
        s.addMark(new Point(1, 1, G));
        s.addMark(new Point(2, 2, C));
        s.deleteMark(0);
        ColorGrid cg = new ColorGrid(3,3);
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {Y, Y, Y},
                {Y, G, Y},
                {Y, Y, C}}, cg);
    }

    @Test
    public void deleteMidTest() {
        Scene s = new Scene(5, Y);
        s.addMark(new Point(0, 0, B));
        s.addMark(new Point(1, 1, G));
        s.addMark(new Point(2, 2, C));
        s.addMark(new Point(0, 1, B));
        s.deleteMark(1);
        ColorGrid cg = new ColorGrid(3,3);
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {B, Y, Y},
                {B, Y, Y},
                {Y, Y, C}}, cg);
    }

    @Test
    public void addAfterDeleteTest() {
        Scene s = new Scene(4, Y);
        s.addMark(new Point(0, 0, B));
        s.addMark(new Point(1, 1, G));
        s.addMark(new Point(2, 2, C));
        s.addMark(new Point(0, 1, B));
        s.deleteMark(1);
        s.addMark(new Point(2, 1, B));
        ColorGrid cg = new ColorGrid(3,3);
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {B, Y, Y},
                {B, Y, B},
                {Y, Y, C}}, cg);
    }

    @Test
    public void deleteEndTest() {
        Scene s = new Scene(5, Y);
        s.addMark(new Point(0, 0, B));
        s.addMark(new Point(1, 1, G));
        s.addMark(new Point(2, 2, C));
        s.addMark(new Point(0, 1, B));
        s.deleteMark(3);
        ColorGrid cg = new ColorGrid(3,3);
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {B, Y, Y},
                {Y, G, Y},
                {Y, Y, C}}, cg);
    }

    @Test
    public void occlusionTest() {
        Scene s = new Scene(5, G);
        s.addMark(new HorizontalLine(0, 2, 0, Y));
        s.addMark(new VerticalLine(0, 2, 1, B));
        s.addMark(new VerticalLine(0, 1, 2, B));
        ColorGrid cg = new ColorGrid(3,3);
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {Y, B, B},
                {G, B, B},
                {G, B, G}}, cg);
    }

    @Test
    public void deleteByColorTest() {
        Scene s = new Scene(5, G);
        s.addMark(new VerticalLine(0, 2, 1, B));
        s.addMark(new HorizontalLine(0, 2, 0, Y));
        s.addMark(new VerticalLine(0, 1, 2, B));
        ColorGrid cg = new ColorGrid(3,3);
        s.deleteMarksByColor(B);
        s.draw(cg);
        TestUtilities.checkCG(new Color[][]{
                {Y, Y, Y},
                {G, G, G},
                {G, G, G}}, cg);
    }
}
import bridges.base.Color;
import bridges.base.ColorGrid;

@SuppressWarnings("ALL")
public class Point extends Mark {
    // instance variables for constructor
    private int x;
    private int y;

    public Point(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.color = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        // row, column, color
        cg.set(this.y, this.x, this.color);
    }
}

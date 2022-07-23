import bridges.base.Color;
import bridges.base.ColorGrid;

@SuppressWarnings("ALL")
public class HorizontalLine extends Mark {
    // instance variables for constructor
    private int start;
    private int end;
    private int y;

    public HorizontalLine(int start, int end, int y, Color c) {
        this.start = start;
        this.end = end;
        this.y = y;
        this.color = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        for(int i = this.start; i<=this.end; i++) {
            cg.set(this.y, i, this.color);
        }
    }
}

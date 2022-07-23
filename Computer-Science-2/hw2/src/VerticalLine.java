import bridges.base.Color;
import bridges.base.ColorGrid;

@SuppressWarnings("ALL")
public class VerticalLine extends Mark {
    private int start;
    private int end;
    private int x;

    public VerticalLine(int start, int end, int x, Color c) {
        this.start = start;
        this.end = end;
        this.x = x;
        this.color = c;
    }

    @Override
    public void draw(ColorGrid cg) {
        for(int i = this.start; i<=this.end; i++) {
            cg.set(i, this.x, this.color);
        }
    }
}

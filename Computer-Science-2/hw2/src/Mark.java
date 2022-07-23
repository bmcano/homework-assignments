import bridges.base.ColorGrid;
import bridges.base.Color;

public abstract class Mark {
    // this is protected (rather than private) so that
    // subclasses of Mark can access it
    protected Color color;

    // Returns true if and only if the given Color
    // is the same as this Mark's Color. Two Colors
    // are considered the same if their Red, Green,
    // Blue, and Alpha components are equal.
    // c: the Color to check against
    public boolean isColor(Color c) {
        // split into multiple statements for better readability
        if(color.getRed() != c.getRed()) return false;
        if(color.getGreen() != c.getGreen()) return false;
        if(color.getBlue() != c.getBlue()) return false;
        return color.getAlpha() == c.getAlpha();
    }

    // Draws this Mark onto the given ColorGrid
    // cg: the ColorGrid to draw onto
    public abstract void draw(ColorGrid cg);
}

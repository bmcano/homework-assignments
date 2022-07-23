import bridges.base.Color;
import bridges.base.ColorGrid;

@SuppressWarnings("ALL")
public class DiagonalLine extends Mark {
    // instance variables for constructor
    private int x0;
    private int x1;
    private int y0;
    private int y1;
    private int[][] coordinates;

    public DiagonalLine(int x0, int y0, int x1, int y1, Color color) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.color = color;
        this.coordinates = bresenham(x0, y0, x1, y1);
    }

    // this code is derived from:
    // https://www.geeksforgeeks.org/bresenhams-line-generation-algorithm/
    // slight modifications were made to properly add the coordinates to an array
    private int[][] bresenham(int x0, int y0, int x1, int y1) {
        // array for storing the coordinates
        int[][] cords = new int[(x1-x0)+1][2];
        int count = 0;

        int m_new = 2 * (y1 - y0);
        int slope_error_new = m_new - (x1 - x0);

        for (int x = x0, y = y0; x <= x1; x++) {
            // Add slope to increment angle formed
            slope_error_new += m_new;

            // Slope error reached limit, time to
            // increment y and update slope error.
            if (slope_error_new >= 0) {
                y++;
                slope_error_new -= 2 * (x1 - x0);
            }

            cords[count][0] = x;
            cords[count][1] = y;
            count++;
        }
        return cords;
    }

    @Override
    public void draw(ColorGrid cg) {
        for (int[] coordinate : coordinates) {
            cg.set(coordinate[1], coordinate[0], this.color);
        }
    }
}

import bridges.base.Color;
import bridges.base.ColorGrid;

@SuppressWarnings("ALL")
public class Circle extends Mark {
    // instance variables for constructor
    private int radius;
    private int xCenter;
    private int yCenter;
    private int[][] coordinates;

    public Circle(int radius, int xcenter, int ycenter, Color color) {
        this.radius = radius;
        this.xCenter = xcenter;
        this.yCenter = ycenter;
        this.color = color;
        this.coordinates = midPointCircleDraw(radius, xcenter, ycenter);
    }

    // this is a helper function for midPointCircleDraw()
    // this will calculate the number of points that will be needed
    // to actually draw the circle, this removes the need of a random
    // integer to be used for the coordinates array
    private int numPoints(int radius) {
        // by default a radius of 0 is a single dot
        if(radius == 0) return 1;

        int num = radius;
        int scaling = 0;
        int size;

        // loops till num is between 1 and 5
        while(num > 5) {
            num -= 5;
            scaling += 1;
        }

        // there is a pattern that occurs for every set of 5
        // these calculations were made by plotting to Desmos the
        // radius and the number of points to find the relationship
        if(num > 0 && num <= 3) {
            size = ((num*4) + 4)+(scaling*28);
        } else {
            size = (((num-4)*4)+24)+((scaling*28));
        }

        return size;
    }

    // this code derived from
    // https://www.geeksforgeeks.org/mid-point-circle-drawing-algorithm/
    // slight modifications were made to properly add coordinates to the array
    private int[][] midPointCircleDraw(int radius, int x_centre, int y_centre) {
        // get the size of the array
        int size = numPoints(radius);
        int[][] cords = new int[size][2];
        int x = radius, y = 0;
        int count = 0;

        cords[count][0] = x + x_centre;
        cords[count][1] = y + y_centre;
        count++;

        // When radius is zero only a single
        // point will be printed
        if (radius > 0) {
            cords[count][0] = x + x_centre;
            cords[count][1] = -y + y_centre;
            count++;

            cords[count][0] = y + x_centre;
            cords[count][1] = x + y_centre;
            count++;

            cords[count][0] = -y + x_centre;
            cords[count][1] = x + y_centre;
            count++;
        }

        // Initialising the value of P
        int P = 1 - radius;
        while (x > y) {
            y++;
            // Mid-point is inside or on the perimeter
            if (P <= 0) {
                P = P + 2 * y + 1;
            }
            // Mid-point is outside the perimeter
            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }

            // All the perimeter points have already
            // been printed
            if (x < y) break;

            cords[count][0] = x + x_centre;
            cords[count][1] = y + y_centre;
            count++;

            cords[count][0] = -x + x_centre;
            cords[count][1] = y + y_centre;
            count++;

            cords[count][0] = x + x_centre;
            cords[count][1] = -y + y_centre;
            count++;

            cords[count][0] = -x + x_centre;
            cords[count][1] = -y + y_centre;
            count++;

            // If the generated point is on the
            // line x = y then the perimeter points
            // have already been printed
            if (x != y) {
                cords[count][0] = y + x_centre;
                cords[count][1] = x + y_centre;
                count++;

                cords[count][0] = -y + x_centre;
                cords[count][1] = x + y_centre;
                count++;

                cords[count][0] = y + x_centre;
                cords[count][1] = -x + y_centre;
                count++;

                cords[count][0] = -y + x_centre;
                cords[count][1] = -x + y_centre;
                count++;
            }
        }
        return cords;
    }

    @Override
    public void draw(ColorGrid cg) {
        for (int[] coordinate : coordinates) {
            if (coordinate == null) {
                continue;
            }
            cg.set(coordinate[1], coordinate[0], this.color);
        }
    }
}

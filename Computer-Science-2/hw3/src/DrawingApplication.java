import bridges.base.Color;
import bridges.connect.Bridges;
import bridges.base.ColorGrid;

public class DrawingApplication {
	public static void main(String[] args) throws Exception {
		// SETUP: edit the arguments passed to the Bridges constructor
		Bridges bridges = new Bridges(0, "BMC__", "863137931540");

		// read in the input image 
		Scene img = new Scene(new Color("blue"));

		// create a BRIDGES color grid
		// rows is number of y pixels in height
		// cols is number of x pixels in width
		ColorGrid cg = new ColorGrid(50, 50);

		img.addMark(new Point(0, 0, new Color("red")));
		img.addMark(new Point(49, 25, new Color("white")));

		img.addMark(new HorizontalLine(10, 20, 40, new Color("green")));
		img.addMark(new HorizontalLine(30, 40, 40, new Color("cyan")));
		img.addMark(new VerticalLine(10, 20, 10, new Color("white")));
		img.addMark(new VerticalLine(30, 40, 10, new Color("red")));

		img.addMark(new Circle(10,10, 35, new Color("black"))); // 56, 52, 44, 40, 36
		img.addMark(new DiagonalLine(0,10,30,30, new Color("brown")));
		img.addMark(new Circle(5,40, 10, new Color("yellow"))); // 28, 24, 16, 12, 8
		img.addMark(new DiagonalLine(40,0, 49, 3, new Color("cyan")));

		// display the original image
		img.draw(cg);
		bridges.setDataStructure(cg);
		bridges.visualize();
	}
}

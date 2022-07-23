import bridges.base.ColorGrid;
import bridges.base.Color;

@SuppressWarnings("ALL")
public class Scene {
	// instance variables for constructor
	private Color color;
	private List<Mark> marks;

    // Creates a Scene with a maximum capacity of Marks and
	// with a background color.
	// maxMarks: the maximum capacity of Marks
	// backgroundColor: the background color of this Scene
	public Scene(Color backgroundColor) {
		this.color = backgroundColor;
		this.marks = new LinkedList<Mark>();
	}

	/*
	// returns true if the Scene has no room for additional Marks
	private boolean isFull() {
		// once it finds an empty space (null) it returns false
		for(Mark i : marks) {
			if(i == null) {
				return false;
			}
		}
		return true;
	}
	 */

	// Adds a Mark to this Scene. When drawn, the Mark
	// will appear on top of the background and previously added Marks
	// m: the Mark to add
	public void addMark(Mark m) {
		marks.add(m);
	}

	// Helper method: deletes the Mark at an index.
	// If no Marks have been previously deleted, the method
	// deletes the ith Mark that was added (0 based).
	// i: the index
	protected void deleteMark(int i) {
		marks.remove(i);
	}

	// Deletes all Marks from this Scene that
	// have a given Color
	// c: the Color
	public void deleteMarksByColor(Color c) {
		for(int i=0; i<marks.size(); i++) {
			if(marks.get(i) == null) {
				continue;
			}
			if(marks.get(i).color == c) {
				marks.remove(i);
			}
		}
	}

	// draws the Marks in this Scene over a background color
	// onto a ColorGrid. Marks will appear on top of the
	// background, and Marks will overlap other Marks if
	// they were added by a more recent call to addMark.
	// cg: the ColorGrid to draw on
	public void draw(ColorGrid cg) {
		// draw background first
		for(int row = 0; row < cg.getHeight(); row++) {
			for(int col = 0; col < cg.getWidth(); col++) {
				cg.set(row, col, this.color);
			}
		}

		// draws the marks and lines
		for(int i=0; i<marks.size(); i++) {
			if(marks.get(i) == null) {
				continue;
			}
			marks.get(i).draw(cg);
		}
	}
}

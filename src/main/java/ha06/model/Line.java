package ha06.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Drawable {

	private Pos start;
	private Pos end;

	public Line(String id, Pos start, Pos end) {
		super(id);
		this.start = start;
		this.end = end;
	}

	public Line(String id, int x1, int y1, int x2, int y2) {
		this(id, new Pos(x1, y1), new Pos(x2, y2));
	}

	public Pos getStart() {
		return start;
	}

	public void setStart(Pos start) {
		this.start = start;
	}

	public Pos getEnd() {
		return end;
	}

	public void setEnd(Pos end) {
		this.end = end;
	}

	@Override
	public Line makeCopy() {
		Line line = null;
		try {
			line = this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return line;
	}

	@Override
	protected Line clone() throws CloneNotSupportedException {
		Line line = (Line) super.clone();
		line.setStart(start.makeCopy());
		line.setEnd(end.makeCopy());
		return line;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.setLineWidth(1.0);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	@Override
	public void move(int x, int y) {
		start.move(x, y);
		end.move(x, y);
	}

	@Override
	public String toString() {
		return "line: " + getId() + ", " + start + ", " + end;
	}
}

package ha06.commands.draw;

import ha06.Store;
import ha06.commands.base.Command;
import ha06.model.Line;

public class LineCommand extends Command {

	//line id x1 y1 x2 y2
	@Override
	public boolean process(String line, Store store) {
		String[] array = line.split(" ");
		if (array.length < 6) {
			return false;
		}

		String id = array[1];

		if (store.hasDrawable(id)) {
			return false;
		}

		int x1 = Integer.parseInt(array[2]);
		int y1 = Integer.parseInt(array[3]);
		int x2 = Integer.parseInt(array[4]);
		int y2 = Integer.parseInt(array[5]);
		Line line1 = new Line(id, x1, y1, x2, y2);
		store.addDrawable(line1);
		return true;
	}

	@Override
	public boolean processUndo(String line, Store store) {
		String[] array = line.split(" ");

		if (array.length < 6) {
			return false;
		}

		String id = array[1];

		if (!store.hasDrawable(id)) {
			return false;
		}

		store.deleteDrawables(id);
		return true;

	}

	@Override
	public String command() {
		return "line";
	}
}

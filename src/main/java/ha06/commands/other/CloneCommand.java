package ha06.commands.other;

import ha06.Store;
import ha06.commands.base.Command;
import ha06.model.Drawable;

public class CloneCommand extends Command {

	@Override
	public boolean process(String line, Store store) {
		String[] array = line.split(" ");

		if (array.length < 5) {
			return false;
		}

		String oldGroupId = array[1];
		String newGroupId = array[2];
		int x = Integer.parseInt(array[3]);
		int y = Integer.parseInt(array[4]);

		Drawable drawable = store.getCopy(oldGroupId);
		drawable.setId(newGroupId);
		drawable.move(x,y);
		store.addDrawable(drawable);

		return true;
	}

	@Override
	public boolean processUndo(String line, Store store) {
		String[] array = line.split(" ");

		if (array.length < 5) {
			return false;
		}

		String newGroupId = array[2];


		if (!store.hasDrawable(newGroupId)) {
			return false;
		}

		store.deleteDrawables(newGroupId);

		return true;
	}

	@Override
	public String command() {
		return "clone";
	}
}

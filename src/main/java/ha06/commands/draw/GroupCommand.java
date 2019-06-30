package ha06.commands.draw;
/*
import ha06.Store;
import ha06.commands.base.Command;
import ha06.model.Drawable;
import ha06.model.Group;

public class GroupCommand extends Command {

	//group id ids...
	@Override
	public boolean process(String line, Store store) {
		String[] array = line.split(" ");

		if (array.length < 3) {
			return false;
		}

		String groupId = array[1];

		if (store.hasDrawable(groupId)) {
			return false;
		}

		String[] ids = new String[array.length - 2];
		System.arraycopy(array, 2, ids, 0, array.length - 2);

		Group group = new Group(groupId);

		for (String id : ids) {
			group.addChildren(store.getCopy(id));
			store.deleteDrawables(id);
		}

		store.addDrawable(group);

		return true;

	}

	@Override
	public boolean processUndo(String line, Store store) {
		String[] array = line.split(" ");

		if (array.length < 3) {
			return false;
		}

		String groupId = array[1];

		if (!store.hasDrawable(groupId)) {
			return false;
		}

		Group group = store.getGroup(groupId);

		for (Drawable drawable : group.getChildren()) {
			store.addDrawable(drawable);
		}

		store.deleteDrawables(group);

		return true;
	}

	@Override
	public String command() {
		return "group";
	}
}
*/
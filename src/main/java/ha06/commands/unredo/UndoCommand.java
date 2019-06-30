package ha06.commands.unredo;

/*
import ha06.Store;
import ha06.commands.base.Command;

public class UndoCommand extends Command {
	@Override
	public boolean process(String line, Store store) {
		if (line.contains(" ")) {
			String[] array = line.split(" ");

			int index = Integer.parseInt(array[1]);
			System.out.println("undo index "+index);


			//String lastCommand = store.getLastCommand(index);
			store.undoCommand(index);
			return true;
		} else {
			return process("undo 0", store);
		}
	}

	@Override
	public boolean processUndo(String line, Store store) {
		System.out.println("undoUndo " + line);
		return false;
	}

	@Override
	public String command() {
		return "undo";
	}
}
*/

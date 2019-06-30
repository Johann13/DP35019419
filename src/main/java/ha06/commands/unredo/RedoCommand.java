package ha06.commands.unredo;

/*
import ha06.Store;
import ha06.commands.base.Command;

public class RedoCommand extends Command {

	@Override
	public boolean process(String line, Store store) {
		if (line.contains(" ")) {
			String[] array = line.split(" ");

			int index = Integer.parseInt(array[1]);
			System.out.println("redo index "+index);

			//String lastCommand = store.getLastCommand(index);
			store.redoCommand(index);
			return true;
		} else {
			return process("redo 0", store);
		}
	}

	@Override
	public boolean processUndo(String line, Store store) {
		return false;
	}

	@Override
	public String command() {
		return "redo";
	}
}
*/
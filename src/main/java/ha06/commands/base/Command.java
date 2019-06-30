package ha06.commands.base;

/*
import ha06.Store;

public abstract class Command {

	public boolean handle(String line, Store store) {
		String cmd = line.split(" ")[0].toLowerCase();
		if (cmd.equals(command())) {
			System.out.println("handle "+line);
			return process(line, store);
		}
		return false;
	}

	public boolean handleUndo(String line, Store store) {
		String cmd = line.split(" ")[0].toLowerCase();
		if (cmd.equals(command())) {
			System.out.println("handleUndo "+line);
			return processUndo(line, store);
		}
		return false;
	}

	public abstract boolean process(String line, Store store);

	public abstract boolean processUndo(String line, Store store);

	public abstract String command();
}
*/
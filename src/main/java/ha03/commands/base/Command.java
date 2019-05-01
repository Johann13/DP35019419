package ha03.commands.base;

import ha03.Assembler;

public abstract class Command {

	public void handle(String line, Assembler assembler) {
		String cmd = line.split(" ")[0].toLowerCase();
		if (cmd.equals(command())) {
			process(line, assembler);
		}
	}

	public abstract void process(String line, Assembler assembler);

	public abstract String command();
}

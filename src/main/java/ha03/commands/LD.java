package ha03.commands;

import ha03.Assembler;
import ha03.commands.base.Command;

public class LD extends Command {

	@Override
	public void process(String line, Assembler assembler) {
		String name = line.split(" ")[1];
		int i = assembler.getStore().get(name);
		assembler.getStack().push(i);
	}

	@Override
	public String command() {
		return "ld";
	}
}

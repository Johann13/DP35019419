package ha03.commands;

import ha03.Assembler;
import ha03.commands.base.Command;

public class Store extends Command {

	@Override
	public void process(String line, Assembler assembler) {
		int i = assembler.getStack().pop();
		String name = line.split(" ")[1];
		assembler.getStore().put(name, i);
	}

	@Override
	public String command() {
		return "store";
	}
}

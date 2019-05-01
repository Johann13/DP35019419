package ha03.commands;

import ha03.Assembler;
import ha03.commands.base.Command;

public class Print extends Command {

	@Override
	public void process(String line, Assembler assembler) {
		int i = assembler.getStack().peek();
		System.out.println("Print: " + i);
	}

	@Override
	public String command() {
		return "print";
	}
}

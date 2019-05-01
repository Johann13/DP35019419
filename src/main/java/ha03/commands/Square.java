package ha03.commands;

import ha03.Assembler;
import ha03.commands.base.Command;

public class Square extends Command {

	@Override
	public void process(String line, Assembler assembler) {
		int i = assembler.getStack().pop();
		assembler.getStack().push(i * i);
	}

	@Override
	public String command() {
		return "square";
	}
}

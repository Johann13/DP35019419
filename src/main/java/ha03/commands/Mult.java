package ha03.commands;

import ha03.Assembler;
import ha03.commands.base.TwoArgsCommand;

public class Mult extends TwoArgsCommand {


	@Override
	public void process(int a, int b, Assembler assembler) {
		assembler.getStack().push(a * b);
	}

	@Override
	public String command() {
		return "mult";
	}

}

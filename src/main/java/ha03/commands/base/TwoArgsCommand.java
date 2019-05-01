package ha03.commands.base;

import ha03.Assembler;

public abstract class TwoArgsCommand extends Command {
	@Override
	public void process(String line, Assembler assembler) {
		int a = assembler.getStack().pop();
		int b = assembler.getStack().pop();
		process(a, b, assembler);
	}

	public abstract void process(int a, int b, Assembler assembler);
}

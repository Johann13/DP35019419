package ha03.commands;

import ha03.Assembler;
import ha03.commands.base.Command;

public class LDC extends Command {

	@Override
	public void process(String line, Assembler assembler) {
		int i = Integer.parseInt(line.split(" ")[1]);
		assembler.getStack().push(i);
	}

	@Override
	public String command() {
		return "ldc";
	}

}

package ha03;

import ha03.commands.*;
import org.junit.Assert;
import org.junit.Test;
// HA03: 08/10
// -2 java.lang.NullPointerException
//	at ha03.Assembler.<init>(Assembler.java:34)
//	at ha03.HA03Test.p1(HA03Test.java:11)
public class HA03Test {

	@Test
	public void p1() {
		Assembler assembler = new Assembler(getClass().getResource("p1.a"));

		assembler.addCommand(new LD(), new LDC(), new Mult(), new Print(), new Store());
		assembler.printLines();
		assembler.printCommands();
		assembler.handle();

		int i = assembler.getStack().peek();
		Assert.assertEquals(42, i);
	}

	@Test
	public void p2() {
		Assembler assembler = new Assembler(getClass().getResource("p2.a"));

		assembler.addCommand(new LD(), new LDC(), new Mult(), new Print(), new Store(), new Add(), new Square());
		assembler.printLines();
		assembler.printCommands();
		assembler.handle();
		int i = assembler.getStack().peek();
		Assert.assertEquals(256, i);

	}

	@Test
	public void p3() {
		Assembler assembler = new Assembler(getClass().getResource("p3.a"));

		assembler.addCommand(new LD(), new LDC(), new Mult(), new Print(), new Store(), new Add(), new Square());
		assembler.printLines();
		assembler.printCommands();
		assembler.handle();
		int x = assembler.getStore().get("x");
		Assert.assertEquals(100, x);
	}
}

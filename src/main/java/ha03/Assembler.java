package ha03;

import ha03.commands.base.Command;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Assembler {

	private Stack<Integer> stack;
	private List<String> lines;
	private List<Command> commands;
	private Map<String, Integer> store;

	private Assembler() {
		stack = new Stack<>();
		store = new HashMap<>();
	}

	public Assembler(String path) {
		this();
		try {
			this.lines = Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Assembler(URL url) {
		this(new File(url.getFile()).getPath());
	}

	public void addCommand(Command... commands) {
		if (this.commands == null) {
			this.commands = new ArrayList<>();
		}
		Collections.addAll(this.commands, commands);
	}

	public void handle() {
		if (commands == null || lines == null) {
			System.out.println("No lines or commands found");
			return;
		}
		if (commands.isEmpty() || lines.isEmpty()) {
			System.out.println("No lines or commands found");
			return;
		}
		System.out.println("---Handle---");
		for (String line : lines) {
			for (Command command : commands) {
				command.handle(line, this);
			}
		}
		System.out.println("---Handle---");
	}

	public void printLines() {
		if (lines == null || lines.isEmpty()) {
			System.out.println("No Lines");
			return;
		}
		System.out.println("---Program lines---");
		for (String line : lines) {
			System.out.println(line);
		}
		System.out.println("---Program lines---");
	}

	public void printCommands() {
		if (commands == null || commands.isEmpty()) {
			System.out.println("No commands");
			return;
		}
		System.out.println("---Assembler commands---");
		for (Command c : commands) {
			System.out.println(c.command());
		}
		System.out.println("---Assembler commands---");
	}

	public Stack<Integer> getStack() {
		return stack;
	}

	public Map<String, Integer> getStore() {
		return store;
	}
}

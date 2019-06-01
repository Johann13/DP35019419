package ha06;

import ha06.commands.base.Command;
import ha06.model.Drawable;
import ha06.model.Group;
import ha06.model.Line;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Store {

	private ObservableList<String> commands;
	private ObservableList<String> undoCommands;

	private List<Command> commandList;
	private Stack<Drawable> drawableStack;

	public Store() {
		commands = FXCollections.observableArrayList();
		undoCommands = FXCollections.observableArrayList();

		commandList = new ArrayList<>();
		drawableStack = new Stack<>();
	}

	public ObservableList<String> getCommands() {
		return commands;
	}

	public ObservableList<String> getUndoCommands() {
		return undoCommands;
	}

	public List<Command> getCommandList() {
		return commandList;
	}

	public Stack<Drawable> getDrawableStack() {
		return drawableStack;
	}


	public void addDrawable(Drawable... drawables) {
		for (Drawable drawable : drawables) {
			drawableStack.push(drawable);
		}
	}

	public void deleteDrawables(Drawable... drawables) {
		for (Drawable drawable : drawables) {
			drawableStack.remove(drawable);
		}
	}

	public void deleteDrawables(String... ids) {
		for (String id : ids) {
			drawableStack.removeIf(drawable -> drawable.getId().equals(id));
		}
	}

	public void addCommand(Command command) {
		commandList.add(command);
	}

	public void addCommand(Command... commands) {
		commandList.addAll(Arrays.asList(commands));
	}

	public void draw(GraphicsContext gc) {
		for (Drawable drawable : drawableStack) {
			drawable.draw(gc);
		}
		//printDraws();
	}

	public void handleCommand(String text) {
		for (Command command : commandList) {
			if (command.handle(text, this)) {
				if (!text.startsWith("undo") && !text.startsWith("redo") && !text.startsWith("clear")) {
					commands.add(text);
				}
				return;
			}
		}
	}


	public void printDraws() {
		System.out.println("commands=" + commands.size() + " undoCommands " + undoCommands.size());
		for (Drawable drawable : drawableStack) {
			System.out.println(drawable);
		}
	}

	public String getCommand(int i) {
		return commands.get(i);
	}

	public String getLastCommand(int i) {
		return getCommand((commands.size() - 1) - i);
	}

	public boolean hasDrawable(String id) {
		for (Drawable drawable : drawableStack) {
			if (drawable.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public Drawable get(String id) {
		System.out.println("get(" + id + ");");
		for (Drawable drawable : drawableStack) {
			System.out.println(drawable.getId());
			if (drawable.getId().equals(id)) {
				return drawable;
			} else if (drawable instanceof Group) {
				Drawable child = ((Group) drawable).getChildById(id);
				if (child != null) {
					return child;
				}
			}
		}
		return null;
	}

	public Drawable getCopy(String id) {
		return get(id).makeCopy();
	}

	public Line getLine(String id) {
		for (Drawable drawable : drawableStack) {
			if (drawable.getId().equals(id)) {
				if (drawable instanceof Line) {
					return (Line) drawable;
				}
			}
		}
		return null;
	}

	public Line getLineCopy(String id) {
		return getLine(id).makeCopy();
	}

	public Group getGroup(String id) {
		for (Drawable drawable : drawableStack) {
			if (drawable.getId().equals(id)) {
				if (drawable instanceof Group) {
					return (Group) drawable;
				}
			}
		}
		return null;
	}

	public Group getGroupCopy(String id) {
		return getGroup(id).makeCopy();
	}

	public void clearDrawables() {
		commands.clear();
		undoCommands.clear();
		drawableStack.clear();
	}


	public void undoCommand(int index) {
		String command = commands.get((commands.size() - 1) - index);
		System.out.println("undoCommand " + command);
		for (Command c : commandList) {
			if (c.handleUndo(command, this)) {
				if (!command.startsWith("undo") && !command.startsWith("redo") && !command.startsWith("clear")) {
					undoCommands.add(command);
					commands.remove((commands.size() - 1) - index);
				}
				return;
			}
		}
	}

	public void redoCommand(int index) {
		String command = undoCommands.get((undoCommands.size() - 1) - index);
		System.out.println("redoCommand " + command);
		for (Command c : commandList) {
			if (c.handle(command, this)) {
				if (!command.startsWith("undo") && !command.startsWith("redo") && !command.startsWith("clear")) {
					commands.add(command);
					undoCommands.remove((undoCommands.size() - 1) - index);
				}
				return;
			}
		}
	}
}

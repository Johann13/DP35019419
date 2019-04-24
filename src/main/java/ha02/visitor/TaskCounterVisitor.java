package ha02.visitor;

import ha02.model.Feature;
import ha02.model.Task;
import ha02.model.Unit;

public class TaskCounterVisitor extends Visitor {
	@Override
	public int visit(Unit unit) {
		return visitChildren(0, unit.getChildren());
	}

	@Override
	public int visit(Task task) {
		return visitChildren(1, task.getChildren());
	}

	@Override
	public int visit(Feature feature) {
		return visitChildren(0, feature.getChildren());
	}
}

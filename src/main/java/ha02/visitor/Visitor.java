package ha02.visitor;

import ha02.model.Feature;
import ha02.model.Task;
import ha02.model.Unit;

import java.util.List;

public class Visitor {

	public int visit(Unit unit) {
		return visitChildren(0, unit.getChildren());
	}

	public int visit(Task task) {
		return visitChildren(0, task.getChildren());
	}

	public int visit(Feature feature) {
		return visitChildren(0, feature.getChildren());
	}


	int visitChildren(int init, List<Unit> children) {
		int sum = init;
		if (children != null) {
			for (Unit u : children) {
				if (u instanceof Feature) {
					sum += visit((Feature) u);
				} else if (u instanceof Task) {
					sum += visit((Task) u);
				} else {
					sum += visit(u);
				}
			}
		}
		return sum;
	}
}

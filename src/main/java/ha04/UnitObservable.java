package ha04;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class UnitObservable {

	private long timestamp;

	private List<UnitObserver> observerList;

	protected abstract String className();


	UnitObservable() {
		timestamp = new Date().getTime();
		observerList = new ArrayList<>();
	}

	void notify(String propertyName, Object newVal, Object oldVal) {
		for (UnitObserver observer : this.observerList) {
			observer.update(new ObjectChange(timestamp + "", timestamp, propertyName, newVal, oldVal));
		}
	}

	private void addListener(UnitObserver unitObservers) {
		if (!observerList.contains(unitObservers)) {
			observerList.add(unitObservers);
		}
	}

	public void addListener(UnitObserver... unitObservers) {
		for (UnitObserver observer : unitObservers) {
			addListener(observer);
		}
	}

	public void removeListener(UnitObserver... unitObservers) {
		for (UnitObserver observer : unitObservers) {
			this.observerList.remove(observer);
		}
	}

	public List<UnitObserver> getObserverList() {
		return observerList;
	}

	public String getId() {
		return timestamp + "";
	}
}

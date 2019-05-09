package ha04;


public class ObjectChange {

	private String objectId;
	private long timestamp;
	private String propertyName;
	private Object newVal;
	private Object oldVal;

	ObjectChange(String objectId, long timestamp, String propertyName, Object newVal, Object oldVal) {
		this.objectId = objectId;
		this.timestamp = timestamp;
		this.propertyName = propertyName;
		this.newVal = newVal;
		this.oldVal = oldVal;
	}

	public String getObjectId() {
		return objectId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Object getNewVal() {
		return newVal;
	}

	public Object getOldVal() {
		return oldVal;
	}

	@Override
	public String toString() {
		return "id:" + objectId + ",time:" + timestamp + ",propertyName:" + propertyName + ",oldVal:" + oldVal + ",newVal:" + newVal;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
		/*
		if (obj instanceof ObjectChange) {
			ObjectChange o = (ObjectChange) obj;
			return objectId.equals(o.objectId) && timestamp == o.timestamp && propertyName.equals(o.propertyName) && newVal == o.newVal && oldVal == o.oldVal;
		}
		return false;
		 */
	}
}

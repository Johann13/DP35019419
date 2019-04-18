package ha01;

import ha01.caterer.Caterer;

public class DeliveryService {

	private Caterer subContractor;

	public DeliveryService() {

	}

	public DeliveryService(Caterer caterer) {
		this.subContractor = caterer;
	}

	public void setSubContractor(Caterer subContractor) {
		this.subContractor = subContractor;
	}

	public void deliver(String foodNo, String address) {
		if (subContractor != null) {
			subContractor.deliver(foodNo, address);
		} else {
			System.out.println("No Caterer!");
		}
	}

}

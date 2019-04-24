package ha01;

import ha01.caterer.AsiaShop;
import ha01.caterer.BurgerShop;
import ha01.caterer.DoenerShop;
import ha01.caterer.PizzaShop;
import org.junit.Test;

public class DeliveryTest {

	@Test
	public void task1() {
		//Create DeliveryService with a Caterer
		DeliveryService deliveryService = new DeliveryService(new BurgerShop());
		//deliver and print the result
		deliveryService.deliver("M4", "My Home Address");

		//swap the Caterer and print the result
		deliveryService.setSubContractor(new PizzaShop());
		deliveryService.deliver("M4", "My other Address");
		deliveryService.setSubContractor(new DoenerShop());
		deliveryService.deliver("M4", "My third Address");
	}

	@Test
	public void task2() {
		//Create DeliveryService with a Caterer
		DeliveryService deliveryService = new DeliveryService(new BurgerShop());
		//deliver and print the result
		deliveryService.deliver("M4", "My Home Address");

		//swap the Caterer and print the result
		deliveryService.setSubContractor(new PizzaShop());
		deliveryService.deliver("M4", "My other Address");
		deliveryService.setSubContractor(new DoenerShop());
		deliveryService.deliver("M4", "My third Address");

		//test newly added AsiaShop
		deliveryService.setSubContractor(new AsiaShop());
		deliveryService.deliver("M4", "My new Address");
	}

}

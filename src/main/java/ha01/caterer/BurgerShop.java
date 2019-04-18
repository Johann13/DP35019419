package ha01.caterer;

public class BurgerShop implements Caterer {
	@Override
	public void deliver(String foodNo, String address) {
		System.out.println("I deliver Burger " + foodNo);
	}
}

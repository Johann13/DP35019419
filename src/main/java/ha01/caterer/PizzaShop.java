package ha01.caterer;

public class PizzaShop implements Caterer {
	@Override
	public void deliver(String foodNo, String address) {
		System.out.println("I deliver Pizza "+foodNo);
	}
}

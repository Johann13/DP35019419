package ha01.caterer;

public class AsiaShop implements Caterer {
	@Override
	public void deliver(String foodNo, String address) {
		System.out.println("I deliver Asian food? " + foodNo);
	}
}

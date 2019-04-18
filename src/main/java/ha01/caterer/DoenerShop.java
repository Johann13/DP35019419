package ha01.caterer;

public class DoenerShop implements Caterer{
	@Override
	public void deliver(String foodNo, String address) {
		System.out.println("I deliver Doener "+foodNo);
	}
}

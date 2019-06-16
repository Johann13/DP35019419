package ha07;

import ha07.builder.ShopBuilder;
import ha07.builder.WarehouseBuilder;
import ha07.model.shop.ShopProduct;
import ha07.server.ShopServer;
import ha07.server.WarehouseServer;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HA07Test {

	@Test
	public void test() throws InterruptedException {

		try {
			if (Files.exists(Paths.get("database/Warehouse.yaml"))) {
				Files.delete(Paths.get("database/Warehouse.yaml"));
			}
			if (Files.exists(Paths.get("database/Shop.yaml"))) {
				Files.delete(Paths.get("database/Shop.yaml"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ShopServer.main(null);
		Thread.sleep(1000);
		WarehouseServer.main(null);


		Thread.sleep(1000);

		WarehouseServer.builder.addLotToStock("lotId1", "Shoe 42, size 8", 50);

		Thread.sleep(1000);
		WarehouseServer.builder.addLotToStock("lotId2", "Shoe 42, size 8", 50);

		Thread.sleep(1000);
		WarehouseServer.builder.addLotToStock("lotId3", "Shoe 42, size 9", 50);

		Thread.sleep(1000);

		ArrayList<ShopProduct> products = ShopServer.builder.shop.getProducts();

		//products.forEach((p) -> System.out.println(p.getName()));

		Thread.sleep(10000);
		//shopBuilder.addCustomer("Alice 1","Wonderland 1");
		//shopBuilder.orderProduct("o1","Shoe 42, size 8","Alice 1");


	}

}

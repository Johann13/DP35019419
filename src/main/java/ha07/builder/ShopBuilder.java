package ha07.builder;

import ha07.model.shop.Shop;
import ha07.model.shop.ShopCustomer;
import ha07.model.shop.ShopOrder;
import ha07.model.shop.ShopProduct;
import ha07.proxy.WarehouseProxy;
import org.fulib.yaml.EventFiler;
import org.fulib.yaml.EventSource;
import org.fulib.yaml.Yamler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.fulib.yaml.EventSource.EVENT_KEY;
import static org.fulib.yaml.EventSource.EVENT_TYPE;

public class ShopBuilder {

	private static final String ADD_PRODUCT_TO_SHOP = "addProductToShop";
	private static final String ADD_CUSTOMER = "addCustomer";
	private static final String NUMBER_OF_ITEMS = "numberOfItems";
	private static final String PRODUCT = "product";
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public Shop shop;
	public EventSource eventSource;
	public WarehouseProxy warehouseProxy;

	public ShopBuilder() {
		shop = new Shop();
		warehouseProxy = new WarehouseProxy();
		eventSource = new EventSource();
		EventFiler eventFiler = new EventFiler(eventSource).setHistoryFileName("database/Shop.yaml");
		String yaml = eventFiler.loadHistory();


		if (yaml != null) {
			ArrayList<LinkedHashMap<String, String>> eventList = new Yamler().decodeList(yaml);
			applyEvents(eventList);
		}
		eventFiler.startEventLogging();
	}

	public void applyEvents(ArrayList<LinkedHashMap<String, String>> list) {
		for (LinkedHashMap<String, String> map : list) {
			switch (map.get(EVENT_TYPE)) {
				case ADD_PRODUCT_TO_SHOP:
					double num = Double.valueOf(map.get(NUMBER_OF_ITEMS));
					addProductToShop(map.get(EVENT_KEY), map.get(PRODUCT), num);
					break;
				case ADD_CUSTOMER:
					addCustomer(map.get(NAME), map.get(ADDRESS));
					break;

			}
		}
	}

	public void addCustomer(String name, String address) {
		LinkedHashMap<String, String> event = eventSource.getEvent(name);
		if (event != null) {
			return;
		}
		ShopCustomer customer = getOrCreateCustomer(name);
		customer.setAddress(address);

		event = new LinkedHashMap<>();
		event.put(EVENT_TYPE, ADD_CUSTOMER);
		event.put(EVENT_KEY, name);
		event.put(NAME, name);
		event.put(ADDRESS, address);
		eventSource.append(event);
	}

	public void addProductToShop(String id, String productName, double num) {
		LinkedHashMap<String, String> event = eventSource.getEvent(id);
		if (event != null) {
			return;
		}

		String productId = productName.replaceAll("\\W", "");
		ShopProduct product = getOrCreateProducts(productName);

		double inStock = product.getInStock();
		product.setInStock(inStock + num);

		event = new LinkedHashMap<>();
		event.put(EVENT_TYPE, ADD_PRODUCT_TO_SHOP);
		event.put(EVENT_KEY, id);
		event.put(PRODUCT, product.getName());
		event.put(NUMBER_OF_ITEMS, "" + num);
		eventSource.append(event);

	}


	public ShopProduct getOrCreateProducts(String productName) {
		String productId = productName.replace("\\W", "");

		for (ShopProduct p : shop.getProducts()) {
			if (p.getId().equals(productId)) {
				return p.setName(productName);
			}
		}


		return new ShopProduct().setId(productId).setName(productName).setShop(shop);
	}


	public ShopCustomer getOrCreateCustomer(String customerName) {
		for (ShopCustomer p : shop.getCustomers()) {
			if (p.getName().equals(customerName)) {
				return p.setName(customerName);
			}
		}
		return new ShopCustomer().setName(customerName).setShop(shop);
	}

	public ShopOrder getOrCreateOrder(String orderId) {
		for (ShopOrder p : shop.getOrders()) {
			if (p.getId().equals(orderId)) {
				return p;
			}
		}
		return new ShopOrder().setId(orderId).setShop(shop);
	}

	public void orderProduct(String orderId, String productName, String customerName) {
		LinkedHashMap<String, String> event = eventSource.getEvent(orderId);
		if (event != null) {
			return;
		}
		ShopProduct product = getOrCreateProducts(productName);
		ShopCustomer customer = getOrCreateCustomer(customerName);
		ShopOrder order = getOrCreateOrder(orderId);
		order.setShopProduct(product).setShopCustomer(customer);

		event = new LinkedHashMap<>();
		event.put(EVENT_TYPE, ADD_PRODUCT_TO_SHOP);
		event.put(EVENT_KEY, orderId);
		event.put(PRODUCT, product.getName());
		event.put(NAME, customer.getName());
		eventSource.append(event);

	}
}

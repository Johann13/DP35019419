package ha07.builder;

import ha07.model.warehouse.Lot;
import ha07.model.warehouse.PalettePlace;
import ha07.model.warehouse.Warehouse;
import ha07.model.warehouse.WarehouseProduct;
import ha07.proxy.ShopProxy;
import org.fulib.yaml.EventFiler;
import org.fulib.yaml.EventSource;
import org.fulib.yaml.Yamler;
import sun.plugin.services.WPlatformService;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.fulib.yaml.EventSource.EVENT_KEY;
import static org.fulib.yaml.EventSource.EVENT_TYPE;

public class WarehouseBuilder {

	public Warehouse warehouse;
	private ShopProxy shopProxy;
	public EventSource eventSource;

	private final static String ADD_LOT_TO_STOCK = "addLotToStock";
	private final static String PRODUCT = "product";
	private final static String SIZE = "size";
	private final static String ADDRESS = "address";
	private final static String PRODUCT_ID = "productId";
	private final static String ORDER_PRODUCT = "orderProduct";
	private final static String LOT_ID = "lotId";
	private final static String ADD_PRODUCT = "addProduct";

	public WarehouseBuilder() {
		warehouse = new Warehouse();
		shopProxy = new ShopProxy();
		eventSource = new EventSource();
		EventFiler eventFiler = new EventFiler(eventSource).setHistoryFileName("database/Warehouse.yaml");

		for (int i = 23; i < 26; i++) {
			PalettePlace place = new PalettePlace().setColumn(i).setRow(42).setId(String.format("place%d%xd", 42, i)).setWarehouse(warehouse);
		}

		String yaml = eventFiler.loadHistory();
		if (yaml != null) {
			ArrayList<LinkedHashMap<String, String>> eventList = new Yamler().decodeList(yaml);
			this.applyEvents(eventList);
		}
		eventFiler.startEventLogging();
	}

	public void applyEvents(ArrayList<LinkedHashMap<String, String>> eventList) {

		for (LinkedHashMap<String, String> map : eventList) {
			switch (map.get(EVENT_TYPE)) {
				case ADD_LOT_TO_STOCK:
					int size = Integer.valueOf(map.get(SIZE));
					addLotToStock(map.get(LOT_ID), map.get(PRODUCT), size);
					break;
				case ORDER_PRODUCT:
					orderProduct(map.get(EVENT_KEY), map.get(PRODUCT), map.get(ADDRESS));
					break;
			}
		}

	}

	public void orderProduct(String eventKey, String product, String address) {

	}

	public Lot addLotToStock(String lotId, String productName, int size) {
		Lot result = getOrCreateLot(lotId);
		double oldSize = result.getLotSize();

		WarehouseProduct product = getOrCreateProducts(productName);
		result.setWarehouseProduct(product).setLotSize(size);
		if (result.getPlaces() == null) {
			for (PalettePlace p : warehouse.getPlaces()) {
				if (p.getLot() == null) {
					p.setLot(result);
					break;
				}
			}
		}

		LinkedHashMap<String, String> event = new LinkedHashMap<>();

		event.put(EVENT_TYPE, ADD_LOT_TO_STOCK);
		event.put(EVENT_KEY, lotId);
		event.put(LOT_ID, lotId);
		event.put(PRODUCT, product.getName());
		event.put(SIZE, "" + size);
		eventSource.append(event);
		if (oldSize == 0.0) {
			shopProxy.addProductToShop(lotId, productName, size);
		}
		return result;

	}

	public WarehouseProduct getOrCreateProducts(String productName) {
		String productId = productName.replace("\\W","");

		for(WarehouseProduct p: warehouse.getProducts()){
			if(p.getId().equals(productId)){
				return p.setName(productName);
			}
		}


		return new WarehouseProduct()
				.setId(productId)
				.setName(productName)
				.setWarehouse(warehouse);
	}

	public Lot getOrCreateLot(String lotId) {

		for (WarehouseProduct p : warehouse.getProducts()) {
			for (Lot l : p.getLots()) {
				if (l.getId().equals(lotId)) {
					return l;
				}
			}
		}

		return new Lot().setId(lotId);
	}


}

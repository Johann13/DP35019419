package ha07;

import org.fulib.Fulib;
import org.fulib.builder.ClassBuilder;
import org.fulib.builder.ClassModelBuilder;
import org.fulib.classmodel.ClassModel;
import org.junit.Test;

public class Gen {

	@Test
	public void genWarehouse() {
		ClassModelBuilder mb = Fulib.classModelBuilder("ha07.model.warehouse");

		ClassBuilder wh = mb.buildClass("Warehouse");
		ClassBuilder wo = mb.buildClass("WarehouseOrder").buildAttribute("address", ClassModelBuilder.STRING).buildAttribute("id", ClassModelBuilder.STRING);
		ClassBuilder wp = mb.buildClass("WarehouseProduct").buildAttribute("name", ClassModelBuilder.STRING).buildAttribute("id", ClassModelBuilder.STRING);
		ClassBuilder lot = mb.buildClass("Lot").buildAttribute("id", ClassModelBuilder.STRING).buildAttribute("lotSize", ClassModelBuilder.DOUBLE);
		ClassBuilder pp = mb.buildClass("PalettePlace").buildAttribute("id", ClassModelBuilder.STRING).buildAttribute("column", ClassModelBuilder.DOUBLE).buildAttribute("row", ClassModelBuilder.DOUBLE);

		wh.buildAssociation(wo, "orders", ClassModelBuilder.MANY, "warehouse", ClassModelBuilder.ONE);
		wh.buildAssociation(wp, "products", ClassModelBuilder.MANY, "warehouse", ClassModelBuilder.ONE);
		wh.buildAssociation(pp, "places", ClassModelBuilder.MANY, "warehouse", ClassModelBuilder.ONE);


		wp.buildAssociation(lot, "lots", ClassModelBuilder.MANY, "warehouseProduct", ClassModelBuilder.ONE);
		wp.buildAssociation(wo, "orders", ClassModelBuilder.MANY, "warehouseProduct", ClassModelBuilder.ONE);

		lot.buildAssociation(pp, "places", ClassModelBuilder.MANY, "lot", ClassModelBuilder.ONE);


		ClassModel model = mb.getClassModel();
		Fulib.generator().generate(model);
	}

	@Test
	public void genShop() {
		ClassModelBuilder mb = Fulib.classModelBuilder("ha07.model.shop");

		ClassBuilder shop = mb.buildClass("Shop");
		ClassBuilder sc = mb.buildClass("ShopCustomer").buildAttribute("address", ClassModelBuilder.STRING).buildAttribute("name", ClassModelBuilder.STRING);
		ClassBuilder sp = mb.buildClass("ShopProduct").buildAttribute("name", ClassModelBuilder.STRING).buildAttribute("id", ClassModelBuilder.STRING).buildAttribute("inStock", ClassModelBuilder.DOUBLE).buildAttribute("price", ClassModelBuilder.DOUBLE);
		ClassBuilder so = mb.buildClass("ShopOrder").buildAttribute("id", ClassModelBuilder.STRING);

		shop.buildAssociation(sc, "customers", ClassModelBuilder.MANY, "shop", ClassModelBuilder.ONE);
		shop.buildAssociation(sp, "products", ClassModelBuilder.MANY, "shop", ClassModelBuilder.ONE);
		shop.buildAssociation(so, "orders", ClassModelBuilder.MANY, "shop", ClassModelBuilder.ONE);

		sc.buildAssociation(so, "orders", ClassModelBuilder.MANY, "shopCustomer", ClassModelBuilder.ONE);
		sp.buildAssociation(so, "orders", ClassModelBuilder.MANY, "shopProduct", ClassModelBuilder.ONE);

		ClassModel model = mb.getClassModel();
		Fulib.generator().generate(model);

	}

}

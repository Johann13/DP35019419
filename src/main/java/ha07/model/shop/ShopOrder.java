package ha07.model.shop;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class ShopOrder  
{

   public static final String PROPERTY_id = "id";

   private String id;

   public String getId()
   {
      return id;
   }

   public ShopOrder setId(String value)
   {
      if (value == null ? this.id != null : ! value.equals(this.id))
      {
         String oldValue = this.id;
         this.id = value;
         firePropertyChange("id", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_shop = "shop";

   private Shop shop = null;

   public Shop getShop()
   {
      return this.shop;
   }

   public ShopOrder setShop(Shop value)
   {
      if (this.shop != value)
      {
         Shop oldValue = this.shop;
         if (this.shop != null)
         {
            this.shop = null;
            oldValue.withoutOrders(this);
         }
         this.shop = value;
         if (value != null)
         {
            value.withOrders(this);
         }
         firePropertyChange("shop", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_shopCustomer = "shopCustomer";

   private ShopCustomer shopCustomer = null;

   public ShopCustomer getShopCustomer()
   {
      return this.shopCustomer;
   }

   public ShopOrder setShopCustomer(ShopCustomer value)
   {
      if (this.shopCustomer != value)
      {
         ShopCustomer oldValue = this.shopCustomer;
         if (this.shopCustomer != null)
         {
            this.shopCustomer = null;
            oldValue.withoutOrders(this);
         }
         this.shopCustomer = value;
         if (value != null)
         {
            value.withOrders(this);
         }
         firePropertyChange("shopCustomer", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_shopProduct = "shopProduct";

   private ShopProduct shopProduct = null;

   public ShopProduct getShopProduct()
   {
      return this.shopProduct;
   }

   public ShopOrder setShopProduct(ShopProduct value)
   {
      if (this.shopProduct != value)
      {
         ShopProduct oldValue = this.shopProduct;
         if (this.shopProduct != null)
         {
            this.shopProduct = null;
            oldValue.withoutOrders(this);
         }
         this.shopProduct = value;
         if (value != null)
         {
            value.withOrders(this);
         }
         firePropertyChange("shopProduct", oldValue, value);
      }
      return this;
   }



   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null)
      {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(listener);
      }
      return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(propertyName, listener);
      }
      return true;
   }

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getId());


      return result.substring(1);
   }

   public void removeYou()
   {
      this.setShop(null);
      this.setShopCustomer(null);
      this.setShopProduct(null);

   }


}
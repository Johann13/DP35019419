package ha07.model.shop.tables;

import java.util.ArrayList;

import java.util.LinkedHashMap;

import ha07.model.shop.ShopOrder;

import ha07.model.shop.Shop;

import ha07.model.shop.ShopCustomer;

import ha07.model.shop.ShopProduct;

import java.util.Arrays;

import java.util.function.Predicate;

import java.util.LinkedHashSet;

public class ShopOrderTable 
{

   public ShopOrderTable(ShopOrder... start)
   {
      this.setColumnName("ShopOrder");
      columnMap.put(this.getColumnName(), 0);
      for (ShopOrder current : start)
      {
         ArrayList<Object> row = new ArrayList<>();
         row.add(current);
         table.add(row);
      }
   }

   private ArrayList<ArrayList<Object>> table = new ArrayList<>();

   public ArrayList<ArrayList<Object>> getTable()
   {
      return table;
   }

   public ShopOrderTable setTable(ArrayList<ArrayList<Object>> value)
   {
      this.table = value;
      return this;
   }


   private String columnName = null;

   public String getColumnName()
   {
      return columnName;
   }

   public ShopOrderTable setColumnName(String value)
   {
      this.columnName = value;
      return this;
   }


   private LinkedHashMap<String, Integer> columnMap = new LinkedHashMap<>();

   public LinkedHashMap<String, Integer> getColumnMap()
   {
      return columnMap;
   }

   public ShopOrderTable setColumnMap(LinkedHashMap<String, Integer> value)
   {
      this.columnMap = value;
      return this;
   }


   public StringTable expandId(String... rowName)
   {
      StringTable result = new StringTable();
      result.setColumnMap(this.columnMap);
      result.setTable(this.table);
      int newColumnNumber = this.table.size() > 0 ? this.table.get(0).size() : 0;
      String newColumnName = rowName != null && rowName.length > 0 ? rowName[0] : "" + ((char)('A' + newColumnNumber));
      result.setColumnName(newColumnName);
      columnMap.put(newColumnName, newColumnNumber);

      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         ArrayList<Object> newRow = (ArrayList<Object>) row.clone();
         newRow.add(start.getId());
         this.table.add(newRow);
      }
      return result;
   }

   public ShopTable expandShop(String... rowName)
   {
      ShopTable result = new ShopTable();
      result.setColumnMap(this.columnMap);
      result.setTable(table);
      int newColumnNumber = this.table.size() > 0 ? this.table.get(0).size() : 0;

      String newColumnName = rowName != null && rowName.length > 0 ? rowName[0] : "" + ((char)('A' + newColumnNumber));
      result.setColumnName(newColumnName);
      columnMap.put(newColumnName, newColumnNumber);

      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         ArrayList<Object> newRow = (ArrayList<Object>) row.clone();
         newRow.add(start.getShop());
         this.table.add(newRow);
      }
      return result;
   }

   public ShopOrderTable hasShop(ShopTable rowName)
   {
      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         Shop other = (Shop) row.get(columnMap.get(rowName.getColumnName()));
         if (start.getShop() == other)
         {
            this.table.add(row);
         }
      }
      return this;
   }

   public ShopCustomerTable expandShopCustomer(String... rowName)
   {
      ShopCustomerTable result = new ShopCustomerTable();
      result.setColumnMap(this.columnMap);
      result.setTable(table);
      int newColumnNumber = this.table.size() > 0 ? this.table.get(0).size() : 0;

      String newColumnName = rowName != null && rowName.length > 0 ? rowName[0] : "" + ((char)('A' + newColumnNumber));
      result.setColumnName(newColumnName);
      columnMap.put(newColumnName, newColumnNumber);

      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         ArrayList<Object> newRow = (ArrayList<Object>) row.clone();
         newRow.add(start.getShopCustomer());
         this.table.add(newRow);
      }
      return result;
   }

   public ShopOrderTable hasShopCustomer(ShopCustomerTable rowName)
   {
      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         ShopCustomer other = (ShopCustomer) row.get(columnMap.get(rowName.getColumnName()));
         if (start.getShopCustomer() == other)
         {
            this.table.add(row);
         }
      }
      return this;
   }

   public ShopProductTable expandShopProduct(String... rowName)
   {
      ShopProductTable result = new ShopProductTable();
      result.setColumnMap(this.columnMap);
      result.setTable(table);
      int newColumnNumber = this.table.size() > 0 ? this.table.get(0).size() : 0;

      String newColumnName = rowName != null && rowName.length > 0 ? rowName[0] : "" + ((char)('A' + newColumnNumber));
      result.setColumnName(newColumnName);
      columnMap.put(newColumnName, newColumnNumber);

      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         ArrayList<Object> newRow = (ArrayList<Object>) row.clone();
         newRow.add(start.getShopProduct());
         this.table.add(newRow);
      }
      return result;
   }

   public ShopOrderTable hasShopProduct(ShopProductTable rowName)
   {
      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         ShopProduct other = (ShopProduct) row.get(columnMap.get(rowName.getColumnName()));
         if (start.getShopProduct() == other)
         {
            this.table.add(row);
         }
      }
      return this;
   }

   public ShopOrderTable selectColumns(String... columnNames)
   {
      LinkedHashMap<String, Integer> oldColumnMap = (LinkedHashMap<String, Integer>) this.columnMap.clone();
      this.columnMap.clear();

      int i = 0;
      for (String name : columnNames)
      {
         if (oldColumnMap.get(name) == null)
            throw new IllegalArgumentException("unknown column name: " + name);
         this.columnMap.put(name, i);
         i++;
      }

      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();

      LinkedHashSet<ArrayList<Object> > rowSet = new LinkedHashSet<>();
      for (ArrayList row : oldTable)
      {
         ArrayList newRow = new ArrayList();
         for (String name : columnNames)
         {
            Object value = row.get(oldColumnMap.get(name));
            newRow.add(value);
         }
         if (rowSet.add(newRow))
            this.table.add(newRow);
      }

      return this;
   }

   public ShopOrderTable dropColumns(String... columnNames)
   {
      LinkedHashMap<String, Integer> oldColumnMap = (LinkedHashMap<String, Integer>) this.columnMap.clone();
      this.columnMap.clear();

      LinkedHashSet<String> dropNames = new LinkedHashSet<>();
      dropNames.addAll(Arrays.asList(columnNames));
      int i = 0;
      for (String name : oldColumnMap.keySet())
      {
         if ( ! dropNames.contains(name))
         {
            this.columnMap.put(name, i);
            i++;
         }
      }

      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();

      LinkedHashSet<ArrayList<Object> > rowSet = new LinkedHashSet<>();
      for (ArrayList row : oldTable)
      {
         ArrayList newRow = new ArrayList();
         for (String name : this.columnMap.keySet())
         {
            Object value = row.get(oldColumnMap.get(name));
            newRow.add(value);
         }
         if (rowSet.add(newRow))
            this.table.add(newRow);
      }

      return this;
   }

   public void addColumn(String columnName, java.util.function.Function<java.util.LinkedHashMap<String,Object>,Object> function)
   {
      int newColumnNumber = this.table.size() > 0 ? this.table.get(0).size() : 0;
      for (ArrayList<Object> row : this.table)
      {
         java.util.LinkedHashMap<String,Object> map = new java.util.LinkedHashMap<>();
         for (String key : columnMap.keySet())
         {
            map.put(key, row.get(columnMap.get(key)));
         }
         Object result = function.apply(map);
         row.add(result);
      }
      this.columnMap.put(columnName, newColumnNumber);
   }

   public ShopOrderTable filter(Predicate< ShopOrder > predicate)
   {
      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         ShopOrder start = (ShopOrder) row.get(columnMap.get(this.getColumnName()));
         if (predicate.test(start))
         {
            this.table.add(row);
         }
      }
      return this;
   }

   public ShopOrderTable filterRow(Predicate<LinkedHashMap<String,Object> > predicate)
   {
      ArrayList<ArrayList<Object> > oldTable = (ArrayList<ArrayList<Object> >) this.table.clone();
      this.table.clear();
      for (ArrayList<Object> row : oldTable)
      {
         LinkedHashMap<String,Object> map = new LinkedHashMap< >();
         for (String key : columnMap.keySet())
         {
            map.put(key, row.get(columnMap.get(key)));
         }
         if (predicate.test(map))
         {
            this.table.add(row);
         }
      }
      return this;
   }

   public LinkedHashSet< ShopOrder > toSet()
   {
      LinkedHashSet< ShopOrder > result = new LinkedHashSet<>();
      for (ArrayList row : this.table)
      {
         ShopOrder value = (ShopOrder) row.get(columnMap.get(columnName));
         result.add(value);
      }
      return result;
   }

   public String toString()
   {
      StringBuilder buf = new StringBuilder();
      for (String key : columnMap.keySet())
      {
         buf.append(key).append(" \t");
      }
      buf.append("\n");
      for (ArrayList<Object> row : table)
      {
         for (Object cell : row)
         {
            buf.append(cell).append(" \t");
         }
         buf.append("\n");
      }
      buf.append("\n");
      return buf.toString();
   }


}
//Summary of changes:
//sell() and removeItem() can now throw a ItemNotFoundException. This exception will originate from indexOfItemByName() in Inventory.java
//These exception will be thrown up the call stack until they are handled by processTransaction() in Basket.java

public class Seller {
    public String name;
    public Inventory inventory;

    public Seller(String storeName, Inventory startingInventory) {
        name = storeName;
        inventory = startingInventory;
    }

    /**
     * Purchases an item. As the Seller does not have a money attribute,
     * the item will always be "bought".
     */
    public void buy(ItemInterface item) {
        inventory.addOne(item);
    }

    /**
     * Attempt to sell an item by name. If an item with a matching name is
     * found, the item is removed and returned.
     * @param itemName
     * @return The sold item.
     */
    public ItemInterface sell(String itemName) throws ItemNotFoundException {
        ItemInterface result = removeItem(itemName);
        return result;
    }

    /**
     * Adds an item to the held Inventory.
     * @param item
     */
    public void addItem(ItemInterface item) {
        inventory.addOne(item);
    }

    /**
     * Removes and returns an item from the held Inventory that matches
     * the `itemName` parameter.
     * @param itemName
     */
    public ItemInterface removeItem(String itemName) throws ItemNotFoundException {
            return inventory.removeOne(itemName);
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }
    
}

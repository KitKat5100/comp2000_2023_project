//Summary
//Created new Item class that implements the ItemInterface. Instead of Tomato, Rock, etc. implemeting the interface themselves-
//They have an Item field. When a new Tomato is initialized, a new Item holding the Tomato's details is initialized with them and-
//assigned to it.

//Reasoning:
//Code is now easier to change. If changes are made to ItemInterface (adding new methods), instead of changing the methods for- 
//all items, only the methods here need to be changed. 
//Similar structure would also be useful if Devs want to add groups of functionality on some items only. They could make an 
//"Equippable" class with all the needed functionality, and include that as a field only on items that can be equiped. 

public class Item implements ItemInterface {
    int expiry;
    String description;
    String name;
    double value;

    public Item(int expiry,String desc, String name, double value) {
        this.expiry = expiry;
        this.description = desc;
        this.name = name;
        this.value = value;
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow(name, description, Double.toString(value), expiry + "");
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow(name, Double.toString(value), column3);
    }
}

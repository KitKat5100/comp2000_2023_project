import java.util.ArrayList;

//Summary of changes
//itemIndex throws a custom exception called ItemNotFoundException
//Edited setItemQuantity, add, and remove to catch the new exception from itemIndex
//Edited both processTransaction() methods to handle a ItemNotFoundException that is thrown all the way from Inventory.java

public class Basket implements BasketInterface {
    ArrayList<ItemInterface> items;
    ArrayList<Integer> quantities;

    public Basket() {
        items = new ArrayList<>();
        quantities = new ArrayList<>();
    }
    // Defined a new type of exception and made this method throw it

    public int itemIndex(String itemName) throws ItemNotFoundException {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getInventoryTableRow().getColumnOne().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        throw new ItemNotFoundException("Item does not exist in inventory");
    }

    public ArrayList<CartTableRow> getRowData() {
        ArrayList<CartTableRow> data = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            data.add(items.get(i).getCartRow(quantities.get(i) + ""));
        }

        return data;
    }

    @Override
    public void setItemQuantity(String itemName, int qty) {
        try {
            int index = itemIndex(itemName);
            quantities.set(index, qty);
        }
        catch (ItemNotFoundException e){
        }
    }

    public void add(ItemInterface item) {
        try {
        int index = itemIndex(item.getInventoryTableRow().getColumnOne());
            quantities.set(index, quantities.get(index) + 1);
        }
        catch (ItemNotFoundException e){
            items.add(item);
            quantities.add(1);
        }
    }

    @Override
    public void remove(String itemName) {
        try{
            int index = itemIndex(itemName);
            items.remove(index);
            quantities.remove(index);
        }
        catch (ItemNotFoundException e){
        }
    }

    @Override
    public void processTransaction(Player from, Seller to) {
        ArrayList<ItemInterface> transactionItems = new ArrayList<>();
        boolean rollback = false;
        // Remove/sell items from the `from` parameter
        for (int i = 0; i < items.size() && !rollback; i++) {
            for (int q = 0; q < quantities.get(i); q++) {
                try {
                    ItemInterface saleItem = from.sell(items.get(i).getInventoryTableRow().getColumnOne());
                    transactionItems.add(saleItem);
                }
                catch (ItemNotFoundException e) {
                        rollback = true;
                        break;  // Trigger transaction rollback
                }
            }
        }

        if (rollback) {
            for (ItemInterface item : transactionItems) {
                from.buy(item);  // Return to `from`
            }
        } else {
            for (ItemInterface item : transactionItems) {
                to.buy(item);  // Have `to` buy each of the transaction items
            }
        }
    }

    @Override
    public void processTransaction(Seller from, Player to) {
        ArrayList<ItemInterface> transactionItems = new ArrayList<>();
        boolean rollback = false;
        // Remove/sell items from the `from` parameter
        for (int i = 0; i < items.size() && !rollback; i++) {
            for (int q = 0; q < quantities.get(i); q++) {
                try {
                    ItemInterface saleItem = from.sell(items.get(i).getInventoryTableRow().getColumnOne());
                    transactionItems.add(saleItem);
                }
                catch (ItemNotFoundException e) {
                    rollback = true;
                    break;  // Trigger transaction rollback
                }
            }
        }
        if (rollback) {
            for (ItemInterface item : transactionItems) {
                from.buy(item);  // Return to `from`
            }
        } else {
            for (ItemInterface item : transactionItems) {
                to.buy(item);  // Have `to` buy each of the transaction items
            }
        }
    }

}

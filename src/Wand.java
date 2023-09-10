public class Wand implements ItemInterface {
    int expiry;

    public Wand(int expiry) {
        this.expiry = expiry;   //expiry is set when object is made?
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow("Wand", "A magic wand!", "100.0", expiry + "");
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow("Wand", "100.0", column3);
    }
}

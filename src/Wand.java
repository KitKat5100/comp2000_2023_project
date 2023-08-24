public class Wand implements ItemInterface {
    int expiry;
    String name = "Ye Mum";

    public Wand(int expiry) {
        this.expiry = expiry;   //expiry is set when object is made?
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow(name, "A magic wand!", "100.0", expiry + "");
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow("Wand", "100.0", column3);
    }
}

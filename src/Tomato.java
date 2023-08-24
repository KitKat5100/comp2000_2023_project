public class Tomato implements ItemInterface {
    int expiry;
    String description;

    public Tomato(int expiry,String desc) {
        this.expiry = expiry;
        this.description = desc;
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow("Tomato", description, "15.0", expiry + "");
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow("Tomato", "15.0", column3);
    }
}

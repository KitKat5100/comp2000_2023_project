import javax.swing.JFrame;

public class App {
    private Player player;
    private Storage storage;
    private JFrame frame;
    private PageManager manager;

    public App(Player p, Storage s) {
        player = p;
        storage = s;
        player.setStorageView(storage.getInventory());

        manager = new PageManager(player, storage);

        // Setup of sorting
        setupSearching((InventoryPage) manager.findPage("Player Inventory"));
        setupSearching((InventoryPage) manager.findPage("Storage"));

        // Setup of craftng
        setupCrafting((ItemCraftPage) manager.findPage("Item Crafting"), player);
        setupUncrafting((ProductPage) manager.findPage("Product Page"), player);

        // Window creation
        manager.refresh();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(manager.getJPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Task 1: Defining what each button in the UI will do.
    void setupSearching(InventoryPage page) {
        page.addSearchByButton(new SearchByButton("All", () -> {
            player.getInventory().setSearch(new SearchAll());
            player.getStorageView().setSearch(new SearchAll());
        }));

        page.addSearchByButton(new SearchByButton("Name", () -> {
            player.getInventory().setSearch(new SearchName());
            player.getStorageView().setSearch(new SearchName());
        }));

        page.addSearchByButton(new SearchByButton("Description", () -> {
            player.getInventory().setSearch(new SearchDescription());
            player.getStorageView().setSearch(new SearchDescription());
        }));
    }

    //CALL THE CRAFTING FUNCTION ----------------------------------------
    void setupCrafting(ItemCraftPage page, Player player) {
        try {
          page.setCraftAction((def) -> player.craft(def));
        }
        catch (Exception e) {
        }
    }

    //CALL THE UN-CRAFTING FUNCTION ----------------------------------------
    void setupUncrafting(ProductPage page, Player player) {
        try {
            page.setUncraftAction((item) -> player.uncraft(item));
        }
        catch (Exception e) {
        }
    }
}

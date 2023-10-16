import java.util.ArrayList;

public class Item implements ItemInterface {
    private ItemDefinition definition;
    private ArrayList<ItemInterface> components = new ArrayList<ItemInterface>();

    /**
     * Creates an Item instance with a set definition.
     * The composition list is (created but) left empty. For composite items, the sub-components
     * should be retrieved/removed from some item source, and added with Item::Add(ItemInterface).
     * @param def
     */
    public Item(ItemDefinition def) {
        definition = def;
    }

    //ADDED
    public void add(ItemInterface component)
    {
        components.add(component);
    }

    @Override
    public double getWeight() {
        double weight = definition.getWeight().orElse(getCompositeWeight());
        return weight;
    }

    //ALSO ADDED THIS
    public double getCompositeWeight() {
        double weight = 0.0;
        for(ItemInterface component: components) {
            weight += component.getWeight();
        }
        return weight;
    }

    //ADDED THIS
    public ArrayList<ItemInterface> getComponents(){
        return components;
    }

    @Override
    public String getName() {
        return definition.getName();
    }

    @Override
    public String getDescription() {
        return definition.getDescription();
    }

    @Override
    public ItemDefinition getDefinition() {
        return definition;
    }

    @Override
    //CHANGE TO IMPLEMENT CRAFTING -------------------------
    public String getCompositionDescription() {
        String s = "";
        for(ItemInterface component: components) {
            s += "- " + component.getName() + "\n";
        }
        return s;
    }

    @Override
    public boolean equals(ItemInterface other) {
        return isOf(other.getDefinition());
    }

    @Override
    public boolean isOf(ItemDefinition def) {
        return getName().equals(def.getName());
    }

    @Override
    public String toString() {
        String output = String.format("Item: %s\nDescription: %s\nWeight: %.2f",
            getName(), getDescription(), getWeight());
        output += "\nHashCode: " + Integer.toHexString(this.hashCode());
        return output;
    }

}
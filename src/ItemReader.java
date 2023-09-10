public class ItemReader {
    //Summary of Changes:
    //Made readStartingItem() return an error instead of the original -1 (or NULL? I forgot).
    //Changed the way readStartingItem() initializes new items. Check Item.java and Tomato/Rock/etc. for more details

	/**
	 * itemData format: {ITEM NAME}, {DESCRIPTION}, {VALUE}, {EXPIRATION IF EXPIRES}
	 * index:                0             1           2               3
	 *
	 * Each field is extracted for you to use.
	 *
	 * This method creates all of the starting items (for the player and store).
	 * @param itemData
	 * @return The read in Item
	 */

    public static ItemInterface readStartingItem(String[] itemData) throws ClassNotFoundException {
        String name = itemData[0].trim();                      // DO NOT MODIFY
        String description = itemData[1].trim();               // DO NOT MODIFY
        double value = Double.valueOf(itemData[2].trim());     // DO NOT MODIFY
        String expiry = itemData[3].trim();                    // DO NOT MODIFY
        // NOTE: expiry will be an empty String if the item does not expire
        
        // You may modify the below
        int expiration = -1;
        if (!expiry.isEmpty()) { //If expiry isnt empty, set expiration to that value.
            expiration = Integer.valueOf(expiry);
        }
        if (name.equals("Tomato")) {
            Tomato newTomato = new Tomato(new Item(expiration, description, name, value));
            return newTomato.itemDetails;
        } else if (name.equals("Rock")) {
            Rock newRock = new Rock(new Item(expiration, description, name, value));
            return newRock.itemDetails;
        } else if (name.equals("Wand")) {
            Wand newWand = new Wand(new Item(expiration, description, name, value));
            return newWand.itemDetails;
        } else {
            throw new ClassNotFoundException("Bad Item read in ItemReader"); //if item is none of the pre-defined objects, send an error
        }

    }
}

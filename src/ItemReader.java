public class ItemReader {
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
    public static ItemInterface readStartingItem(String[] itemData) { //I DONT GET IT? WHY KEEP ALL THIS INFO AS DATA IF UR GONNA HARD-CODE OVERWRITE IT ANYWAY
        String name = itemData[0].trim();                      // DO NOT MODIFY
        String description = itemData[1].trim();               // DO NOT MODIFY
        double value = Double.valueOf(itemData[2].trim());     // DO NOT MODIFY
        String expiry = itemData[3].trim();                    // DO NOT MODIFY
        // NOTE: expiry will be an empty String if the item does not expire
        
        // You may modify the below

        //CHANGE THE RETURN VALUE HERE
        int expiration = -1;
        if (!expiry.isEmpty()) { //If expiry isnt empty, set expiration to that value.
            expiration = Integer.valueOf(expiry);
        }
        if (name.equals("Tomato")) {  //creates a new tomato with the expiration (expiry)
            return new Tomato(expiration, description);
        } else if (name.equals("Rock")) {
            return new Rock(expiration);
        } else if (name.equals("Wand")) {
            return new Wand(expiration);
        } else {
            System.err.println("Bad Item read in ItemReader"); //if item is none of the pre-defined objects, send an error
            System.exit(0);
            return null;
        }
    }
}

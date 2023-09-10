   //Summary:
   //Custom exception class for when an item can't be found in an inventory
   public class ItemNotFoundException extends Exception {
        ItemNotFoundException(){
            super();
        }

        ItemNotFoundException(String desc){
            super(desc);
        }
    }
package gameStuff;

import java.util.ArrayList;

public class Merchant {	
    String name;
    int gold;
    ArrayList<Item> inventory = new ArrayList<>();
    
    public Merchant(String name, int gold) {
    	this.name = name;
    	this.gold = gold;
    }
    
    
    public String getName() {
    	return name;
    }
    
    
    public void setGold(int amount) {
    	gold += amount;
    }
    
    
    public int getGold() {
    	return gold;
    }
    
    
    public void addItem(Item item) {
    	inventory.add(item);
    }
    
    
    public void removeItem(Item item) {
    	try {
    		inventory.remove(item);
    	} catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("I tried to remove an item from the merchant's inventory!");
    	}
    }
    
    
    public Item getItem(int i) {
    	return inventory.get(i - 1);
    }
    
    
    public void trade() {
    	while (true) {
    		System.out.println("Would you like to (b)uy, (s)ell, or (c)ancel?\n");
    		String choice = Game.in.nextLine();
    		
	    	switch(choice) {
	    		case "c":
	    			System.out.printf("You turn away from the %s and back to the room.%n%n", getName());
	    			break;
	    		case "b":
	    			buy();
	    			break;
	    		case "s":
	    			sell();
	    			break;
	    		default:
	    			System.out.print(Game.no);
	    			continue;
	    	}
	    	break;
    	}
    }
    
    
    private void buy() {
    	boolean complete = false;
    	
    	isMerchantEmpty();
    	inventoryPicker("merchant");
    	while(!complete) {
    		Item i = chooseItem("from merchant");
    		if (i == null) {
    			complete = true;
    			System.out.printf("The %s thanks you for your patronage.%n%n", getName());
    			break;
    		}
    		if (canBuy(i)) {
    			playerBuy(i);
    			complete = true;
    			break;
    		}
    	}
    }
    
    
    private void sell() {
    	boolean complete = false;
    	
    	isPlayerEmpty();
    	inventoryPicker("player");
    	while (!complete) {
    		Item i = chooseItem("for merchant");
    		if (i == null) {
    			complete = true;
    			System.out.printf("The %s thanks you for your patronage.%n%n", getName());
    			break;
    		}
    		if (canSell(i)) {
    			playerSell(i);
    			complete = true;
    			break;
    		}
    	}
    }
    
    
    private void isMerchantEmpty() {
    	if (inventory.isEmpty()) {
    		System.out.printf("The %s has nothing to sell!%n", getName());
    		return;
    	}
    }
    
   
    private void isPlayerEmpty() {
    	if (Game.player.inventory.isEmpty()) {
    		System.out.println("You have nothing to sell!");
    		return;
    	}
    }
    
    
    private void inventoryPicker(String who) {
    	int i = 0;
    	
    	switch(who) {
    		case "player":
    			for (Item item : Game.player.sellingInventory) {
    				System.out.printf("%d. %s", ++i, item.getName());
    				displayInventory(item);
    			}
    			System.out.println(Game.player.displayGold());
    			break;
    		case "merchant":
    			for (Item item : inventory) {
    				System.out.printf("%d. %s", ++i, item.getName());
    				displayInventory(item);
    			}
    			System.out.println(Game.player.displayGold());
    			break;
    		default:
    			System.out.println(Game.oops);
    			System.out.println("I'm pretty sure you won't see this oopsie in the inventoryPicker!");
    	}
    }
    
    
    private void displayInventory(Item item) {
    	if (item.has("value")) {
    		System.out.printf(" | %d gold", item.getValue());
    	}
    	if (item.has("damage")) {
    		System.out.printf(" | %d damage", item.getDamage());
    	}
    	if (item.has("heal")) {
    		System.out.printf(" | +%d HP", item.getHeal());
    	}
    	System.out.println();
    }
    
    
    private Item chooseItem(String who) {
    	System.out.println("Choose an item number or 0 to cancel.\n");
    	while(true) {
    		try {
    			int choice = Integer.parseInt(Game.in.nextLine());
			
    			if (choice == 0) {
    				return null;
    			}
			
    			switch(who) {
    				case "from merchant":
    					return getItem(choice);
    				case "for merchant":
    					return Game.player.getItem(choice);
    			}
    		} catch (Exception e) {
    			System.out.println("Not a valid response.\n");
    		}
    	}
    }
    
    
    private boolean canBuy(Item item) {
    	if (Game.player.getGold() < item.getValue()) {
    		System.out.println("Sorry, you can't afford that!");
    		return false;
    	}
    	return true;
    }
    
    
    private boolean canSell(Item item) {
    	if (getGold() < item.getValue()) {
    		System.out.printf("The %s can't afford that!", getName());
    		return false;
    	}
    	return true;
    }
    
    
    private void playerBuy(Item item) {
    	try {
    		Game.player.setGold(-item.getValue());
    		removeItem(item);
    		Game.player.addItem(item);
    		item.setValue(item.getValue()/2);
    		System.out.printf("Added to inventory: %s%n", item.getName());
    		System.out.printf("Gold remaining: %d%n", Game.player.getGold());
    		System.out.printf("The %s thanks you for your patronage.%n", getName());
    		System.out.println();
    	} catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("Buying error!/n");
    	}
    }
    
    
    private void playerSell(Item item) {
    	try {
    		Game.player.setGold(item.getValue());
    		Game.player.removeItem(item);
    		addItem(item);
    		item.setValue(item.getValue()*2);
    		System.out.printf("Removed from inventory: %s%n", item.getName());
    		System.out.printf("Gold: %d%n", Game.player.getGold());
    		System.out.printf("The %s thanks you for your patronage.%n", getName());
    		System.out.println();
    	} catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("Selling error!/n");
    	}
    }
}

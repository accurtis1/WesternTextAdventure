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
    	}
    	catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("I tried to remove an item from the merchant's inventory!");
    	}
    }
    
    
    public Item getItem(int i) {
    	return inventory.get(i - 1);
    }
    
    
    public void trade(String choice) {
    	switch(choice) {
    		case "b":
    			buy();
    			break;
    		case "s":
    			sell();
    			break;
    		default:
    			System.out.println("Not a valid response.\n");
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
    		}
    		if (canBuy(i)) {
    			playerBuy(i);
    			complete = true;
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
    		}
    		if (canSell(i)) {
    			playerSell(i);
    			complete = true;
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
    			for (Item item : Game.player.inventory) {
    				System.out.printf("%d. %s", ++i, item.getName());
    				displayInventory(item);
    			}
    			break;
    		case "merchant":
    			for (Item item : inventory) {
    				System.out.printf("%d. %s", ++i, item.getName());
    				displayInventory(item);
    			}
    			break;
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
			int choice = Integer.parseInt(Game.in.nextLine());
			
			if (choice == 0) {
				return null;
			}
			
    		try {
    			switch(who) {
    				case "from merchant":
    					return getItem(choice);
    				case "for merchant":
    					return Game.player.getItem(choice);
    			}
    		}
    		catch (Exception e) {
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
    	Game.player.setGold(-item.getValue());
    	removeItem(item);
    	Game.player.addItem(item);
    	item.setValue(item.getValue()/2);
    	System.out.printf("Added to inventory: %s%n", item.getName());
    	System.out.printf("Gold remaining: %d%n", Game.player.getGold());
    	System.out.printf("The %s thanks you for your patronage.%n", getName());
    	System.out.println();
    }
    
    
    private void playerSell(Item item) {
    	Game.player.setGold(item.getValue());
    	Game.player.removeItem(item);
    	addItem(item);
    	item.setValue(item.getValue()*2);
    	System.out.printf("Removed from inventory: %s%n", item.getName());
    	System.out.printf("Gold: %d%n", Game.player.getGold());
    	System.out.printf("The %s thanks you for your patronage.%n", getName());
    	System.out.println();
    }
}

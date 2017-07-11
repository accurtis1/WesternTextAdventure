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
    
    public void addItem(Item item) {
    	inventory.add(item);
    }
    
    public Item displayInv() {
    	if (!inventory.isEmpty()) {
	    	System.out.println("Available to buy:");
	    	
	    	for (int i = 0; i < inventory.size(); i++) {
	            Item item = inventory.get(i);
	            if (item.equals(Game.pelletGun)) Game.pelletGun.value = 5;
	            System.out.printf("%d. %s", i + 1, item.name);
	            if (item.value > 0)
	            	System.out.printf(" | %d gold", item.value);
	            if (item.damage > 0)
	            	System.out.printf(" | %d damage", item.damage);
	            if (item.heal > 0)
	            	System.out.printf(" | +%d HP", item.heal);
	            System.out.println();
	    	}
	    	
	    	System.out.println("Choose an item number or 0 to cancel.\n");
	    	
	    	while (true) {
	            try {
	                int pick = Integer.parseInt(Game.in.nextLine());
	                if (pick == 0) return null;
	                return inventory.get(pick - 1);
	            }
	            catch (Exception e) {
	            	System.out.println("Not a valid entry.\n");
	            }
	    	}
    	}
    	else {
    		System.out.printf("The %s has nothing to sell!%n", name);
    		return null;
    	}
    }
}

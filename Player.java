package gameStuff;

import java.util.ArrayList;

public class Player {
	
    // keeping it public because, frankly, I haven't been
	// studious enough to read up on how to use private appropriately
	// and effectively
    public String name;
    public int hp = 100;
    public int gold = 500;
    public int damage = 1;
    
    // quest switches
    // for prompts
    public boolean shadyActive = false;
    public boolean dwarfActive = false;
    public boolean prisonerActive = false;
    // for rewards
    public boolean shadyComplete = false;
    public boolean dwarfComplete = false;
    public boolean prisonerComplete = false;
    
    // first fight switch
    public boolean fought = false;
    // changes prompts after player wins
    public boolean won = false;
    // determines first play/need for help prompt
    public boolean played = false;
    
    ArrayList<Item> inventory = new ArrayList<>();
    ArrayList<Item> healingInventory = new ArrayList<>();
    
    public void setName() {
    	System.out.println("What's your name, partner?\n");
    	name = Game.in.nextLine();
    }
    
    public void help() {
    	System.out.println( "You can use these commands at any time:\n"
    		+ "(i) to view your inventory and stats.\n"
    		+ "(q) to view your active quests.\n"
    		+ "(l) to look around the area you're in.\n"
    		+ "(x) to exit the game.\n"
    		+ "(?) to repeat this message.");
    	if (inventory.contains(Game.map))
    		System.out.println("(m) to view map.");
    	System.out.println();
    }
    
    public void constants(String input, int index) {
        if (input.equals("i")) {
        	Game.player.displayInv();
        }
        else if (input.equals("q")) {
        	Game.player.displayQuest();  
        }
        else if (input.equals("x")) {
        	Game.player.exit();
        }
        else if (input.equals("?")) {
        	Game.player.help();
        }
        else if (input.equals("m")) {
        	if (Game.player.inventory.contains(Game.map)) {
        		Game.player.displayMap(index);
        	}
        	else {
        		System.out.println("Not a valid response.\n");
        		
        	}
        }
    }
    
    public void addItem(Item item) {
    	inventory.add(item);
    	if (item.heal > 0) {
    		healingInventory.add(item);
    	}
    }
    
    public Item setDamage() {
    	// 'best' weapon in inventory
    	Item best = null;
    	// finds weapon with maximum damage
    	for (Item item : inventory) {
    		if (item.damage > damage) {
    			damage = item.damage;
    			best = item;
    		}
    	}
    	// when player has no weapons 
    	if (best == null) {
    		damage = 1;
            return Game.fists;
    	}
    	return best;
    }
    
    public int getDamage() {
    	return damage;
    }
    
    public void heal(Item item) {
    	System.out.printf("You down the %s and feel like a new buckaroo!%n", item.name);
    	System.out.printf("+%d HP%n", Math.min(item.value, 100 - hp));
    	hp = Math.min(100, hp += item.heal);
    	System.out.printf("HP remaining: %d%n", hp);
    	inventory.remove(item);
    }
    
    public void displayQuest() {
    	if (!shadyActive && !dwarfActive && !prisonerActive)
    		System.out.println("No active quests.\n");
    	else {
    		System.out.println("Quests:");
    		if (shadyActive) System.out.println("Collect all the guns in town for the shady guy in the gun shop.");
    		if (dwarfActive) System.out.println("Bring the dwarf in the apothecary a fish 'full of philosophical mercury.'");
    		if (prisonerActive) System.out.println("Bring the prisoner something dskfljslkdjfklsdjfjfkdlsf");
    		System.out.println();
    	}
    }
    
    public void displayInv() {
    	// purely aesthetic
    	String border = " ~~~~~~~~~~~~~~~~~~~~~~~~~~";
    	String walledBorder = "|~~~~~~~~~~~~~~~~~~~~~~~~~~|";
    	int max = walledBorder.length();
    	
    	// filing in the empty space in each box so it's even
    	String items = "| Items:";
    	String gold = "| Gold: " + this.gold;
    	String hp = "| HP: " + this.hp;
    	String weapon = "| (" + setDamage().name + ")";
    	String dmg = "| Damage: " + getDamage();
    	int rest1 = max - items.length() - 1;
    	int rest2 = max - gold.length() - 1;
    	int rest3 = max - hp.length() - 1;
    	int rest4 = max - dmg.length() - 1;
    	int rest5 = max - weapon.length() - 1;
    	String fill1 = new String(new char[rest1]).replace("\0", " ");
    	String fill2 = new String(new char[rest2]).replace("\0", " ");
    	String fill3 = new String(new char[rest3]).replace("\0", " ");
    	String fill4 = new String(new char[rest4]).replace("\0", " ");
    	String fill5 = new String(new char[rest5]).replace("\0", " ");
    	
    	System.out.println(border);
    	System.out.println(items + fill1 + "|");
    	
    	for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            String inv = "| - " + item.name;
            int rest6 = max - inv.length() - 1;
            String fill6 = new String(new char[rest6]).replace("\0", " ");
            System.out.println(inv + fill6 + "|");
    	}
    	
    	System.out.println(walledBorder);
    	System.out.println(gold + fill2 + "|");
    	System.out.println(walledBorder);
    	System.out.println(hp + fill3 + "|");
    	System.out.println(walledBorder);
    	System.out.println(dmg + fill4 + "|");
    	System.out.println(weapon + fill5 + "|");
    	System.out.println(border);
    	System.out.println();
    }
    
    public void displayMap(int index) {
    	System.out.println("          |   ~~~~ |                                                  N         ");
    	System.out.println("          |        |                    ,~~~~~~~~~~~~~~~,             |         ");
    	System.out.println("         /  ~~    /                     |               |         W---X---E     ");
    	System.out.println("        /        /                      |  Dern Tootin' |             |         ");
    	System.out.println("        |       |                       |     Saloon    |             S         ");
    	System.out.println("        |  ~~~  |                       |               |                       ");
    	System.out.println("       /    ~~ /         ,~~~~~~~~~~~~~~+~~~~~~   ~~~~~~+~~~~~~~~~~~~~~~,       ");
    	if (shadyComplete) {
    		System.out.println("      /       /      ----|              |               |               |       ");
    		System.out.println("     / ~~~   /      | XX |   Gun Shop      Town Square      Apothecary  |       ");
    		System.out.println("    /       /        ----|              |               |               |       ");
    	}
    	else {
    		System.out.println("      /       /          |              |               |               |       ");
    		System.out.println("     / ~~~   /           |   Gun Shop      Town Square      Apothecary  |       ");
    		System.out.println("    /       /            |              |               |               |       ");
    	}
    	System.out.println("    |   ~~  |            '~~~~~~~~~~~~~~+~~~~~+   +~~~~~+~~~~~~~~~~~~~~~'       ");
    	System.out.println("    |  ~~~~~|                                 |   |                             ");
    	System.out.println("    |       |                                 `   `                             ");
    	System.out.println("   /~~~~   /   River of                       `   `                             ");
    	System.out.println("  /       /   Riches                  Entrance to Wild Peaks                    ");
    	System.out.println("  |  ~~~  |                                                                     ");
    	System.out.printf("                ~~ You are at the %s ~~%n%n", Game.rooms.get(index));
    }
    
    public Item sellInv() {
    	if (!inventory.isEmpty()) {
	    	System.out.println("Inventory:");
	    	
	    	for (int i = 0; i < inventory.size(); i++) {
	            Item item = inventory.get(i);
	            System.out.printf("%d. %s", i + 1, item.name);
	            if (item.value > 0)
	            	System.out.printf(" | %d gold", (int) item.value/2);
	            if (item.damage > 0)
	            	System.out.printf(" | %d damage", item.damage);
	            if (item.heal > 0)
	            	System.out.printf(" | +%d HP", item.heal);
	            System.out.println();
	    	}
	    	
	    	while (true) {
	            System.out.println("Choose an item number or 0 to cancel.\n");
	            try {
	                int pick = Integer.parseInt(Game.in.nextLine());
	                if (pick == 0) return null;
	            	return inventory.get(pick - 1);
	            }
	            catch (Exception e) {
	            	System.out.println("Not a valid response.");
	            }
	    	}
    	}
    	else {
    		System.out.println("You have nothing to sell!");
    		return null;
    	}
    }
    
    public void chooseHeal() {
    	if (!healingInventory.isEmpty()) {
    		printHealingInventory();
   		}
    }
    
    public void printHealingInventory() {
    	System.out.println("Healing items:");
    	for (int i = 0; i < healingInventory.size(); i++) {
    		Item tempItem = healingInventory.get(i);
    		System.out.printf("%d. %s | %+d HP%n", i + 1, tempItem.name, tempItem.heal);
    	}
    }
    
    public void healInv() {
    	ArrayList<Item> healing = new ArrayList<>();
    	for (Item item : inventory) 
    		if (item.heal > 0)
    			healing.add(item);
    	
    	if (!healing.isEmpty()) {
            System.out.println("Healing items:");
            for (int i = 0; i < healing.size(); i++) {
            	Item it = healing.get(i);
            	System.out.printf("%d. %s | +%d HP%n", i + 1, it.name, it.heal);
            }
            while (true) {
            	System.out.println("Choose an item number or 0 to cancel.\n");
            	int pick = Integer.parseInt(Game.in.nextLine());
            	
            	if (pick == 0) break;
            	
            	try {
                    Item item = healing.get(pick - 1);
                    heal(item);
                    break;
            	}
            	catch (Exception e) {
            	    System.out.println("Not a valid response.");
            	}
            }
    	}
    	else System.out.println("You don't have anything to heal yourself with!");
    }
    
    public void buy(Item item, Merchant merchant) {
    	gold += (-item.value);
    	inventory.add(item);
    	System.out.printf("Added to inventory: %s%n", item.name);
    	System.out.printf("-%d gold%n", item.value);
    	if (item.damage > 0) merchant.inventory.remove(item);
    	if (item.equals(Game.pelletGun)) Game.pelletGun.value = 0;
    	if (merchant.name.equals("bartender")) System.out.println("The bartender gives you a smile as he leaves to assist other patrons.\n");
    }
    
    public void sell(Item item, Merchant merchant) {
    	gold += (item.value/2);
    	inventory.remove(item);
    	merchant.inventory.add(item);
    	System.out.printf("Removed from inventory: %s%n", item.name);
    	System.out.printf("+%d gold%n", item.value/2);
    }
    
    public void exit() {
    	System.out.printf("Get on' outta here %s, you rascal you!", name);
    	System.exit(0);
    }
}

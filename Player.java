package gameStuff;

import java.util.ArrayList;

public class Player {
	
    // keeping it public because, frankly, I haven't been
	// studious enough to read up on how to use private appropriately
	// and effectively
    public String name;
    public int hp = 100;
    public int gold = 500;
    public Item weapon = getWeapon();
    public int damage = weapon.damage;
    
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
    
    
    public String getName() {
    	return name;
    }
    
    
    public void help() {
    	System.out.println( "You can use these commands at any time:\n"
    		+ "(i) to view your inventory and stats.\n"
    		+ "(q) to view your active quests.\n"
    		+ "(l) to look around the area you're in.\n"
    		+ "(x) to exit the game.\n"
    		+ "(?) to repeat this message.");
    	if (inventory.contains(Game.map)) {
    		System.out.println("(m) to view map.");
    	}
    	System.out.println();
    }
    
    
    public void constants(String input, int index) {
    	switch(input) {
    		case "i":
    			displayInv();
    			break;
    		case "q":
    			displayQuests();
    			break;
    		case "x":
    			exit();
    			break;
    		case "?":
    			help();
    			break;
    		case "m":
    			if (inventory.contains(Game.map)) {
    				displayMap(index);
    			}
    			else {
    				System.out.println("Not a valid response.\n");
    			}
    			break;
    		default:
    			System.out.println(Game.oops);
    			System.out.println("I tried to streamline your menu options!\n");
    	}
    }
    
    
    public void addItem(Item item) {
    	inventory.add(item);
    	if (item.heal > 0) {
    		healingInventory.add(item);
    	}
    }
    
    
    public void removeItem(Item item) {
    	try {
    		inventory.remove(item);
    		if (item.heal > 0) {
    			healingInventory.remove(item);
    		}
    	}
    	catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("I tried to remove an item from your inventory!\n");
    	}
    }
    
    
    public Item getItem(int choice) {
    	return inventory.get(choice - 1);
    }
    
    
    public void setWeapon() {
    	for (Item item : inventory) {
    		if (item.damage > 1) {
    			weapon = item;
    		}
    		else {
    			weapon = Game.fists;
    		}
    	}
    }
    
    
    public Item getWeapon() {
    	setWeapon();
    	return weapon;
    }
    
    
    public int getDamage() {
    	return damage;
    }
    
    
    public void setHp(int amount) {
    	if (amount > 0) {
    		hp = Math.min(hp += amount, 100);
    	}
    	else {
    		hp = Math.max(hp += amount, 0);
    	}
    }
    
    
    public int getHp() {
    	return hp;
    }
    
    
    public int getHpBenefit(int amount) {
    	return Math.min(amount, 100 - hp);
    }
    
    
    public int getHpDetriment(int amount) {
    	return Math.min(amount, hp - amount);
    }
    
    
    public void setGold(int amount) {
    	gold += amount;
    }
    
    
    public int getGold() {
    	return gold;
    }
 
    
    // ------ healing -------
    public void heal() {
    	if (healConditions()) {
    		printHealingInventory();
    		chooseHeal();
    	}
    }


    private boolean healConditions() {
    	if (hp == 100) {
    		System.out.println("Your ticker is running just fine, no need to waste precious supplies!");
    		return false;
    	}
    	if (healingInventory.isEmpty()) {
    		System.out.println("You have nothing to heal yourself with!");
    		return false;
    	}
    	return true;
    }


    private void printHealingInventory() {
    	System.out.println("Healing items:");
    	for (int i = 0; i < healingInventory.size(); i++) {
    		Item tempItem = healingInventory.get(i);
    		System.out.printf("%d. %s | %+d HP%n", i + 1, tempItem.name, tempItem.heal);
    	}
    }


    private void chooseHeal() {
    	while(true) {
	    	System.out.println("Choose an item number or 0 to cancel.\n");
	    	int pick = Integer.parseInt(Game.in.nextLine());
	    	
	    	if (pick == 0) break;
	    	try {
	    		Item tempItem = healingInventory.get(pick - 1);
	    		applyHeal(tempItem);
	    		break;
	    	}
	    	catch (Exception e) {
	    		System.out.println("Not a valid response.");
	    	}
    	}
    }


    private void applyHeal(Item item) {
    	System.out.printf("You down the %s and feel like a new buckaroo!%n", item.name);
    	System.out.printf("+%d HP%n", getHpBenefit(item.value));
    	System.out.printf("HP remaining: %d%n", getHp());
    	removeItem(item);
    }
    // ------- /healing ---------
    
    
    public void displayInv() {
    	String border = " ~~~~~~~~~~~~~~~~~~~~~~~~~~";
    	String wallBorder = "|~~~~~~~~~~~~~~~~~~~~~~~~~~|";
    	int max = wallBorder.length();
    	
    	// making each box an even length
    	// initial strings
    	String items = "| Items:";
    	String gold = "| Gold: " + getGold();
    	String hp = "| HP: " + getHp();
    	String weapon = "| (" + getWeapon() + ")";
    	String dmg = "| Damage: " + getDamage();
    	// the 'rest' of the empty space
    	int rest1 = max - items.length() - 1;
    	int rest2 = max - gold.length() - 1;
    	int rest3 = max - hp.length() - 1;
    	int rest4 = max - dmg.length() - 1;
    	int rest5 = max - weapon.length() - 1;
    	// the 'filler' - I suspect I can do this iteratively, will explore
    	String fill1 = new String(new char[rest1]).replace("\0", " ");
    	String fill2 = new String(new char[rest2]).replace("\0", " ");
    	String fill3 = new String(new char[rest3]).replace("\0", " ");
    	String fill4 = new String(new char[rest4]).replace("\0", " ");
    	String fill5 = new String(new char[rest5]).replace("\0", " ");
    	
    	// actual construction!
    	System.out.println(border);
    	System.out.println(items + fill1 + "|");
    	
    	for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            // initial string
            String inv = "| - " + item.name;
            // rest of empty space
            int rest6 = max - inv.length() - 1;
            // filler
            String fill6 = new String(new char[rest6]).replace("\0", " ");
            System.out.println(inv + fill6 + "|");
    	}
    	
    	System.out.println(wallBorder);
    	System.out.println(gold + fill2 + "|");
    	System.out.println(wallBorder);
    	System.out.println(hp + fill3 + "|");
    	System.out.println(wallBorder);
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
    
    public void displayQuests() {
    	if (!shadyActive && !dwarfActive && !prisonerActive) {
    		System.out.println("No active quests.\n");
    	}
    	else {
    		System.out.println("Quests:");
    		if (shadyActive) {
    			System.out.println("Collect all the guns in town for that shady guy in the Gun Shop.");
    		}
    		if (dwarfActive) {
    			System.out.println("Bring the dwarf in the Apothecary a fish 'full of philosophical mercury.'");
    		}
    		if (prisonerActive) {
    			System.out.println("Bring the prisoner something dskfljslkdjfklsdjfjfkdlsf");
    		}
    		System.out.println();
    	}
    }
    
    
    public void exit() {
    	System.out.printf("Get on' outta here %s, you rascal you!", getName());
    	System.exit(0);
    }
}

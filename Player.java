package gameStuff;

import java.util.ArrayList;

public class Player {
	
    private String name;
    private int hp = 100;
    private int gold = 500;
    private Item weapon;
    private int damage;
    
    private boolean shadyActive = false;
    private boolean dwarfActive = false;
    private boolean prisonerActive = false;

    private boolean shadyComplete = false;
    private boolean dwarfComplete = false;
    private boolean prisonerComplete = false;
    
    // triggers gift of boots
    private boolean fought = false;
    // *IMPLEMENT* triggers gift of ???
    // also changes room prompts
    private boolean won = false;
    
    // they were clogging up the Game file
    String easyDefeat = "You're nearly laughing as you strut away. That guy was a joke.";
    String mediumDefeat = "Your adrenaline is still pumping despite being out of harm's way. You were made to be an outlaw!";
    String hardDefeat = "You walk away from the fight, rather dazed, and try to orient yourself.";
    String bossFlee = "Your heart is fluttering like a jackrabbit in the August heat. You can't believe you escaped with your life!";
    
    String strikeNull = "You both fire your weapons but the bullets bite the dust.";
    String fleeNull = "As you turn your back to flee you nearly jump out of your socks - your rival just shot a bullet straight through your hat! Talk about a hat trick!";
    
    String deathMessage = "\nAlas, it seems the Wild West got the best of you this time...\nIsn't death a bitch? Would you like to be resurrected (y/n)?\n";
    
    String bootsMessage = "\nYou can't help but notice that the now-incapacitated cowboy has some real fancy boots on those feet...\n"
                		+ "You try them on and - lo and behold - they fit!!\n"
                		+ "Custom boots added to inventory -- all prices lowered by 10%\n";
    
    String shadyStart = "The shady guy says something to you about how he wants a bunch of guns.";
    String dwarfStart = "The dwarf says something to you about mercury from a rare fish for a philosopher's stone.";
    String prisonerStart = "The prisoner begs you for help. Idk what she did or what she needs yet but whatever.";
    
    String questQuestion = "Do you accept this quest (y/n)?\n";
    String questDecline = "You politely decline and return to your business.\n";
    
    ArrayList<Item> inventory = new ArrayList<>();
    ArrayList<Item> healingInventory = new ArrayList<>();
    ArrayList<Item> sellingInventory = new ArrayList<>();
    
    public Player() {
    	addItem(Game.pelletGun);
    	weapon = Game.fists;
    	damage = getDamage();
    }
    
    
    public void setName() {
    	System.out.println("What's your name, partner?\n");
    	name = Game.in.nextLine();
    }
    
    
    public String getName() {
    	return name;
    }
    
    
    // --- various game status switches ---
    public void setWon(boolean status) {
    	won = status;
    }
    
    
    public boolean getWon() {
    	return won;
    }
    
    
    public void setFought(boolean status) {
    	fought = status;
    }
    
    
    public boolean getFought() {
    	return fought;
    }
    
    
    public void setShadyActive(boolean status) {
    	shadyActive = status;
    }
    
    
    public boolean getShadyActive() {
    	return shadyActive;
    }
    
    
    public void setDwarfActive(boolean status) {
    	dwarfActive = status;
    }
    
    
    public boolean getDwarfActive() {
    	return dwarfActive;
    }
    
    
    public void setPrisonerActive(boolean status) {
    	prisonerActive = status;
    }
    
    
    public boolean getPrisonerActive() {
    	return prisonerActive;
    }
    
    
    public void setShadyComplete(boolean status) {
    	shadyComplete = status;
    }
    
    
    public boolean getShadyComplete() {
    	return shadyComplete;
    }
    
    
    public void setDwarfComplete(boolean status) {
    	dwarfComplete = status;
    }
    
    
    public boolean getDwarfComplete() {
    	return dwarfComplete;
    }
    
    
    public void setPrisonerComplete(boolean status) {
    	prisonerComplete = status;
    }
    
    
    public boolean getPrisonerComplete() {
    	return prisonerComplete;
    }
    // ------ /status switches -------
    
    
    // ----- player attributes -----
    public int getHp() {
    	return hp;
    }
    
    // for items
    public int getHpBenefit(int amount) {
    	return Math.min(amount, 100 - hp);
    }
    
    // for items
    public int getHpDetriment(int amount) {
    	return Math.min(amount, hp - amount);
    }
    
    
    public void setGold(int amount) {
    	if (amount < 0) {
    		gold = Math.max(0,  gold += amount);
    	} else {
    		gold += amount;
    	}
    }
    
    
    public int getGold() {
    	return gold;
    }
    
    public String displayGold() {
    	return String.format("Your gold: %d", getGold());
    }
    
    
    public void addItem(Item item) {
    	try {
    		inventory.add(item);
    		if (item.getHeal() > 0) {
        		healingInventory.add(item);
        	}
    		if (item.getValue() > 0) {
        		sellingInventory.add(item);
        	}
    	} catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("I tried to add an item to your inventory!");
    	}
    	
    }
    
    
    public void removeItem(Item item) {
    	try {
    		inventory.remove(item);
    		if (item.getHeal() > 0) {
    			healingInventory.remove(item);
    		}
    		if (item.getValue() > 0) {
    			sellingInventory.remove(item);
    		}
    		if (item == weapon) {
    			weapon = Game.fists;
    		}
    	} catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("I tried to remove an item from your inventory!\n");
    	}
    }
    
    
    public Item getItem(int choice) {
    	return sellingInventory.get(choice - 1);
    }
    
    
    private void setWeapon() {	
    	for (Item item : inventory) {
    		if (item.getDamage() > weapon.getDamage()) {
    			weapon = item;
    		}
    	}
    }
    
    
    public Item getWeapon() {
    	setWeapon();
    	return weapon;
    }
    
    
    private void setDamage() {
    	damage = getWeapon().getDamage();
    }
    
    
    public int getDamage() {
    	setDamage();
    	return damage;
    }
    
    
    public void setHp(int amount) {
    	if (amount > 0) {
    		hp = Math.min(hp += amount, 100);
    	} else {
    		hp = Math.max(hp += amount, 0);
    	}
    }
    // ----- /player attributes -----
 
    
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
	    	
	    	if (pick == 0) {
	    		break;
	    	}
	    	
	    	try {
	    		Item tempItem = healingInventory.get(pick - 1);
	    		applyHeal(tempItem);
	    		break;
	    	} catch (Exception e) {
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
    
    
    // ----- misc ------
    public void activateBoots() {
    	for (Item item : Game.items) {
    		// this way it doesn't decrease the value of an item the player already bought
    		if (!inventory.contains(item)) {
    			item.setValue((int) (item.getValue() * 0.9));
    		}
    	}
    }
    // ----- /misc -----
    
    
    // ----- display -----
    public void displayInv() {
    	String border = " ~~~~~~~~~~~~~~~~~~~~~~~~~~";
    	String wallBorder = "|~~~~~~~~~~~~~~~~~~~~~~~~~~|";
    	int max = wallBorder.length();
    	
    	// making each box an even length
    	// initial strings
    	String items = "| Items:";
    	String gold = "| Gold: " + getGold();
    	String hp = "| HP: " + getHp();
    	String weapon = "| (" + getWeapon().getName() + ")";
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
    	} else {
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
    	} else {
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
    				System.out.println(Game.no);
    			}
    			break;
    		// duct tape
    		case "":
    			System.out.println(Game.no);
    			break;
    		default:
    			System.out.println(Game.oops);
    			System.out.println("I tried to streamline your menu options!\n");
    	}
    }
    // ------ /display ------
    
    
    public void exit() {
    	System.out.printf("Get on' outta here %s, you rascal you!", getName());
    	System.exit(0);
    }
}

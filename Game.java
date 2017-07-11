package gameStuff;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    // Stream of Consciousness Ideas and Notes:
	
    // More items in mage's shop
    // teleporter - teleports you to any location
    // medic kit - potions and mead heal 50% more
    
    // Dwarf at the apothecary
    // Quest ~ help wizard create a philosopher's stone*
    // Solution ~ philosophical mercury - King Mackerel in the river
    // Reward ~ convert one of your items to (random amount) gold
	// *copyright? or just too unoriginal?
    
    // Execution in the town square
    // Quest ~ save the condemned
    // Solution ~ some kind of explosion - ??
    // Reward ~ coward's cloak - always flee w/o taking damage
    
	
	
    // TASKS:
	// ~~~~~set gold to 0~~~~~
    // do everything in HiddenRoom
    // write methods in GunShop for shady guy
    // write quest methods in game
    // implement the rest of the npcs/quests
    // select requirement? for prisoner quest
    // implement crazy item for winning
	// FORMATTING/REFACTORING DAMNIT
    // fix entry messages for command clarity
    // saloon say 'fight as many cowboys to your heart's
    //   content' or some bullshit
	// give teleporter functionality
	// 
	// create a method to index the rooms recursively or something instead of typing each of them out
	// ?create empty spaces in inventory via iteration?
	// create getters/setters for items and their shit and fix everything
	// PRIVATIZE everything's shit after getters and setters have been created
	// make sure the indexing in the trade menus work correctly
	
	
	
	
	// ********( Change Log )********
	
	// 02/07/2017 - 04/03/2017
	//    - didn't think of starting a change log, but now I see its usefulness

	// 04/04/2017
	//    - attempted ideas for items (teleporter, medic kit, magic stone, custom boots)
	//    - player wins boots on first cowboy kill
	//    - boots decrease prices by 10%
	//    - DRY practice w/ constants method in Player
	//    - added map & functionality
	//	  - got rid of 'flower' item
	//    - added 'fists' to cover issue of having no weapons
	//    - TONS o' formatting changes
	//    - started prisoner quest
	//    - added 'rooms' to Game and 'index' to Room so map shows location
	//    - covered issue of player/merchant not having any items
	//    - error exception handling for player.sellInv()

	// 04/05/2017
	//    - some prisoner quest stuff
	//    - added 'teleporter' to item list and wizard inventory
	//    - changed the conditions under which items show up in sell menus to prevent irrelevant information
	//    - removed erroneous border in inventory display

	// 07/11/2017
	//    - good to be back!
	//    - refactored player.inventory and fixed a long-standing bug
	//    - refactored player.setDamage
	//    - refactored player.getDamage
	//    - refactored player.setName
	//    - created addItem() for player and merchant
	//    - created npc.addRequirement()
	//    - created player.healingInventory
	//    - refactored player.heal()
	// 	  - committed to GitHub!
	//    - created player.getName()
	//    - refactored player.constants() to switch instead of ifs
	//    - created player.weapon
	//    - refactored player.damage
	//    - created player.setWeapon()
	//    - created player.getWeapon()
	//    - removed player.setDamage()
	//    - removed player.getDamage()
	//    - changed around function placement in Player
	//    - tried private functions for the first time (!!!) with the healing methods in Player
	//    - gigantic refactor to the trading feature
	//    - like seriously ^that was ridiculous
	//    - switched ALL of the room decision trees to switch statements!
	//    - added static Strings for wrong turns and invalid responses!!!
	//    - distributed functions in rooms instead of cramming everything into entry()
	//    - actually basically just refactored the shit out of all the rooms
	//    - refactored River's steaming pile of crap that I called a function
	
	
	// --------- initializing ---------
    static Scanner in = new Scanner(System.in);
    
    static HashMap<String, Room> roomObjects = new HashMap<>();
    static ArrayList<Item> items = new ArrayList<>();
    // used to index rooms for the map
    static ArrayList<String> rooms = new ArrayList<>();
    static Player player = new Player();
    
    // constants used for menus
    static String cons = "iqx?m";
    // admission of guilt for bugs
    // if anyone ever actually plays, the description that follows the 'oops' will help!
    static String oops = "Uh oh, developer error!";
    // I needed these so long ago
    static String no = "Not a valid response.\n";
    static String wrongTurn = "Whoops, can't go that way!\n";

    
    
    // does this work static?
    static Random random = new Random();
    
    
    
    
    //  --------- items (name, value, damage, hp) ---------
    // weapons
    static Item fists = new Item("fists", 0, 1, 0);
    static Item pelletGun = new Item("pellet gun", 10, 2, 0);
    static Item bbGun = new Item ("BB gun", 20, 4, 0);
    static Item pistol = new Item (".44 Magnum pistol", 40, 6, 0);
    static Item revolver = new Item(".38 Special revolver", 60, 8, 0);
    static Item rifle = new Item (".68 Remington rifle", 80, 10, 0);
    static Item shotgun = new Item("16 gauge shotgun", 100, 12, 0);
    // other buy-ables
    static Item mead = new Item("mead", 10, 0, 4);
    static Item potion = new Item("healing potion", 50, 0, 25);
    // misc. items
    static Item rubberDuck = new Item("rubber duck", 0, 0, 0);
    static Item redHerring = new Item("red herring", 0, 0, 0);
    static Item kingMackerel = new Item("king mackerel", 0, 0, 0);
    static Item map = new Item("map", 0, 0, 0);
    // special items
    static Item customBoots = new Item("custom cowboy boots", 0, 0, 0);
    static Item teleporter = new Item("teleporter", 100, 0, 0);
    
    
    
    
    //  -------- merchants -------
    // name, gold
    static Merchant bartender = new Merchant("bartender", 100);
    static Merchant wizard = new Merchant("wizard", 200);
    static Merchant armsDealer = new Merchant("arms dealer", 300);
    
    
    
    
    //  -------- enemies ----------
    // name, gold, hp, damage
    static Enemy easy = new Enemy("a scrawny cowboy", 10, 8, 5);
    static Enemy medium = new Enemy("a burly cowboy", 20, 15, 8);
    static Enemy hard = new Enemy("a massive cowboy", 30, 25, 10);
    static Enemy boss = new Enemy("the baddest outlaw", 0, 50, 15);
    
    
    
    
    //  --------- npcs ---------
    static Npc shadyGuy = new Npc("shady guy");
    static Npc dwarf = new Npc("dwarf");
    static Npc prisoner = new Npc("prisoner");
    
    
    
    
    
    public static void main(String[] args) {	

    	//  ---------- rooms ----------
    	// name, index, items, merchants, npcs
    	Entry entry = new Entry("entry", 0);
    	roomObjects.put("entry", entry);
    	rooms.add("Base of Wild Peaks");
    	River river = new River("river", 1);
    	roomObjects.put("river", river);
    	rooms.add("River of Riches");
    	TownSquare townSquare = new TownSquare("town square", 2, prisoner);
    	roomObjects.put("town square", townSquare);
    	rooms.add("Town Square");
    	GunShop gunShop = new GunShop("gun shop", 3, armsDealer, shadyGuy);
    	roomObjects.put("gun shop", gunShop);
    	rooms.add("Gun Shop");
    	Saloon saloon = new Saloon("saloon", 4, bartender);
    	roomObjects.put("saloon", saloon);
    	rooms.add("Saloon");
    	Apothecary apothecary = new Apothecary("apothecary", 5, wizard, dwarf);
    	roomObjects.put("apothecary", apothecary);
    	rooms.add("Apothecary");
    	HiddenRoom hiddenRoom = new HiddenRoom("hidden room", 6);
    	roomObjects.put("hidden room", hiddenRoom);
    	rooms.add("??????");
    	
    	
    	// ------ populating river ------
    	river.addItem(redHerring);
    	river.addItem(kingMackerel);
    	river.addItem(rubberDuck);
    	river.addItem(bbGun);
    	
    	//  ----- populating shops ------
    	armsDealer.addItem(pistol);
    	armsDealer.addItem(revolver);
    	armsDealer.addItem(rifle);
    	armsDealer.addItem(shotgun);
   
    	bartender.addItem(mead);
    	
    	wizard.addItem(potion);
    	wizard.addItem(teleporter);
    	
    	
    	//  ------ populating npcs ------
    	shadyGuy.requirement.add(pelletGun);
    	shadyGuy.requirement.add(pistol);
    	shadyGuy.requirement.add(revolver);
    	shadyGuy.requirement.add(rifle);
    	shadyGuy.requirement.add(shotgun);
    	shadyGuy.requirement.add(bbGun);
    	
    	dwarf.addRequirement(kingMackerel);
    	
    	
    	//  --- the trusty pellet gun! ---
    	player.addItem(pelletGun);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//  ------ AND SO IT BEGINS -------
    	System.out.println("Welcome to the Wild West! You're in a world where the dreams"
    		+ " are big and the guns are bigger.\nThere will be enemies to defeat, gold to discover,"
    		+ " quests to complete, and guns to upgrade.\nMaybe some day you could defeat"
    		+ " the boss cowboy and become the new leader of the town...");
    	player.setName();
    	System.out.printf("Howdy %s!%n", player.getName());
    	System.out.println("I see you're new to these here parts. Let me give you a run down.\n"
		+ "Actions are simple; just type any letter in the (parentheses) to continue.\n");
    	player.help();
    	System.out.println("Are ya ready to have a hog-killin' time (y/n)?\n");
    	
    	while (true) {
    	    String input = in.nextLine();
    	    
    	    if (input.equals("y"))
    	    	break;
    	    else if (input.equals("n")) {
    	    	System.out.printf("You know what %s? This town wasn't big enough for you anyway."
    				+ " Don't let me catch you near these parts again!", player.getName());
        		System.exit(0);
    	    }
    		else
    			System.out.println("Not a valid response.\n");
    	}
    	
    	// Time to play!
    	// Game engine is located in the 'Room' file
    	
    	Room start = new Room("", 0);
    	start.playEngine();
    }
    
    
    
    
    
    
    
    
    
    
    //  ----------- METHODS -----------
    
    public String enemySelection(String input) {
    	
    	if (input.equals("f")) {
            int a = random.nextInt(3);
            
            if (a == 1) {
            	attackPlayer(hard);
            	System.out.println("You walk away from the fight, rather dazed, and try to orient yourself.");
            }
            else if (a == 2) {
            	attackPlayer(medium);
            	System.out.println("Your adrenaline is still pumping despite being out of harm's way. You were made to be an outlaw!");
            }
            else {
            	attackPlayer(easy);
            	System.out.println("You're nearly laughing as you strut away. That guy was a joke.");
            }
    	}
    	else if (input.equals("o")) {
            attackPlayer(boss);
            if (player.won)
            	return "saloon";
            System.out.println("Your heart is fluttering like a jackrabbit in the August heat. You can't believe you escaped with your life!");
    	}
    	
    	System.out.println("From here you can either return to the (s)aloon or go south to the (t)own square.\n");
    	
    	while (true) {
            String d = in.nextLine();
            
            if (d.equals("s"))
            	return "saloon";
            else if (d.equals("t"))
            	return "town square";
            else 
            	System.out.println("Please either return to the (s)aloon or go to the (t)own square.\n");
    	}
    }
    
    
    
    
    
    
    
    // fight method that I probably need to refactor but it works for now
    // I wanted it to be completely random whether the player or enemy gets hit instead of having
    // the two damages face off and whoever's was highest got to attack the other. I also changed
    // the enemy's damage so that it spans a smaller but higher range (i.e. 7-10 instead of 1-10)
    public void attackPlayer(Enemy enemy) {
    	
    	// needed to determine player's best weapon and therefore damage
    	player.getDamage();
    	System.out.printf("You and %s step outside the saloon, ready for a shootout.%n", enemy.name);
    	// I had to do this so the hp would reset every time, otherwise the cowboy stays dead,
    	// and I wanted replayability so you can make money this way (even though it's very
    	// boring and - for all intents and purposes - has no true replayability)
    	int hp = enemy.hp;
    	
    	while (player.hp > 0 && hp > 0) {
            System.out.println("Do you want to (s)trike, (f)lee, or (h)eal?\n");
            String input = in.nextLine();
            
            int edam = (int) (Math.random() * enemy.damage + (enemy.damage - 3));
            int rand = random.nextInt(5);
            // boolean found (for special items)
            
            // ------- strike --------
            if (input.equals("s")) {
            	System.out.printf("You and %s draw your weapons and fire your shots.%n", enemy.name);
            	
            	// for item in player inventory       // or inventory contains item/element/whatever??
            	// if item in inventory - battle consequences
            	// item found
            	// remove item -or- uses/turns +1
            	// else pass
            	
            	if (rand == 0 || rand == 1) {
            		hp -= player.damage;
            		System.out.printf("You hit him! -%d HP%nRemaining enemy HP: %d%n", player.damage, Math.max(0, hp));
            	}
            	else if (rand == 2 || rand == 3) {
            		player.hp -= edam;
            		System.out.printf("You've been hit! -%d HP%nRemaining HP: %d%n", edam, Math.max(0, player.hp));
            	}
            	else
            		System.out.println("You both fire your weapons but the bullets bite the dust.");
            }
            
            // -------- flee ---------
            else if (input.equals("f")) {
            	System.out.println("You attempt to scurry away...");
            	
            	if (rand == 0 || rand == 1)
            		System.out.printf("Your competitor fires at you and just barely misses.%n", enemy.name);
            	else if (rand == 2 || rand == 3) {
            		player.hp -= edam;
            		System.out.printf("Your adversary shoots wildly and without abandon, grazing your side.%n-%d HP%nHP remaining: %d%n", edam, Math.max(0, player.hp));
            	}
            	else
            		System.out.println("As you turn your back to flee you nearly jump out of your socks - your rival just shot a bullet straight through your hat! Talk about a hat trick!");
            	break;
            }
            
            
            //
            
            //
            
            //
            
            // -------- heal ---------
            else if (input.equals("h")) {
            	if (player.hp >= 100)
            		System.out.println("Your ticker is running just fine, no need to waste precious supplies!");
            	else
            		player.heal();
            }
            else if (cons.contains(input))
            	Game.player.constants(input, 4);
            else
            	System.out.println("Not a valid response.");
    	}
    	
    		//
    	
    		//
    	
    		//
    	
    		//
    	
    	// ------- player death --------
    	if (player.hp <= 0) {
            System.out.println("\nAlas, it seems the Wild West got the best of you this time...\nIsn't death a bitch? Would you like to be resurrected (y/n)?\n");
            while (true) {
            	String die = in.nextLine();
            	
            	if (die.equals("n")) {
            		System.out.println("See ya partner!");
            		System.exit(0);
            	}
            	else if (die.equals("y")) {
            		System.out.println("Here we go!");
            		player.hp = 100;
            		break;
            	}
            	else
            		System.out.println("Not a valid response.\n");
            }
    	}
    	
    	// ------- player victory --------
    	else if (hp <= 0) {
    		// --------- boss victory --------
            if (enemy == boss) {
            	System.out.println("You emerge victorious! Congratulations,"
            			+ " you're the toughest sharpshooter in the Old West!\n"
            			+ "You go down in history as the most fearsome fighter that"
            			+ " has ever graced these dirt roads. Yee haw!");
            	System.out.println("Would you like to continue playing (y/n)?\n");
            	
            	while (true) {
                    String p = in.nextLine();
                    
                    if (p.equals("y")) {
                    	
                    	// give player some crazy item here
                    	
                    	System.out.println("You turn back to the saloon, keen on keeping the adventure alive.");
                    	player.won = true;
                    	break;
                    }
                    else if (p.equals("n"))
                    	player.exit();
                    else
                    	System.out.println("Not a valid response.\n");
            	}
            }
            // -------- regular victory ---------
            else {
            	System.out.printf("You emerge victorious! You take %d gold from the cowboy's pockets.%n", enemy.gold);
            	player.gold += enemy.gold;
            	if (!player.fought) {
                    System.out.println("\nYou can't help but notice that the now-incapacitated cowboy has some real fancy boots"
                    		+ " on them feet...\nYou try them on and - lo and behold - they fit!!\nCustom boots added to inventory"
                    		+ " -- all prices lowered by 10%\n");
                    player.fought = true;
                    player.addItem(customBoots);
                    boots();
            	}
            }
    	}
    }
    
    
    
    
    
    // one-time method used when the player acquires the custom boots
    // I could've prob just put this up there ^^ in the code block
    // where the player gets the boots...but it seemed appropriate to
    // separate it
    public void boots() {
    	for (Item item : items) {
    		// this way it doesn't decrease the value of an item the player already bought
    		if (!player.inventory.contains(item))
    			item.value = (int) (item.value * 0.9);
    	}
    }
    
    
    public void trade(Merchant merchant) {
    	System.out.println("Would you like to (b)uy, (s)ell, or (c)ancel?\n");
        
    	while (true) {
            String choice = in.nextLine();
            
            if (choice.equals("c")) {
            	System.out.printf("You turn away from the %s and back to the room.%n", merchant.getName());
            	System.out.println();
            	break;
            }
            merchant.trade(choice);
            break;
    	}
    }
    
    
    

    //  ----------- QUESTS ---------------
    
    // it would've been too boring to have a cookie-cutter quest method,
    // and I don't want each room w/ an NPC to be cluttered up with all the
    // dialogue and shit, so each NPC will have their own quest method here

    public void questSelection(Npc npc) {
	if (npc == shadyGuy)
		System.out.println("The shady guy says something to you about how he wants"
		    + " a bunch of guns.");
	else if (npc == dwarf)
		System.out.println("The dwarf says something to you about mercury from a"
		    + " rare fish for a philosopher's stone.");
	else if (npc == prisoner)
		System.out.println("The prisoner begs you for help. Idk what she did or what"
		    + " she needs yet but w/e");
	
	System.out.println("Do you accept this quest (y/n)?\n");
	
	while (true) {
	    String input = in.nextLine();
	    
	    if (input.equals("y")) {
	    	if (npc == shadyGuy) {
	    		player.shadyActive = true;
	    		shadyQuest();
	    	}
	    	else if (npc == dwarf) {
	    		player.dwarfActive = true;
	    		dwarfQuest();
	    	}
	    	else if (npc == prisoner) {
	    		player.prisonerActive = true;
	    		prisonerQuest();
	    	}
	    	break;
	    }
	    else if (input.equals("n")) {
	        System.out.println("You politely decline and return to your business.\n");
	        break;
	    }
	    else
	    	System.out.println("Not a valid response.\n");
	    }
    }
    
    
    
    
    
    
    public void shadyQuest() {
    	if (!shadyGuy.meetsRequirement()) {
    		System.out.println("The shady character checks your rucksack.\n'You don't have 'em all son!"
    				+ " Come back when your collection is complete.'");
    		if (shadyGuy.almostShady()) {
    			System.out.println("There must be a gun somewhere that the arms dealer doesn't sell...");
    		}
    		System.out.println();
    	}
    	
    	else {
    		System.out.println("'You got 'em!' he bellows. 'Now you're startin' to look like a real cowboy. I think you're"
    				+ " finally worthy of this...'\nHe walks to the counter and whispers something in the arms dealer's ear."
    				+ " The dealer smiles at you and hands you a key.\n'Alright partner, we only let the best of the best"
    				+ " take this chance, and you've proven yourself,' he says.\nDespite your awful confusion, you feel pretty"
    				+ " proud. You now have access to the (h)idden room in the Gun Shop.\n");
    		player.shadyComplete = true;
    	}
    }
    
    
    
    
    
    public void dwarfQuest() {
    	System.out.println("Bitch.");
    }
    
    
    
    
    
    
    public void prisonerQuest() {
    	// System.out.println("Idk wtf to do D': secret location maybe? tells you about a cabin she lives in to the east"
    	//		+ " of the entrance but idk what you have to do there...how to search for something? ooh maybe set up"
    	//		+ " a battleship thing, but that might be too hard....fun though....???");
    	if (!prisoner.meetsRequirement())
    		System.out.println("The prisoner looks at you incredulously. 'What are you doing?' she whispers. 'We don't"
    				+ " have this kind of time! Go get that cloak!'\n");
    	if (prisoner.meetsRequirement()) {
    		System.out.println("You throw the - bomb or somethng idk I started this sentence 3 months ago");
    	}
    }
    
    public void battleWagons() {
    	// still not sure I"m gonna do this
    }
}

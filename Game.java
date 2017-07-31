package gameStuff;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
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
    // write quest methods in game
    // implement the rest of the npcs/quests
    // select requirement? for prisoner quest
    // implement crazy item for winning
	// FORMATTING/REFACTORING DAMNIT
	// give teleporter functionality
	// write prompts for shadyguy
	// and the rest of the quests while you're at it
	
	
	
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
	//    - created player.weapon getter/setter
	//    - removed player.damage getter/setter
	//    - changed around function placement in Player
	//    - tried private functions for the first time (!!!) with the healing methods in Player
	//    - gigantic refactor to the trading feature
	//    - like seriously ^that was ridiculous
	//    - switched ALL of the room decision trees to switch statements!
	//    - added static Strings for wrong turns and invalid responses!!!
	//    - distributed functions in rooms instead of cramming everything into entry()
	//    - actually basically just refactored the shit out of all the rooms
	//    - refactored River's steaming pile of crap that was entry()
	
	// 07/12/2017
	//    - I think I fixed most of the trade bugs
	//    - basically refactored the entirety of Game
	
	// 07/31/2017
	//    - gold is displayed during a trade
	//    - fixed error when a non-integer is chosen during trade
	//    - fixed formatting for striking and fleeing a fight
	//    - fixed returning to world after fight
	//    - did a lot with how weapons are set
	//    - added item.special
	//    - removed item.special
	//    - added player.sellingInventory
	//    - my ADD kicked in, lots of frivolous formatting
	//    - ruined formatting
	//    - implemented hidden room functionality
	//    - damn near finished shady quest
	
	
	
	// --------- initializing ---------
    static Scanner in = new Scanner(System.in);
    
    // constants used for menus
    static String cons = "iqx?m";
    // admission of guilt for bugs
    // if anyone ever actually plays, the description that follows the 'oops' will help!
    static String oops = "Uh oh, developer error!";
    // I needed these so long ago
    static String no = "Not a valid response.\n";
    static String wrongTurn = "Whoops, can't go that way!\n";
    
    static HashMap<String, Room> roomObjects = new HashMap<>();
    static ArrayList<Item> items = new ArrayList<>();
    // used to index rooms for the map
    static ArrayList<String> rooms = new ArrayList<>();
    
    // used for rolls throughout game
    static Random random = new Random();
    
    private static int numOfRooms = 0;
    
    
    //  --------- items (name, value, damage, heal) ---------
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

    
    //  -------- player! --------
    static Player player = new Player();

    
    public static void main(String[] args) {	
    	//  ---------- rooms ----------
    	// name, index, items, merchants, npcs
    	Entry entry = new Entry("entry", roomIndex());
    	roomObjects.put("entry", entry);
    	rooms.add("Base of Wild Peaks");
    	River river = new River("river", roomIndex());
    	roomObjects.put("river", river);
    	rooms.add("River of Riches");
    	TownSquare townSquare = new TownSquare("town square", roomIndex(), prisoner);
    	roomObjects.put("town square", townSquare);
    	rooms.add("Town Square");
    	GunShop gunShop = new GunShop("gun shop", roomIndex(), armsDealer, shadyGuy);
    	roomObjects.put("gun shop", gunShop);
    	rooms.add("Gun Shop");
    	Saloon saloon = new Saloon("saloon", roomIndex(), bartender);
    	roomObjects.put("saloon", saloon);
    	rooms.add("Saloon");
    	Apothecary apothecary = new Apothecary("apothecary", roomIndex(), wizard, dwarf);
    	roomObjects.put("apothecary", apothecary);
    	rooms.add("Apothecary");
    	HiddenRoom hiddenRoom = new HiddenRoom("hidden room", roomIndex());
    	roomObjects.put("hidden room", hiddenRoom);
    	rooms.add("??????");
    	
    
    	//  ----- populating shops ------
    	armsDealer.addItem(pistol);
    	armsDealer.addItem(revolver);
    	armsDealer.addItem(rifle);
    	armsDealer.addItem(shotgun);
   
    	bartender.addItem(mead);
    	
    	wizard.addItem(potion);
    	wizard.addItem(teleporter);
    	
    	
    	//  ------ populating npcs ------
    	shadyGuy.addRequirement(pelletGun);
    	shadyGuy.addRequirement(pistol);
    	shadyGuy.addRequirement(revolver);
    	shadyGuy.addRequirement(rifle);
    	shadyGuy.addRequirement(shotgun);
    	shadyGuy.addRequirement(bbGun);
    	
    	dwarf.addRequirement(kingMackerel);
    	
    	
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
    	System.out.println("Are you ready to have a hog-killin' time (y/n)?\n");
    	
    	while (true) {
    	    String input = in.nextLine();
    	    
    	    switch(input) {
    	    	case "y":
    	    		break;
    	    	case "n":
    	    		System.out.printf("You know what %s? This town wasn't big enough for you anyway."
    	    				+ " Don't let me catch you near these parts again!", player.getName());
    	        	System.exit(0);
    	        default:
    	        	System.out.println(Game.no);
    	        	continue;
    	    }
    	    break;
    	}
    	
    	// Time to play!
    	// Game engine is located in the 'Room' file
    	
    	Room start = new Room("", 0);
    	start.playEngine();
    }
    
    
    
    
    
    
    
    
    
    
    //  ----------- METHODS -----------
    
    
    private static int roomIndex() {
    	return numOfRooms++;
    }
    
    
    public void delay(int num) {
    	try {
    		TimeUnit.MILLISECONDS.sleep(num);
    	} catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("I tried to make this dramatic!");
    	}
    }
    
    
    public String enemySelection(String input) {
    	
    	if (input.equals("f")) {
            regularEnemy();
    	} else if (input.equals("o")) {
    		bossEnemy();
    	}
    	return goBack();
    }
    
    
    public void regularEnemy() {
    	int roll = random.nextInt(2);
        
        switch(roll) {
        	case 0:
        		attackPlayer(easy);
        		System.out.println(player.easyDefeat);
        		break;
        	case 1:
        		attackPlayer(medium);
        		System.out.println(player.mediumDefeat);
        		break;
        	case 2:
        		attackPlayer(hard);
        		System.out.println(player.hardDefeat);
        		break;
        	default:
        		System.out.println(Game.oops);
        		System.out.println("I tried to switch up your enemy selection! Pun intended.");
        		break;
        }
    }
    
    
    public void bossEnemy() {
        attackPlayer(boss);
        if (player.getWon()) {
        	return;
        }
        System.out.println(player.bossFlee);
    }
    
    
    public void attackPlayer(Enemy enemy) {

    	System.out.printf("You and %s step outside the saloon, ready for a shootout.%n", enemy.name);
    	
    	while (player.getHp() > 0 && enemy.getHp() > 0) {
            int damage = random.nextInt(enemy.getDamage()) + 1;
            int roll = random.nextInt(4);
            
            System.out.println("Do you want to (s)trike, (f)lee, or (h)eal?\n");
            String input = in.nextLine();
            
            if (cons.contains(input)) {
            	player.constants(input, 4);
            }
            
            // *IMPLEMENT* boolean found (for special items)
            
            switch(input) {
            	case "s":
            		strikeEnemy(enemy, damage, roll);
            		continue;
            	case "f":
            		fleeEnemy(enemy, damage, roll);
            		break;
            	case "h":
            		healPlayer();
            		continue;
            }
            break;
    	}
    	
    	
    	if (player.getHp() <= 0) {
    		playerDeath();
    		return;
    	} else if (enemy.getHp() > 0) {
    		return;
    	} else {
    		enemyDeath(enemy);
    	}
    }
    
    	
	public void strikeEnemy(Enemy enemy, int damage, int roll) {
		System.out.printf("You and %s draw your weapons and fire your shots.%n", enemy.getName());
		delay(1000);
		
    	// *IMPLEMENT*
    	// for item in player inventory       // or inventory contains item/element/whatever??
    	// if item in inventory - battle consequences
    	// item found
    	// remove item -or- uses/turns +1
    	// else pass
		
    	switch(roll) {
    		case 0:
    		case 1:
    			enemy.setHp(-player.getDamage());
    			System.out.printf("You hit him! -%d HP%n"
    					+ "Enemy HP: %d%n", player.getDamage(), Math.max(0, enemy.getHp()));
    			break;
    		case 2:
    		case 3:
    			player.setHp(-damage);
    			System.out.printf("You've been hit! -%d HP%n"
    					+ "Remaining HP: %d%n", damage, Math.max(0, player.getHp()));
    			break;
    		default:
    			System.out.println(player.strikeNull);
    			break;
    	}
    }
            
            
	public void fleeEnemy(Enemy enemy, int damage, int roll) {
    	System.out.println("You attempt to scurry away...");
    	
       	switch(roll) {
    		case 0:
    		case 1:
    			System.out.printf("Your adversary fires at you and just barely misses.%n", enemy.getName());
    			break;
    		case 2:
    		case 3:
    			player.setHp(-damage);
    			System.out.printf("Your adversary shoots wildly and without abandon, grazing your side. -%d HP%nRemaining HP: %d%n", damage, Math.max(0, player.getHp()));
    			break;
    		default:
    			System.out.println(player.fleeNull);
    			break;
    	}
	}
            

	public void healPlayer() {
		player.heal();
	}

    
	public void playerDeath() {
		System.out.println(player.deathMessage);
		// ^ asks to continue
        while (true) {
        	String live = in.nextLine();
        	
        	switch(live) {
        		case "n":
        			System.out.println("See ya partner!");
        			System.exit(0);
        		case "y":
        			System.out.println("Here we go!");
        			player.setHp(100 - player.getHp());
        			break;
        		default:
        			System.out.println(Game.no);
        			break;
        	}
        }
	}
    	
	
	public void enemyDeath(Enemy enemy) {
		if (enemy == boss) {
			bossDeath();
		} else {
			System.out.printf("You emerge victorious! You take %d gold from the cowboy's pockets.%n", enemy.getGold());
			player.setGold(enemy.getGold());
			
			if (!player.getFought()) {
                System.out.println(player.bootsMessage);
                player.setFought(true);
                player.addItem(customBoots);
                player.activateBoots();
			}
			enemy.setHp(100 - enemy.getHp());
		}
	}
    

	public void bossDeath() {
		System.out.println("You emerge victorious! Congratulations,"
    			+ " you're the toughest sharpshooter in the Old West!\n"
    			+ "You go down in history as the most fearsome fighter that"
    			+ " has ever graced these dirt roads. Yee haw!");
    	System.out.println("Would you like to continue playing (y/n)?\n");
    	
    	while (true) {
            String keepGoing = in.nextLine();
            
            switch(keepGoing) {
            	case "y":
            		
            		// *IMPLEMENT* Some crazy you-win item. Teleporter??
            		
            		System.out.println("You turn back to the saloon, keen on keeping the adventure alive.");
            		player.setWon(true);
            		break;
            	case "n":
            		System.out.println("See ya partner!");
            		System.exit(0);
            	default:
            		System.out.println(Game.no);
            		continue;
            }
            break;
    	}
	}
	
	
	public String goBack() {
		System.out.println("From here you can either return to the (s)aloon or go south to the (t)own square.\n");
    	
    	while (true) {
            String decision = in.nextLine();
            
            switch(decision) {
            	case "s":
            		return "saloon";
            	case "t":
            		return "town square";
            	default:
            		System.out.println("Please either return to the (s)aloon or go to the (t)own square.\n");
            		break;
            }
    	}
	}
    
    
    public void trade(Merchant merchant) {
    	merchant.trade();
    }
    

    //  ----------- QUESTS ---------------
    
    // it would've been too boring to have a cookie-cutter quest method,
    // and I don't want each room file w/ an NPC to be cluttered up with all the
    // dialogue and shit, so each NPC will have their own quest method here

    public void questSelection(Npc npc) {
		if (npc == shadyGuy) {
			System.out.println(player.shadyStart);
			//System.out.println(player.questQuestion);
			//if (questAnswer()) {
			//	player.setShadyActive(true);
			//}
		} else if (npc == dwarf) {
			System.out.println(player.dwarfStart);
		} else if (npc == prisoner) {
			System.out.println(player.prisonerStart);
		}
		
		System.out.println("Do you accept this quest (y/n)?\n");
		
		while (true) {
		    String input = in.nextLine();
		    
		    if (input.equals("y")) {
		    	if (npc == shadyGuy) {
		    		player.setShadyActive(true);
		    		shadyQuest();
		    	} else if (npc == dwarf) {
		    		player.setDwarfActive(true);
		    		dwarfQuest();
		    	} else if (npc == prisoner) {
		    		player.setPrisonerActive(true);
		    		prisonerQuest();
		    	}
		    	break;
		    } else if (input.equals("n")) {
		        System.out.println(player.questDecline);
		        break;
		    } else
		    	System.out.println("Not a valid response.\n");
		    }
    }
    
    
    // public void questAccept(Npc npc)
    
    
    
    	public void shadyQuest() {
    	if (!shadyGuy.meetsRequirement()) {
    		System.out.println("The shady character checks your rucksack.\n'You don't have 'em all son!"
    				+ " Come back when your collection is complete.'");
    		if (shadyGuy.almostShady()) {
    			System.out.println("There must be a gun somewhere that the arms dealer doesn't sell...");
    		}
    		System.out.println();
    	} else {
    		System.out.println("'You got 'em!' he bellows. 'Now you're startin' to look like a real cowboy.'\n"
    				+ "He walks to the counter and whispers something in the arms dealer's ear. The dealer smiles at you and hands you a key.\n"
    				+ "'Alright partner, we only let the best of the best take this chance, and you've proven yourself,' he says.\n"
    				+ "Despite your awful confusion, the flattery feels pretty good.\n"
    				+ "You now have access to the (h)idden room in the Gun Shop.\n");
    		player.setShadyComplete(true);
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

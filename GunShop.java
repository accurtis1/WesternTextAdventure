package gameStuff;

import java.util.ArrayList;

public class GunShop extends Room {
    ArrayList<Item> items = new ArrayList<>();
    Game game = new Game();
    Merchant merchant;
    Npc npc;
    
    public GunShop(String name, int index, Merchant merchant, Npc npc) {
    	super(name, index);
    	this.merchant = merchant;
    	this.npc = npc;
    }
    
    
    public String enter() {
    	System.out.println("A well-stocked gun shop sits before you, ripe for the picking. That is, if you have the gold.\n"
    		+ "An arms dealer stands behind the counter, eager to (t)rade with you.");
    	if (Game.player.getShadyComplete()) {
    		System.out.println("The shady man is furiously explaining something to a teeny tiny cowboy in the corner.\n"
    				+ "Behind the arms dealer lies the door to the (h)idden room.");
    	} else {
    		System.out.println("You overhear a strange, shady-looking man in the corner mumbling about his lack of (g)uns.");
    	}
    	System.out.println("The exit for the Town Square is to the (e)ast.\n");
    	return choice();
    }
    
    
    private String choice() {
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input)) {
            	Game.player.constants(input, index);
            	return choice();
            }
            
            switch(input) {
            	case "l":
                	look();
                	break;
            	case "t":
            		game.trade(Game.armsDealer);
            		break;
            	case "g":
                	shady();
                	break;
            	case "h":
            		if (Game.player.getShadyComplete()) {
            			return "hidden room";
            		}
            		else {
            			System.out.println(Game.no);
            			break;
            		}
            	case "n":
            	case "s":
            	case "w":
            		System.out.println(Game.wrongTurn);
            		break;
            	case "e":
            		return "town square";
            	default:
            		System.out.println(Game.no);
            		break;
            }
            return choice();
    	}
    }
    
    
    private void look() {
    	System.out.println("You eye the room. A cabinet behind the counter holds the precious weapons."
            	+ " You'd have to go through the arms dealer to get to them. Not worth it.\nThere aren't"
            	+ " any items to obtain in here. You may be able to nab a box of ammunition,"
            	+ " but do you really think you'll make it out the door?\n");
    }
    
    
    private void shady() {
    	if (Game.player.getShadyComplete()) {
    		System.out.println("The shady guy is busy with a business deal and ignores you.\n");
    	} else {
    		if (Game.player.getShadyActive()) {
    			game.shadyQuest();
    		} else {
    			game.questSelection(Game.shadyGuy);
    		}
    	}
    }
}

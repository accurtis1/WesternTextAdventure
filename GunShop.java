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
    	System.out.println("A well-stocked gun shop sits before you, ripe for the picking."
    		+ " That is, if you have the gold.\n"
    		+ "An arms dealer stands behind the counter, eager to (t)rade with you.\n"
    		+ "Mention weird shady guy in the corner idk what (p)ucking letter to use.");
    	if (Game.player.shadyComplete) {
    		System.out.println("now you can see the (h)idden room.");
    	}
    	System.out.println("The exit back to the Town Square is to the (e)ast.\n");
    	return choice();
    }
    
    
    private String choice() {
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input)) {
            	Game.player.constants(input, index);
            }
            
            switch(input) {
            	case "l":
                	look();
                	break;
            	case "t":
            		game.trade(Game.armsDealer);
            		break;
            	case "p":
                	shady();
                	break;
            	case "h":
            		if (Game.player.shadyComplete) {
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
    	}
    }
    
    
    private void look() {
    	System.out.println("You eye the room. A cabinet behind the counter holds the precious weapons."
            	+ " You'd have to go through the arms dealer to get to them. Not worth it.\nThere aren't"
            	+ " any items to obtain in here. You may be able to nab a box of ammunition,"
            	+ " but do you really think you'll make it out the door?\n");
    }
    
    
    private void shady() {
    	if (Game.player.shadyComplete) {
    		System.out.println("The shady guy is busy with a business deal and ignores you.\n");
    	}
    	else {
    		if (Game.player.shadyActive) {
    			game.shadyQuest();
    		}
    		else {
    			game.questSelection(Game.shadyGuy);
    		}
    	}
    }
}

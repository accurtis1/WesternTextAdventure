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
    		+ " That is, if you have the gold.\nAn arms dealer stands behind the"
    		+ " counter, eager to (t)rade with you.\nMention weird shady guy in the"
    		+ " corner idk what letter to use.PPPP");
    	if (Game.player.shadyComplete)
    		System.out.println("now you can see the (h)idden room.");
    	System.out.println("The exit back to the Town Square is to the (e)ast.\n");
    	
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input))
            	Game.player.constants(input, index);
            
            else if (input.equals("l"))
            	System.out.println("You eye the room. A cabinet behind the counter holds the precious weapons."
            	+ " You'd have to go through the arms dealer to get to them. Not worth it.\nThere aren't"
            	+ " any items to obtain in here. You may be able to nab a box of ammunition,"
            	+ " but do you really think you'll make it out the door?\n");
            
            else if (input.equals("t"))
            	game.trade(Game.armsDealer);
            
            else if (input.equals("p")) {
            	if (Game.player.shadyComplete && !Game.player.shadyActive)
            		System.out.println("The shady guy is busy with a business deal and ignores you.\n");
            	else {
            		if (!Game.player.shadyActive && !Game.player.shadyComplete)
            			game.questSelection(Game.shadyGuy);
            		else if (Game.player.shadyActive)
            			game.shadyQuest();
            		if (Game.player.shadyComplete && Game.player.shadyActive) {
            			Game.player.shadyActive = false;
            			return "hidden room";
            		}
            		else if (Game.player.shadyComplete && !Game.player.shadyActive)
            			return "gun shop";
            	}
            }
            
            else if (input.equals("h") && Game.player.shadyComplete)
            	return "hidden room";
            
            else if (input.equals("n") || input.equals("s") || input.equals("w"))
            	System.out.println("Whoops, can't go that way.\n");
            
            else if (input.equals("e"))
            	return "town square";
            
            else
            	System.out.println("Not a valid response.\n");
    	}
    }
}

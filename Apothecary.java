package gameStuff;

import java.util.ArrayList;

public class Apothecary extends Room {
    ArrayList<Item> items = new ArrayList<>();
    Game game = new Game();
    Merchant merchant;
    Npc npc;
    
    public Apothecary(String name, int index, Merchant merchant, Npc npc) {
    	super(name, index);
    	this.merchant = merchant;
    	this.npc = npc;
    }
    
    public String enter() {
    	System.out.println("You walk into a small, hazy, steam-filled room. Its sole occupant is"
    		+ " a lone man with a long white beard; his pointed hat nearly touches"
    		+ " the low ceiling.\nHe stirs a large cauldron while flipping through"
    		+ " a book that appears to be a thousand years old. Looking up, he notices you.\n"
    		+ "'Would you like to (t)rade with me, dear traveler?' he asks.\n(d)warf stuff\nThe exit back to"
    		+ " the Town Square is to the (w)est.\n");
    
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input))
            	Game.player.constants(input, index);
            
            else if (input.equals("l"))
            	System.out.println("There are no loose items on the floor but the wizard has multitudes of"
            	+ " knick knacks littering his shelves, each one more interesting than the last.\n"
            	+ "The cauldron he stirs is emanating a strong, sickly sweet smell. It bubbles the color"
            	+ " of fresh violets and looks as thick as paste. You wonder what he could possibly be"
            	+ " making...\n");
            
            else if (input.equals("t"))
            	game.trade(Game.wizard);
            
            else if (input.equals("n") || input.equals("e") || input.equals("s"))
            	System.out.println("Whoops, can't go that way.\n");
            
            else if (input.equals("w"))
            	return "town square";
            
            else
            	System.out.println("Not a valid response.\n");
    	}
    }
}

package gameStuff;

import java.util.ArrayList;

public class Entry extends Room {
    ArrayList<Item> items = new ArrayList<>();
    Game game = new Game();
    int gold;
    
    public Entry(String name, int index, int gold) {
    	super(name, index);
    	this.gold = gold;
    }
    
    public String enter() {
    	System.out.println("You stand in front of a dirt road that leads to Wild Peaks, an old mining town. An isolated tumbleweed rolls across"
    		+ " your path.\nTo the (w)est is the so-called 'River of Riches' where you can fish"
    		+ " for items or pan for gold.\nTo the (n)orth is the Town Square, an area flanked"
    		+ " by shops and the Dern Tootin' Saloon.\n");
    	
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input))
            	Game.player.constants(input, index);
            
            else if (input.equals("b")) {
            	// battlwagons attempt
            }
            
            else if (input.equals("l")) {
            	System.out.printf("The entrance to town is a red dirt road with no items in sight. Wooden posts that"
            		+ " once held up a sign stand alone and desolate in the dust.%nYou hear the river running to your"
            		+ " west accompanied by the faint voices of gold panners trying to strike it rich. Anyone can dream.%n"
            		+ "The drone coming from the Town Square is louder; it seems like everyone is doing their business on this"
            		+ " fine afternoon.%nYou almost overlook something glinting on the ground...it's gold!%n+%d gold.%n%n", gold);
            	Game.player.gold += gold;
            }
            	
            else if (input.equals("n"))
            	return "town square";
            
            else if (input.equals("e") || input.equals("s"))
            	System.out.println("Whoops, can't go that way.\n");
            
            else if (input.equals("w"))
            	return "river";
            
            else
            	System.out.println("Not a valid response.\n");
    	}
    }
}

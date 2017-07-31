package gameStuff;

import java.util.ArrayList;

public class Entry extends Room {
    ArrayList<Item> items = new ArrayList<>();
    Game game = new Game();
    boolean collected = false;
    
    public Entry(String name, int index) {
    	super(name, index);
    }
    
    
    public String enter() {
    	System.out.println("You stand in front of a dirt road that leads to Wild Peaks, an old mining town. An isolated tumbleweed rolls across your path.\n"
    		+ "To the (w)est is the so-called 'River of Riches' where you can fish for items or pan for gold.\n"
    		+ "To the (n)orth is the Town Square, an area flanked by shops and the Dern Tootin' Saloon.\n");
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
            	case "b":
            		//battlewagons attempt
            		break;
            	case "n":
            		return "town square";
            	case "w":
            		return "river";
            	case "e":
            	case "s":
            		System.out.println(Game.wrongTurn);
            		break;
            	default:
            		System.out.println(Game.no);
            		break;
            }
            return choice();
    	}
    }
    
    
    private void look() {
    	System.out.println("The entrance to town is a red dirt road with no items in sight. Wooden posts that"
        		+ " once held up a sign stand alone and desolate in the dust.%nYou hear the river running to your"
        		+ " west accompanied by the faint voices of gold panners trying to strike it rich. Anyone can dream.%n"
        		+ "The drone coming from the Town Square is louder; it seems like everyone is doing their business on this"
        		+ " fine afternoon.%n");
    	if (!collected) {
    		int gold = (int)(Math.random()*20);
	    	System.out.printf("You almost overlook something glinting on the ground...it's gold!%n"
	        		+ "+%d gold.%n%n", gold);
	        Game.player.setGold(gold);
    	}
    }
}

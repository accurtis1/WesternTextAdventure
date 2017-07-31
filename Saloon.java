package gameStuff;

import java.util.ArrayList;
import java.util.Random;

public class Saloon extends Room {
    ArrayList<Item> items = new ArrayList<>();
    Merchant merchant;
    int looked = 0;
    Game game = new Game();
    Random rand = new Random();
    
    public Saloon(String name, int index, Merchant merchant) {
    	super(name, index);
    	this.merchant = merchant;
    }
    
    
    public String enter() {
    	if (!Game.player.getWon()) {
    		System.out.println("You swing open the doors to the Dern Tootin' Saloon and step inside."
    		+ " A few patrons eye you warily; you're the 'New Kid in Town.'");
    	} else {
    		System.out.println("The Dern Tootin' Saloon is filled with a tangible sense of respect as you step inside. Everyone knows"
    		+ " not to mess with you.");
    	}
    	
    	System.out.println("A burly bartender stands at the far end of the room, ready for you to (b)uy a drink.\n"
    		+ "Many patrons here, from the spindly to the stacked, are looking for a (f)ight.\n"
    		+ "The exit back to the Town Square is to the (s)outh.\n");
    	
    	if (!Game.player.getWon()) {
    		System.out.println("If you're feeling up to it, you can take on the baddest (o)utlaw."
    		+ " If you win, you'll be the town's new force to be reckoned with!\n");
    	}
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
            	case "f":
            		return game.enemySelection(input);
            	case "o":
            		if (!Game.player.getWon()) {
            			game.enemySelection(input);
            		} else {
            			System.out.println(Game.no);
            		}
            	case "b":
            		game.trade(merchant);
            	case "s":
            		return "town square";
            	case "n":
            	case "e":
            	case "w":
            		System.out.println(Game.wrongTurn);
            		break;
            	default:
            		System.out.println(Game.no);
            		break;
            }
            return choice();
    	}
    }
            
            
    public void look() {
    	if (looked < 4) {
    		System.out.println("You scramble around on the floor, looking for spare change.");
        	if (rand.nextInt(3) == 1) {
        		System.out.println("No gold here. Maybe there's some in that corner over there...\n");
        	} else {
        		int floorGold = (int)(Math.random() * 15 + 5);
                System.out.println("Woohoo! You find " + floorGold + " gold lying on the ground.\n");
                Game.player.setGold(floorGold);
                looked++;
        	}
    	} else {
    		System.out.println("You've already found everything in here!\n");
    	}
    }
}

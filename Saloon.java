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
    	if (!Game.player.won)
    		System.out.println("You swing open the doors to the Dern Tootin' Saloon and step inside."
    		+ " A few patrons eye you warily; you're the 'New Kid in Town.'");
    	else
    		System.out.println("The Dern Tootin' Saloon is filled with a tangible sense of respect as you step inside. Everyone knows"
    		+ " not to mess with you.");
    	
    	System.out.println("A burly bartender stands at the far end of the room, ready for you to (b)uy a drink.\n"
    		+ "There are many patrons here, both weak and strong, looking for a (f)ight.\n"
    		+ "The exit back to the Town Square is to the (s)outh.\n");
    	
    	if (!Game.player.won)
    		System.out.println("If you're feeling up to it, you can take on the baddest (o)utlaw."
    		+ " If you win, you'll be the town's new force to be reckoned with!\n");
    	
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input))
            	Game.player.constants(input, index);
            
            else if (input.equals("l")) {
            	if (looked < 4) {
            		System.out.println("You scramble around on the floor, looking for spare change.");
            		
            		if (rand.nextInt(3) == 1)
            			System.out.println("No gold here. Maybe there's some in that corner over there...\n");
            		
            		else {
                            int floorGold = (int)(Math.random() * 15 + 5);
                            System.out.println("Woohoo! You find " + floorGold + " gold lying on the ground.\n");
                            Game.player.gold += floorGold;
                            looked++;
            		}
            	}
            	else
            		System.out.println("You've already found everything in here!\n");
            }
            
            else if (input.equals("f") || (input.equals("o") && !Game.player.won))
            	return game.enemySelection(input);
            
            else if (input.equals("b"))
            		game.buy(Game.bartender);
            
            else if (input.equals("n") || input.equals("e") || input.equals("w"))
            	System.out.println("Whoops, can't go that way.\n");
            
            else if (input.equals("s"))
            	return "town square";
            
            else
            	System.out.println("Not a valid response.\n");
    	}
    }
}

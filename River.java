package gameStuff;

import java.util.ArrayList;
import java.util.Random;

public class River extends Room {
    String name;
    Item item1;
    Item item2;
    Item item3;
    int looked = 0;
    ArrayList<Item> items = new ArrayList<>();
    int a = 3;
    Random rand = new Random();
    
    // damn there's gotta be a better way to do this
    public River(String name, int index, Item item1, Item item2, Item item3, Item item4) {
    	super(name, index);
    	items.add(item1);
    	items.add(item2);
    	items.add(item3);
    	items.add(item4);
    }
    
    public String enter() {
    	System.out.println("After a short trek you stumble upon the River of Riches. The babble of"
    		+ " the rushing stream whispers promises of gold hidden within.\nYou can take your"
    		+ " chances and (p)an for gold or break out a pole and go (f)ishing.\nTo the (e)ast"
    		+ " is the entrance to town.\n");
    	
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input))
            	Game.player.constants(input, index);
            
            else if (input.equals("l"))
            	System.out.println("The glistening river flows freely, unencumbered by debris or unsightly"
            	+ " garbage.\nSeveral townspeople are either fishing or panning for gold. Some wade in the"
            	+ " water, washing off the hot summer day.\nThere aren't any items on the ground, but"
            	+ " you can see hints of treasures galore in the water.\n");
            
            else if (input.equals("p")) {
            	if (looked < 5) {
            		System.out.println("You cross your fingers and place your pan in the water.");
            		
            		if (rand.nextInt(6) == 1)
            			System.out.println("You bring up nothing of interest this time.\n");
            		else {
            			int floorGold = (int)(Math.random() * 25 + 5);
            			System.out.println("Woohoo! You retrieve " + floorGold + " gold.\n");
            			Game.player.gold += floorGold;
            			looked++;
            		}
            	}
            	else
            		System.out.println("You try once more to no avail. You must've gotten it all.\n");
            }
            
            else if (input.equals("f")) {
            	if (items.size() > 0) {
                    System.out.println("You cast your line in the river, hoping for the best.");
                    
                    if (rand.nextInt(2) == 1) {
                    	int b = rand.nextInt(a + 1);
                    	Item item = items.get(b);
                    	System.out.printf("You catch a %s!%n%n", item.name);
                    	Game.player.inventory.add(item);
                    	items.remove(item);
                    	a--;
                    }
                    else
                    	System.out.println("Nothing this time, but you were so close!\n");
            	}
            	else
            		System.out.println("You cast your line in the water but the effort is futile. The river seems eerily empty.\n");
            }
            
            else if (input.equals("n") || input.equals("s") || input.equals("w"))
            	System.out.println("Whoops, can't go that way.\n");
            
            else if (input.equals("e"))
            	return "entry";
            
            else
            	System.out.println("Not a valid response.\n");
    	}
    }
}

package gameStuff;

import java.util.ArrayList;

public class River extends Room {
    String name;
    ArrayList<Item> items = new ArrayList<>();
    int success = 0;

    public River(String name, int index) {
    	super(name, index);
    	addItem(Game.rubberDuck);
    	addItem(Game.kingMackerel);
    	addItem(Game.redHerring);
    	addItem(Game.bbGun);
    }
    
    
    public void addItem(Item item) {
    	items.add(item);
    }
    
    
    public void removeItem(Item item) {
    	items.remove(item);
    }
    
    
    public Item getItem() {
    	return items.get(0);
    }
    
    
    public String enter() {
    	System.out.println("After a short trek you stumble upon the River of Riches. The babble of"
    		+ " the rushing stream whispers promises of gold hidden within.\n"
    		+ "You can take your chances and (p)an for gold or break out a pole and go (f)ishing.\n"
    		+ "To the (e)ast is the entrance to town.\n");
    	return choice();
    }
    
    
    public String choice() {
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
            	case "p":
            		pan();
            		break;
            	case "f":
            		fish();
            		break;
            	case "e":
            		return "entry";
            	case "n":
            	case "s":
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

    
    private void look() {
    	System.out.println("The glistening river flows freely, unencumbered by debris or unsightly"
            	+ " garbage.\n"
            	+ "Several townspeople are either fishing or panning for gold. Some wade in the water, washing off the sweat of a hot summer day.\n"
            	+ "There aren't any items on the ground, but you can see hints of treasures galore in the water.\n");
    }
    
    private void pan() {
    	if (success > 5) {
    		System.out.println("You try once more to no avail. You must've gotten it all.\n");
    	} else {
    		System.out.println("You cross your fingers and place your pan in the water.");
    		
    		if (randomize()) {
    			panSuccess();
    		} else {
    			System.out.println("You bring up nothing of interest this time.\n");
    		}
    	}
    }
    
    private void panSuccess() {
		int panGold = randomGold();
		System.out.println("Woohoo! You retrieve " + panGold + " gold.\n");
		Game.player.setGold(panGold);
		success++;
    }
    
    
    private void fish() {
    	if (items.size() == 0) {
    		System.out.println("You cast your line in the water but the effort is futile. The river seems eerily empty.\n");
    	} else {
            System.out.println("You cast your line in the river, hoping for the best.");
            
            if (randomize()) {
            	fishSuccess();
            } else {
            	System.out.println("Nothing this time, but you were so close!\n");
            }
    	}
    }
    
    private void fishSuccess() {
    	Item item = getItem();
    	System.out.printf("You catch a %s!%n%n", item.name);
    	Game.player.addItem(item);
    	removeItem(item);
    }
    
    private boolean randomize() {
    	int num = Game.random.nextInt(4);
    	switch(num) {
    		case 0:
    		case 1:
    			return true;
    		default:
    			return false;
    	}
    }
    
    private int randomGold() {
    	int num = Game.random.nextInt(20);
    	return num;
    }
}

package gameStuff;

public class HiddenRoom extends Room {
	
	static int used = 0;
    
    public HiddenRoom (String name, int index) {
    	super(name, index);
    }
    
    
    public String enter() {
		System.out.println("You walk through the hidden door behind the arms dealer to reveal a swampy and very humid room.\n"
			+ "There are only two objects in sight - a pump-action well in the middle of the room with some green growth on it and a fancy glass on the ground beside it.\n"
			+ "You have a strong urge to (f)ill the cup.\n"
			+ "The door that (r)eturns to the Gun Shop is behind you.\n");
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
	        	case "f":
	        		fill();
	        		break;
	        	case "r":
	        		return "gun shop";
	        	default:
	        		System.out.println(Game.no);
	        		break;
	        }
	        return choice();
	    }
    }
    
    
    private void look() {
    	System.out.println("Green moss and rope-like vines cling to the stone walls. The air is almost solid, it's so thick.\n"
    			+ "A rickety pump is the only fixture in the room. Next to it sits a dusty but elegant drinking glass.\n");
    }
    
    
    private void fill() {
    	System.out.println("You take the glass and pump some water into it. The room glows with the liquid's flourescent green shimmer.\n"
    			+ "It's almost like energy is emantating from the water itself. Do you drink it (y/n)?\n");
    	
    	try {
    		while(true) {
    	    	String input = Game.in.nextLine();
    	    	
    	    	switch(input) {
    	    		case "y":
    	    			if (used < 5) {
    	    				drink();
    	    			} else {
    	    		    	System.out.println("Other than a powerful bout of gas, you feel nothing.\n");
    	    			}
    	    			break;
    	    		case "n":
    	    			System.out.println("You dump the water out and set the glass down. The watered area of the floor " + phrase() + "\n");
    	    			break;
    	    		default:
    	    			System.out.println(Game.no);
    	    			break;
    	    	}
    	    	break;
    		}
    	} catch (Exception e) {
    		System.out.println(Game.oops);
    		System.out.println("I mucked up your water!");
    	}
    }
    
    
    private void drink() {
    	System.out.println("You toss the strange liquid down the hatch.");
    	
    	int roll = Game.random.nextInt(4);
    	
    	switch(roll) {
    		case 0:
    			changeHp(true);
    			break;
    		case 1:
    			changeHp(false);
    			break;
    		case 2:
    			changeGold(true);
    			break;
    		case 3:
    			changeGold(false);
    			break;
    		default:
    			System.out.println(Game.oops);
    			System.out.println("I tried to buy you a drank!");
    			break;
    	}
    }
    
    
    private void changeHp(boolean good) {
    	System.out.println("Suddenly you notice an odd tingling sensation all over. Your heartbeat is deafening in your ears...");
    	
		int amount = Game.random.nextInt(15) + 1;
		
    	if (!good) {
    		amount *= -1;
    	} else {
    		System.out.print("+");
    	}
    	
    	System.out.printf("%d HP%n", amount);
    	Game.player.setHp(amount);
    	System.out.printf("Current HP: %d%n%n", Game.player.getHp());
    	
    	used++;
    }
    
    
    private void changeGold(boolean good) {
    	System.out.println("Uncomfortable doesn't even come close to how you feel as your pockets start to move and change shape...");
    	
    	int amount = Game.random.nextInt(100) + 1;
    	
    	if (!good) {
    		amount *= -1;
    	} else {
    		System.out.print("+");
    	}
    	
    	System.out.printf("%d gold%n", amount);
    	Game.player.setGold(amount);
    	if (Game.player.getGold() <= 0) {
    		Game.player.setGold(Game.player.getGold() * -1);
    	}
    	System.out.printf("Current gold: %d%n%n", Game.player.getGold());
    	
    	used++;
    }
    
    
    private String phrase() {
    	int roll = Game.random.nextInt(3);
    	
    	switch(roll) {
    		case 0:
    			return "grows a set of mutant-looking flowers.";
    		case 1:
    			return "suddenly becomes spotless and shiny.";
    		case 2:
    			return "starts to steam and hiss. It didn't like that, apparently.";
    		default:
    			System.out.println(Game.oops);
    			System.out.println("I tried to be funny!");
    			return "";
    	}
    }
}

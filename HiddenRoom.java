package gameStuff;

public class HiddenRoom extends Room {
    
    public HiddenRoom (String name, int index) {
    	super(name, index);
    }
    
    public String enter() {
	System.out.println("You walk through the hidden door behind the arms dealer to reveal a swampy, nasty room etc etc\n"
		+ " there are only two objects in sight. one is a still well in the middle of the room with a spigot running down\n"
		+ " in it and a fancy glass on the edge of the well. you have a strong urge to fill the cup up with the faucet\n."
		+ " prompts for actions blah blah blah r to return to gun shop\n");
	
	while (true) {
	    String input = Game.in.nextLine();
	    
        if (Game.cons.contains(input))
        	Game.player.constants(input, index);
	    
	    
	    else if (input.equals("")) {
		// actions and stuff, put randomized effects methods in game
	    }
	    
	    
	    
	    else if (input.equals("r"))
	    	return "gun shop";
	    
	    else
	    	System.out.println("Not a valid response.\n");
	}
    }
}

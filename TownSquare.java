package gameStuff;

public class TownSquare extends Room {
    Npc npc;
    Game game = new Game();
    public TownSquare(String name, int index, Npc npc) {
    	super(name, index);
    	this.npc = npc;
    }
    
    
    public String enter() {
    	System.out.println("The Town Square is bustling with people. A young man in rags shouts the latest news."
    		+ " A few buildings surround the hub of activity.\n"
    		+ "(p)risoner\n"
    		+ "To the (w)est is the land's most respected Gun Shop.\n"
    		+ "To the (n)orth is the famed shootout spot, the Dern Tootin' Saloon.\n"
    		+ "To the (e)ast is a squat building that reads 'Apothecary' on its sign.\n"
    		+ "The entrance to town is to the (s)outh.\n");
    	return choice();
    }
    
    
    public String choice() {
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input)) {
            	Game.player.constants(input, index);
            }
            
            switch(input) {
            	case "l":
            		look();
            		break;
            	case "p":
            		prisoner();
            		break;
            	case "n":
            		return "saloon";
            	case "e":
            		return "apothecary";
            	case "s":
            		return "entry";
            	case "w":
            		return "gun shop";
            	default:
            		System.out.println(Game.no);
            }
    	}
    }
    
    
    private void look() {
    	System.out.println("People hustle in and out of the shops, concealing firearms under their jackets and potions in their bags.\n"
            	+ "You watch more than one drunk cowboy stumble out of the saloon; some sport smoking holes in their sleeves.");
    	if (!Game.player.prisonerComplete) System.out.println("A woman at the far end of the square stands on the gallows, her head"
    			+ " cast down. A small crowd is beginning to gather.");
    	if (Game.player.prisonerActive) System.out.println("She looks up and spots you. Her eyes are begging for mercy. You must stop this execution.");
    	if (Game.player.prisonerComplete) System.out.println("The crowd has abandoned the now-deserted gallows, mumbling their disappointment.");
    	if (!Game.player.inventory.contains(Game.map)) {
    		System.out.println("What luck! You find a shoddy map in the dirt, scrawled on the back of a WANTED poster.\n"
    				+ "Map added to inventory. Type (m) at any time to view.");
    		Game.player.addItem(Game.map);
    	}
    	System.out.println();
    }
    
    
    private void prisoner() {
    	if (Game.player.prisonerComplete) {
    		System.out.println("The prisoner and her crowd have disappeared. Only two angry-looking deputies remain.\n");
    	}
    	else if (!Game.player.prisonerActive) {
    		game.questSelection(Game.prisoner);
    	}
    	else {
    		game.prisonerQuest();
    	}
    }
}

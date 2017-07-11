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
    		+ " A few buildings surround the hub of activity.\n(p)risoner\nTo the (w)est is the land's most respected"
    		+ " Gun Shop.\nTo the (n)orth is the famed shootout spot, the Dern Tootin' Saloon.\nTo the (e)ast"
    		+ " is a squat building that reads 'Apothecary' on its sign.\nThe entrance to town is to the (s)outh.\n");
    	
    	while (true) {
            String input = Game.in.nextLine();
            
            if (Game.cons.contains(input))
            	Game.player.constants(input, index);
            
            else if (input.equals("l")) {
            	System.out.println("People hustle in and out of the shops, concealing firearms under their jackets and potions in their bags.\n"
            	+ "You watch more than one drunk cowboy stumble out of the saloon; some sport smoking holes in their sleeves.");
            	if (!Game.player.prisonerComplete) System.out.println("A woman at the far end of the square stands on the gallows, her head"
            			+ " cast down. A small crowd is beginning to gather.");
            	if (Game.player.prisonerActive) System.out.println("She looks up and spots you. Her eyes are begging for mercy. You must stop this execution.");
            	if (Game.player.prisonerComplete) System.out.println("The crowd has abandoned the now-deserted gallows, mumbling their disappointment.");
            	if (!Game.player.inventory.contains(Game.map)) {
            		System.out.println("What luck! You find a shoddy map in the dirt, scrawled on the back of a WANTED poster.\n"
            				+ "Map added to inventory. Type (m) at any time to view.");
            		Game.player.inventory.add(Game.map);
            	}
            	System.out.println();
            }
            
            else if (input.equals("p")) {
            	if (Game.player.prisonerComplete && !Game.player.prisonerActive)
            		System.out.println("The prisoner and her crowd have disappeared. Only two angry-looking deputies remain.\n");
            	else if (!Game.player.prisonerActive && !Game.player.prisonerComplete)
            		game.questSelection(Game.prisoner);
            	else if (Game.player.prisonerActive)
            		game.prisonerQuest();
            }
            
            else if (input.equals("n"))
            	return "saloon";
            
            else if (input.equals("e"))
            	return "apothecary";
            
            else if (input.equals("s"))
            	return "entry";
            
            else if (input.equals("w"))
            	return "gun shop";
            
            else
            	System.out.println("Not a valid response.\n");
    	}
    }
}

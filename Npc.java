package gameStuff;

import java.util.ArrayList;

public class Npc {
    String name;
    ArrayList<Item> requirement = new ArrayList<>();
    
    public Npc(String name) {
    	this.name = name;
    }
    
    public void addRequirement(Item item) {
    	requirement.add(item);
    }
    
    // pretty sure I'm gonna have most npcs only require
    // one item, so this will do
    public boolean meetsRequirement() {
    	for (Item item : requirement) {
            if (Game.player.inventory.contains(item)) continue;
            else return false;
    	}
    	return true;
    }
    
    // in case the player bought all the guns from the arms dealer
    // and is confused because they still don't have all the guns
    // in the game (you can fish a BB gun out of the river lol)
    public boolean almostShady() {
	for (Item item : requirement) {
	    if (item == Game.bbGun) continue;
	    if (Game.player.inventory.contains(item)) continue;
	    else return false;
	}
	if (Game.player.inventory.contains(Game.bbGun)) return false;
	return true;
    }
}

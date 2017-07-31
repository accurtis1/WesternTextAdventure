package gameStuff;

import java.util.ArrayList;

public class Npc {
    String name;
    ArrayList<Item> requirement = new ArrayList<>();
    
    public Npc(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    public void addRequirement(Item item) {
    	requirement.add(item);
    }
    
    public boolean meetsRequirement() {
    	for (Item item : requirement) {
            if (Game.player.inventory.contains(item)) {
            	continue;
            } else {
            	return false;
            }
    	}
    	return true;
    }
    
    // in case player did not find bb gun
    public boolean almostShady() {
    	for (Item item : requirement) {
    		if (item == Game.bbGun) {
    			continue;
    		}
    		if (Game.player.inventory.contains(item)) {
    			continue;
    		} else {
    			return false;
    		}
    	}
    	if (Game.player.inventory.contains(Game.bbGun)) {
    		return false;
    	}
    	return true;
    }
}

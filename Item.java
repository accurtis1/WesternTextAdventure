package gameStuff;

public class Item {
    String name;
    int value;
    int damage;
    int heal;
    
    public Item(String name, int value, int damage, int heal) {
    	this.name = name;
    	this.value = value;
    	this.damage = damage;
    	this.heal = heal;
    	Game.items.add(this);
    }
}
package gameStuff;

import java.util.ArrayList;

public class Enemy {
    String name;
    int gold;
    int hp;
    int damage;
    ArrayList<Item> inventory = new ArrayList<>();
    
    public Enemy(String name, int gold, int hp, int damage) {
    	this.name = name;
    	this.gold = gold;
    	this.hp = hp;
    	this.damage = damage;
    }
    
    
    public String getName() {
    	return name;
    }
    
    
    public void setHp(int amount) {
    	hp += amount;
    }
    
    
    public int getHp() {
    	return hp;
    }
    
    
    public int getDamage() {
    	return damage;
    }
    
    
    public int getGold() {
    	return gold;
    }
}

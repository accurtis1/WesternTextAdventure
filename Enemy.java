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
}

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

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setHeal(int heal) {
		this.heal = heal;
	}

	public int getHeal() {
		return heal;
	}
	
	public boolean has(String condition) {
		switch (condition) {
			case "value":
				if (getValue() > 0) {
					return true;
				} return false;
			case "damage":
				if (getDamage() > 0) {
					return true;
				} return false;
			case "heal":
				if (getHeal() > 0) {
					return true;
				} return false;
			default:
				System.out.println(Game.oops);
				System.out.println("I tried to streamline item conditions!");
				return false;
		}
	}
}
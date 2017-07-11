package gameStuff;

public class Room {
    
    String name;
    int index;
    
    public Room(String name, int index) {
    	this.name = name;
    	this.index = index;
    }
    
    public Room nextRoom(String name) {
    	// This is the third place the engine goes.
    	// It takes the string passed through from the
    	// openingScene function and searches through the
    	// HashMap of rooms to get the proper object.
    	// It then returns that object to openingScene.
    	return Game.roomObjects.get(name);
    }
    
    public String enter() {
    	// Idk I kept getting an error because Room
    	// didn't have an 'enter' function. It's never
    	// accessed in the game but whatevs.;
    	return "";
    }
    
    public Room openingScene(String startScene) {
    	// This is the second place the engine goes.
    	// We're now passing a string through to the
    	// nextRoom function. We will receive an
    	// object that is returned to us from
    	// nextRoom and return it to the 
    	// currentRoom.
    	return nextRoom(startScene);
    }
    
    public void playEngine() {
    	// Here is where the game starts
    	// It's setting the currentRoom for the first time
    	// and the first time it's going to be the openingScene
    	// in which we pass a string through. It will receive
    	// a room object and that will be set as the currentRoom.
    	Room currentRoom = openingScene("entry");
    	
    	while (true) {
            String nextRoomName = currentRoom.enter();
            currentRoom = nextRoom(nextRoomName);
    	}
    }
}

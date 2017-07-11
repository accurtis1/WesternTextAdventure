package gameStuff;

public class BattleWagons {
	int[][] board = new int[5][5];
	int[][] ships = new int[3][2];
	int[] shot = new int[2];
	int attempt;
	int shotHit;
	
	public BattleWagons() {
		// something that runs shit goes here
	}
	
	public void initboard(int[][] board) {
		System.out.println(board);
	}
}

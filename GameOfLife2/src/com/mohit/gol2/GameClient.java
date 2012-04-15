package com.mohit.gol2;

public class GameClient {
	public static void main(String[] args) {
		System.out.println("--- Game Of Life ---");
		int gridSize = 20;
		Game gameOfLife = new Game();
		gameOfLife.startGame(gameOfLife, gridSize);
	}
}

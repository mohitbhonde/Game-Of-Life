package com.mohit.gol2;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private int [][] currentGenereation;
	private int [][] successor;
	
	private void initalizeBaseGrid(int size){
		currentGenereation = new int[size][size];
	}
	private void placeStartingSeeds() {
		
		// oscilator blinker 2
		currentGenereation[8][1] = 1;
		currentGenereation[8][2] = 1;
		currentGenereation[8][3] = 1;
		// beacon period 2
		currentGenereation[1][1] = 1;
		currentGenereation[1][2] = 1;
		currentGenereation[2][1] = 1;
		currentGenereation[2][2] = 1;
		currentGenereation[3][3] = 1;
		currentGenereation[3][4] = 1;
		currentGenereation[4][3] = 1;
		currentGenereation[4][4] = 1;
		
		// still life block
		currentGenereation[6][6] = 1;
		currentGenereation[6][7] = 1;
		currentGenereation[7][6] = 1;
		currentGenereation[7][7] = 1;
		
		// still life boat
		currentGenereation[10][6] = 1;
		currentGenereation[10][7] = 1;
		currentGenereation[11][6] = 1;
		currentGenereation[12][7] = 1;
		currentGenereation[11][8] = 1;
		
		//light weight space ship
		currentGenereation[16][1] = 1;
		currentGenereation[16][2] = 1;
		currentGenereation[16][3] = 1;
		currentGenereation[16][4] = 1;
		currentGenereation[17][0] = 1;
		currentGenereation[17][4] = 1;
		currentGenereation[18][4] = 1;
		currentGenereation[19][0] = 1;
		currentGenereation[19][3] = 1;
	}
	
	private void printGeneration(int[][] array, int size){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(array[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	private void performIteration(int boundarySize){
		successor = new int[boundarySize][boundarySize];
		for(int i=0;i<boundarySize;i++){
			for(int j=0;j<boundarySize;j++){
				List<Integer> neighbours = getNeighbours(currentGenereation,i,j,boundarySize);
				if(currentGenereation[i][j] == GameOfLifeConstants.CELL_ALIVE){
					if(neighbours.size() < GameOfLifeConstants.UNDER_POPULATION ){
						successor[i][j] = GameOfLifeConstants.CELL_DEAD;
					}
					else if (neighbours.size() > GameOfLifeConstants.OVER_POPULATION){
						successor[i][j] = GameOfLifeConstants.CELL_DEAD;
					}
					else if (neighbours.size() >= GameOfLifeConstants.UNDER_POPULATION && neighbours.size() <= GameOfLifeConstants.OVER_POPULATION){
						successor[i][j] = GameOfLifeConstants.CELL_ALIVE;
					}
					else{
						successor[i][j] = GameOfLifeConstants.CELL_DEAD;
					}
				}
				else {
					if(neighbours.size() == GameOfLifeConstants.OVER_POPULATION){
						successor[i][j] = GameOfLifeConstants.CELL_ALIVE;
					}
					else {
						successor[i][j] = GameOfLifeConstants.CELL_DEAD;
					}
				}
			}
		}
		this.setCurrentGenereation(successor);
	}
	
	private List<Integer> getNeighbours(int[][] current, int i, int j, int boundarySize) {
		List<Integer> neighbours = new ArrayList<Integer>();
		if((i-1) >= 0 && (j-1) >=0){
			if(current[i-1][j-1] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i-1][j-1]);
		}
		
		if( (i-1) >= 0){
			if(current[i-1][j] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i-1][j]);
		}
		
		if((j+1) < boundarySize && (i-1) >=0 ){
			if(current[i-1][j+1] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i-1][j+1]);
		}
		
		if((j+1) < boundarySize){
			if(current[i][j+1] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i][j+1]);
		}
		
		if((i+1) < boundarySize && (j+1) < boundarySize){
			if(current[i+1][j+1] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i+1][j+1]);
		}
		
		if((i+1) < boundarySize){
			if(current[i+1][j] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i+1][j]);
		}
		
		if((j-1)>=0 && (i+1) < boundarySize){
			if(current[i+1][j-1] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i+1][j-1]);
		}
		
		if((j-1) >= 0){
			if(current[i][j-1] == GameOfLifeConstants.CELL_ALIVE)
				neighbours.add(current[i][j-1]);
		}
		
		return neighbours;
	}
	private int[][] getCurrentGenereation() {
		return currentGenereation;
	}
	private void setCurrentGenereation(int[][] currentGenereation) {
		this.currentGenereation = currentGenereation;
	}
	
	public void startGame(Game gameOfLife, int gridSize){
		gameOfLife.initalizeBaseGrid(gridSize);
		gameOfLife.placeStartingSeeds();
		gameOfLife.printGeneration(gameOfLife.getCurrentGenereation(),gridSize);
		for(int i=0;i<10;i++){
			gameOfLife.performIteration(gridSize);
			System.out.println("---------------------");
			gameOfLife.printGeneration(gameOfLife.getCurrentGenereation(),gridSize);
		}
	}
}

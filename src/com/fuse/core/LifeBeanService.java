package com.fuse.core;

import java.util.ArrayList;
import java.util.List;

public class LifeBeanService {
	
	public static final String C_ALIVE = "0";
	public static final String C_DEAD = ".";

	private boolean isValid(int r, int c, int gridRow, int gridCol) {
		boolean isValid = false;
		
		if(r >= 0 && c >= 0 && r < gridRow && c < gridCol){
			isValid = true;
		}
		
		return isValid;
	}

	
	private int countNeighbors(int row, int col, List<List<String>> inputGrid) {
		
		int r=0,c=0;
		int neighbor = 0;
		
		int gridRow = inputGrid.size();
		int gridCol = inputGrid.get(0).size();
		
		
		//diagonal - 2nd Quadrant
		r = row -1;
		c = col -1;
		if(isValid(r,c,gridRow, gridCol) && C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}

		//diagonally - 4th Quadrant
		r = row +1;
		c = col +1;
		if(isValid(r,c,gridRow, gridCol)&& C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}
		//diagonally - 3rd Quadrant
		r = row +1;
		c = col -1;
		if(isValid(r,c,gridRow, gridCol)&& C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}
		
		//diagonally - 1st Quadrant
		r = row -1;
		c = col +1;
		if(isValid(r,c,gridRow, gridCol)&& C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}
		
		//left 
		r = row;
		c = col -1;
		if(isValid(r,c,gridRow, gridCol)&& C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}
		
		//right 
		r = row;
		c = col +1;
		if(isValid(r,c,gridRow, gridCol)&& C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}
				
		//up 
		r = row -1;
		c = col ;
		if(isValid(r,c,gridRow, gridCol)&& C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}
		
		//down 
		r = row+1;
		c = col ;
		if(isValid(r,c,gridRow, gridCol)&& C_ALIVE.equals(inputGrid.get(r).get(c))){
			neighbor += 1;
		}
		
		return neighbor;
	
	}

    public List<List<String>> calcNextGridState(List<List<String>> inputGrid) {
		
    	List<List<String>> resultGrid = new ArrayList<>();
    	
    	for (int i = 0; i < inputGrid.size(); i++) {
    		List<String> resultCols = new ArrayList<>();
			for (int j = 0; j < inputGrid.get(i).size(); j++) {
				
				int count = countNeighbors(i,j, inputGrid);
				String currentState = inputGrid.get(i).get(j).equals(".") ? C_DEAD : C_ALIVE;;
				String state = predictNextState(currentState, count);
				resultCols.add(state);
			}
			resultGrid.add(resultCols);
		}
		
		return resultGrid;
	}


	public String predictNextState(String currentState, int neighborCount) {
		
		boolean isNexGen = (C_ALIVE.equals(currentState)) && (neighborCount == 2 || neighborCount == 3);
		
		boolean isRevived = (C_DEAD.equals(currentState)) && neighborCount == 3;
		
		String nextState = C_DEAD;
		
		if(isNexGen || isRevived){
			nextState = C_ALIVE;
		}
		
		return nextState;
	}
	
	
}

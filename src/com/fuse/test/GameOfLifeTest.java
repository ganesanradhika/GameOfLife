package com.fuse.test;

import org.junit.Assert;
import org.junit.Test;

import com.fuse.core.LifeBeanService;

public class GameOfLifeTest {
	
	@Test
	public void stateAlive_lessThanTwoNeighbor_Dies(){
		
		LifeBeanService game = new LifeBeanService();
		
		String s = LifeBeanService.C_ALIVE;
		int aliveNeighbors = 1;
		
		String nextState = game.predictNextState(s, aliveNeighbors);
		
		Assert.assertEquals(LifeBeanService.C_DEAD, nextState);
	}

	@Test
	public void stateAlive_moreThanTwoNeighbor_Dies(){
		
		LifeBeanService game = new LifeBeanService();
		
		String s = LifeBeanService.C_ALIVE;
		int aliveNeighbors = 5;
		
		String nextState = game.predictNextState(s, aliveNeighbors);
		
		Assert.assertEquals(LifeBeanService.C_DEAD, nextState);
	}
	
	@Test
	public void stateAlive_twoOrthreeNeighbors_Alive(){
		
		LifeBeanService game = new LifeBeanService();
		
		String s = LifeBeanService.C_ALIVE;
		int aliveNeighbors = 2;
		
		String nextState = game.predictNextState(s, aliveNeighbors);
		
		Assert.assertEquals(LifeBeanService.C_ALIVE, nextState);
		
		aliveNeighbors = 3;
		
		nextState = game.predictNextState(s, aliveNeighbors);
		
		Assert.assertEquals(LifeBeanService.C_ALIVE, nextState);
	}
	
	@Test
	public void stateDead_ExactlyThreeNeighbors_Alive(){
		
		LifeBeanService game = new LifeBeanService();
		
		String s = LifeBeanService.C_DEAD;
		int aliveNeighbors = 3;
		
		String nextState = game.predictNextState(s, aliveNeighbors);
		
		Assert.assertEquals(LifeBeanService.C_ALIVE, nextState);
		
	}

	@Test
	public void stateDead_LessThanThreeNeighbors_Dies(){
		
		LifeBeanService game = new LifeBeanService();
		
		String s = LifeBeanService.C_DEAD;
		int aliveNeighbors = 2;
		
		String nextState = game.predictNextState(s, aliveNeighbors);
		
		Assert.assertEquals(LifeBeanService.C_DEAD, nextState);
		
	}
	
	@Test
	public void stateDead_moreThanThreeNeighbors_Dies(){
		
		LifeBeanService game = new LifeBeanService();
		
		String s = LifeBeanService.C_DEAD;
		int aliveNeighbors = 4;
		
		String nextState = game.predictNextState(s, aliveNeighbors);
		
		Assert.assertEquals(LifeBeanService.C_DEAD, nextState);
		
	}
	
}

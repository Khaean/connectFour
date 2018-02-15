package test;


import static org.junit.Assert.*;

import java.util.Scanner;
import org.junit.Test;
import Main.ConnectFour;

public class ConnectFourTest{

	@Test
	public void test4StraightHorizontal() {
		
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		
		// Test 4 straight horizontal
		Integer[][] boardTest = new Integer[6][7];
		boardTest[0][0] = 1;
		boardTest[0][1] = 1;
		boardTest[0][2] = 1;
		boardTest[0][3] = 1;
		boardTest[1][1] = 2;
		boardTest[2][2] = 2;
		boardTest[3][3] = 2;
		game.setBoard(boardTest);
		
		assertEquals(game.checkWinner(), new Integer(1));
		assertNotEquals(game.checkWinner(), new Integer(2));
	}
	
	@Test
	public void test4StraightVertical() {
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		
		// Test 4 straight vertical
		Integer[][] boardTest = new Integer[6][7];
		boardTest[0][0] = 1;
		boardTest[1][0] = 1;
		boardTest[2][0] = 1;
		boardTest[3][0] = 1;
		boardTest[1][1] = 2;
		boardTest[2][2] = 2;
		boardTest[3][3] = 2;
		game.setBoard(boardTest);
		
		assertEquals(game.checkWinner(), new Integer(1));
		assertNotEquals(game.checkWinner(), new Integer(2));
	}
	
	@Test
	public void test4StraightDiagonal1() {
		ConnectFour game = new ConnectFour(new Scanner(System.in));
	
		// Test 4 straight diagonal 1
		Integer[][] boardTest = new Integer[6][7];
		boardTest[0][1] = 1;
		boardTest[1][2] = 1;
		boardTest[2][3] = 1;
		boardTest[3][4] = 1;
		boardTest[1][1] = 2;
		boardTest[2][2] = 2;
		boardTest[3][3] = 2;
		game.setBoard(boardTest);
		
		assertEquals(game.checkWinner(), new Integer(1));
		assertNotEquals(game.checkWinner(), new Integer(2));
	}
	
	@Test
	public void test4StraightDiagonal2() {	
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		
		// Test 4 straight diagonal 1
		Integer[][] boardTest = new Integer[6][7];
		boardTest[0][4] = 1;
		boardTest[1][3] = 1;
		boardTest[2][2] = 1;
		boardTest[3][1] = 1;
		boardTest[1][1] = 2;
		boardTest[2][1] = 2;
		boardTest[3][3] = 2;
		game.setBoard(boardTest);
		
		assertEquals(game.checkWinner(), new Integer(1));
		assertNotEquals(game.checkWinner(), new Integer(2));
	}
	
	@Test
	public void testNoWinner() {	
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		
		// Test no winner
		Integer[][] boardTest = new Integer[6][7];
		boardTest[0][4] = 1;
		boardTest[1][6] = 1;
		boardTest[2][2] = 1;
		boardTest[3][1] = 1;
		boardTest[1][1] = 2;
		boardTest[2][1] = 2;
		boardTest[3][3] = 2;
		game.setBoard(boardTest);
		
		assertEquals(game.checkWinner(), new Integer(0));
		assertNotEquals(game.checkWinner(), new Integer(1));
		assertNotEquals(game.checkWinner(), new Integer(2));
	}
	
	@Test
	public void testDropDisc() {
			
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		game.dropDisc(1,0);
		assertEquals(game.checkWinner(), new Integer(0));
		game.dropDisc(2,0);
		assertEquals(game.checkWinner(), new Integer(0));
		game.dropDisc(1,1);
		assertEquals(game.checkWinner(), new Integer(0));
		game.dropDisc(2,1);
		assertEquals(game.checkWinner(), new Integer(0));
		game.dropDisc(1,2);
		assertEquals(game.checkWinner(), new Integer(0));
		game.dropDisc(2,2);
		assertEquals(game.checkWinner(), new Integer(0));
		game.dropDisc(1,3);
		assertEquals(game.checkWinner(), new Integer(1));
	}
	
	@Test
	public void testInput() {
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		assertTrue(game.TestInput(0));
		assertTrue(game.TestInput(1));
		assertTrue(game.TestInput(2));
		assertFalse(game.TestInput(Integer.MAX_VALUE));
		assertFalse(game.TestInput(Integer.MIN_VALUE));
	}
	
	@Test
	public void testColumnFull() {
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		game.dropDisc(1,5);
		game.dropDisc(1,5);
		game.dropDisc(1,5);
		game.dropDisc(1,5);
		game.dropDisc(1,5);
		game.dropDisc(1,5);

		assertFalse(game.TestInput(5));
		assertTrue(game.TestInput(2));
		
	}
	
	@Test
	public void testBoardFull() {
		ConnectFour game = new ConnectFour(new Scanner(System.in));
		game.dropDisc(1,0);
		game.dropDisc(2,0);
		game.dropDisc(1,0);
		game.dropDisc(2,0);
		game.dropDisc(1,0);
		game.dropDisc(2,0);
		
		game.dropDisc(1,1);
		game.dropDisc(2,1);
		game.dropDisc(1,1);
		game.dropDisc(2,1);
		game.dropDisc(1,1);
		game.dropDisc(2,1);

		game.dropDisc(1,2);
		game.dropDisc(2,2);
		game.dropDisc(1,2);
		game.dropDisc(2,2);
		game.dropDisc(1,2);
		game.dropDisc(2,2);

		game.dropDisc(2,3);
		game.dropDisc(1,3);
		game.dropDisc(2,3);
		game.dropDisc(1,3);
		game.dropDisc(2,3);
		game.dropDisc(1,3);

		game.dropDisc(1,4);
		game.dropDisc(2,4);
		game.dropDisc(1,4);
		game.dropDisc(2,4);
		game.dropDisc(1,4);
		game.dropDisc(2,4);

		game.dropDisc(1,5);
		game.dropDisc(2,5);
		game.dropDisc(1,5);
		game.dropDisc(2,5);
		game.dropDisc(1,5);
		game.dropDisc(2,5);

		game.dropDisc(1,6);
		game.dropDisc(2,6);
		game.dropDisc(1,6);
		game.dropDisc(2,6);
		game.dropDisc(1,6);
		game.dropDisc(2,6);
		
		assertTrue(game.boardIsFull());
		
	}

}

package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class model {
	// Game Board

	private char[][] board = new char[3][3];
	
	//Keeps track if player is X or O
	boolean xPlayer = true;
	ArrayList<char[][]> gameState = new ArrayList<char[][]>();
	
	
	public model(){
		boolean fullpass = false;
		initialize();
		for(int i = 0; i < 9; i++){
			generalTurn();
			if(gameFinished()){
			
				break;
			}else if(i == 8){
				fullpass = true;
			}
		}
		if(xPlayer){
			System.out.println("Player O is the winner!");
		}else if(fullpass){
			System.out.println("DRAW");
		}else{
			
			System.out.println("Player X is the winner!");
		}
	}
	// Sets all Characters in array to empty
	 void initialize(){
		for(int i = 0; i < 3; i++){
			for(int k = 0; k < 3; k++){
				board[i][k] = ' ';
			}
		}
	}
	 // Asks player for input
	 public void generalTurn(){
		 if(xPlayer){
		 System.out.println("Player X, enter a position to place your character, NO COMMA");
		 }else{
			 System.out.println("Player O, enter a position to place your character, NO COMMA");
		 }
		 Scanner scan = new Scanner(System.in);
		 int number  = scan.nextInt();
		 int idxOne = number / 10;
		 int idxTwo = number % 10;
		 if(!emplace(idxOne,idxTwo)){
			 generalTurn();
		 }
		 gameState.add(board);
		 printBoard();
		 xPlayer = !xPlayer;
		 
	 }
	 
	 boolean emplace(int x, int y){
		 if(x > 2 || y > 2 || x < 0 || y < 0){
			 System.out.println("OUT OF BOUNDS!, TRY AGAIN");
			 return false;
		 }
		 if(board[x][y] != ' '){
			 System.out.println("Something is already there. Try Again");
			 return false;
			 
		 }
		 else{
			 if(xPlayer){
			 board[x][y] = 'x';
			 return true;
		 }else{
			 board[x][y] = 'o'; 
			 return true;
		 }
		 
	 }
	}
	// CHECKS IF GAME IS FINISHED
	boolean gameFinished(){
	boolean finished = false;
	//Diagonal Check
		finished = (board[0][0] == board[1][1] && board[0][0] == board[2][2]) || (board[0][2] == board[1][1] && board[2][0] == board[1][1]) && board[1][1] != ' ';
		
		if(finished){
			return true;
		}
	//Horizontal Check
		for(int i = 0; i < 3; i++){
			finished = board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ';
			if(finished){
				return true;
			}
		}
		// Vertical Check
		
		for(int i = 0; i < 3; i++){
			finished = board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[i][0] != ' ';
			if(finished){
				return true;
			}
		}
		return false;
	}

	//PRINTS BOARD AFTER EVERY TURN
	public void printBoard(){
		System.out.print("[" + board[0][0]+ "]");System.out.print("[" +board[0][1] + "]");System.out.println("[" + board[0][2] + "]");
		System.out.print("[" + board[1][0]+ "]");System.out.print("[" +board[1][1] + "]");System.out.println("[" + board[1][2] + "]");
		System.out.print("[" + board[2][0]+ "]");System.out.print("[" +board[2][1] + "]");System.out.println("[" + board[2][2] + "]");
	}
		
}

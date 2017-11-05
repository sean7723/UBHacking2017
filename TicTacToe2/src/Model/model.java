package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class model {
	// Game Board
	FileWriter writer;
	
	private char[][] board = new char[3][3];
	
	//Keeps track if player is X or O
	boolean xPlayer = true;
	ArrayList<char[][]> gameState = new ArrayList<char[][]>();
	ArrayList<String> dataList = new ArrayList<String>();
	
	public void fillList(){
		String data = "";
		for(int x = 0; x < gameState.size(); x++){
		for(int i= 0; i < 3; i++ ){
			for(int k = 0; k < 3; k++){
				if(gameState.get(x)[i][k] == ' '){
					data+= "0,";
				}
				if(gameState.get(x)[i][k] == 'x'){
					if(!xPlayer){
					data+="1,";
					}else{
						data+="2,";
					}
				}
				if(gameState.get(x)[i][k] == 'o'){
					if(!xPlayer){
						data+="2,";
						}else{
							data+="1,";
						}
				}
			}
		}
		dataList.add(data);
		data = "";
		}
		
	
		

	}
	public void writeToFile(String filename) throws IOException{
		FileWriter file = new FileWriter(filename, true);
		for(int i = 0; i < dataList.size(); i++){
			file.write(dataList.get(i) + "\r\n");
		}
		file.close();
	}
	public model() throws IOException{
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
		fillList();
		for(int i = 0; i < dataList.size(); i++){
			System.out.println(dataList.get(i));
		}
		if(xPlayer){
			System.out.println("Player O is the winner!");
		}else if(fullpass){
			System.out.println("DRAW");
		}else{
			
			System.out.println("Player X is the winner!");
		}
		String location = "data.csv";
		writeToFile(location);
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
			 char[][] boardCopy = new char[3][3];
			 for(int i = 0; i < 3; i++){
				 for(int k = 0; k< 3; k++){
					 boardCopy[i][k] = board[i][k];
				 }
			 }
			 if(xPlayer){
			 boardCopy[x][y] = 'x';
			 board[x][y] = 'x';
			 printBoard();
			 xPlayer = !xPlayer;
			 printArray(boardCopy);
			 gameState.add(boardCopy);
			 System.out.println(gameState.size());
			 printArray(gameState.get(0));
			 return true;
		 }else{
			 
			 boardCopy[x][y] = 'o';
			 board[x][y] = 'o';
			 printBoard();
			 xPlayer = !xPlayer;
			 printArray(boardCopy);
			 gameState.add(boardCopy);
			 
			 System.out.println(gameState.size());
			 printArray(gameState.get(0));
			 return true;
		 }
		 
	 }
	}
	// CHECKS IF GAME IS FINISHED
	boolean gameFinished(){
	boolean finished = false;
	//Diagonal Check
		finished = (board[0][0] == board[1][1] && board[0][0] == board[2][2]) && board[2][2] != ' ' || ((board[0][2] == board[1][1] && board[2][0] == board[1][1]) && board[0][2] != ' ');
		
		if(finished){
			return true;
		}
	//Horizontal Check
		for(int i = 0; i < 3; i++){
			finished = (board[i][0] == board[i][1] && board[i][1] == board[i][2]) && board[i][0] != ' ';
			if(finished){
				return true;
			}
		}
		// Vertical Check
		
		for(int i = 0; i < 3; i++){
			finished = (board[0][i] == board[1][i] && board[1][i] == board[2][i]) && board[0][i] != ' ';
			if(finished){;
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
	
	public void printArray(char[][] array){
		for(int i = 0; i < 3; i++){
			for(int k = 0; k < 3; k++){
				System.out.print(array[i][k]);
			}
		}
		
	}
		
}

import api.GameEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("GAME START");
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        while(!gameEngine.isComplete(board).isOver()){
            System.out.println("Make your Move");
            Player computer = new Player("O");
            Player opponent = new Player("X");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move opponentMove = new Move(new Cell(row,col));
            gameEngine.move(board,opponent, opponentMove);
            System.out.println(board);
            if(!gameEngine.isComplete(board).isOver()){
                Move computerMove = gameEngine.suggestMove(computer,board);
                gameEngine.move(board,computer, computerMove);
                System.out.println(board);
            }
        }

        System.out.println("Game winner is " + gameEngine.isComplete(board).getWinner());
    }

}

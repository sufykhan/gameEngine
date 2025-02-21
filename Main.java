import api.AIPlayer;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("GAME START");
        GameEngine gameEngine = new GameEngine();
        AIPlayer gameManager = new AIPlayer();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        while(!ruleEngine.getState(board).isOver()){
            System.out.println("Make your Move");
            Player computer = new Player("O");
            Player opponent = new Player("X");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move opponentMove = new Move(opponent,new Cell(row,col));
            gameEngine.move(board,opponentMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver()){
                Move computerMove = gameManager.suggestMove(computer,board);
                gameEngine.move(board,computerMove);
                System.out.println(board);
            }
        }

        System.out.println("Game winner is " + ruleEngine.getState(board).getWinner());
    }

}

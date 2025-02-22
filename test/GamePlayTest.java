package test;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    public void playGame(Board board, int [][] firstPlayerMoves, int [][] secondPlayerMoves){
        int firstPlayerRow,firstPlayerCol,secondPlayerRow,secondPlayerCol,index = 0;
        while(!ruleEngine.getState(board).isOver()){
             System.out.println("Make your Move");
            Player computer = new Player("O");
            Player human = new Player("X");
            firstPlayerRow = firstPlayerMoves[index][0];
            firstPlayerCol = firstPlayerMoves[index][1];
            secondPlayerRow = secondPlayerMoves[index][0];
            secondPlayerCol = secondPlayerMoves[index][1];
            Move opponentMove = new Move(human,new Cell(firstPlayerRow,firstPlayerCol));
            Move computerMove =  new Move(computer,new Cell(secondPlayerRow,secondPlayerCol));
            gameEngine.move(board,opponentMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver()){
                gameEngine.move(board,computerMove);
                System.out.println(board);
            }
            index++;
        }
    }
    @Test
    public void checkForRowWin (){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves = new int[][]{{2,0},{2,1},{2,2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }
    @Test
    public void checkForColWin (){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0,2},{1,2},{2,2}};
        int[][] secondPlayerMoves = new int[][]{{0,1},{1,1},{2,1}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForDiagonalWin (){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0,0},{1,1},{2,2}};
        int[][] secondPlayerMoves = new int[][]{{0,2},{1,0},{2,0}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForReverseDiagonalWin (){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0,2},{1,1},{2,0}};
        int[][] secondPlayerMoves = new int[][]{{1,0},{2,1},{2,2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("X", ruleEngine.getState(board).getWinner());
    }

    @Test
    public void checkForComputerWin (){
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{2,0},{2,1},{0,2}};
        int[][] secondPlayerMoves = new int[][]{{1,0},{1,1},{1,2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals("O", ruleEngine.getState(board).getWinner());
    }
}

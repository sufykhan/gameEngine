package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameResult;
import game.Move;
import game.Player;

public class Main {
    public static void main(String[] args) {
        System.out.println("Suiiiii");
    }
    public Board start(){
        return new Board();
    }

   public void move(Board board, Player player, Move move){

   }

   public GameResult isComplete(Board board){
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                rowComplete = true;
                String firstCharacter = board1.getCell(i,0);
                for (int j = 1; j < 3; j++) {
                    if (!firstCharacter.equals(board1.getCell(i,j))) {
                        rowComplete = false;
                        break;
                    }
                }
                if (rowComplete) {
                    return new GameResult(true, firstCharacter);
                }
            }
            boolean colComplete = true;
            for (int i = 0; i < 3; i++) {
                colComplete = true;
                String firstCharacter = board1.getCell(i,0);
                for (int j = 1; j < 3; j++) {
                    if (!firstCharacter.equals(board1.getCell(j,i))) {
                        colComplete = false;
                        break;
                    }
                }
                if (colComplete) {
                    return new GameResult(true, firstCharacter);
                }
            }
            boolean diagonalComplete = true;
            String firstCharacter = board1.getCell(0,0);
            for (int i = 1; i < 3; i++) {
                if (!firstCharacter.equals(board1.getCell(i,i))) {
                    diagonalComplete = false;
                    break;
                }
            }
            if (diagonalComplete) {
                return new GameResult(true, firstCharacter);
            }
            boolean reverseDiagonalComplete = true;
            firstCharacter = board1.getCell(0,2);
            for (int i = 0; i < 2; i++) {
                if (!firstCharacter.equals(board1.getCell(3 - i - 1 ,3 - i - 1))) {
                    reverseDiagonalComplete = false;
                    break;
                }

            }
            if (reverseDiagonalComplete) {
                return new GameResult(true, firstCharacter);
            }

            int count=0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i,j)!=null){
                        count ++;
                    }
                }
            }
            if(count == 9){
                return new GameResult(true,"-");
            } else{
                return new GameResult(false,"-");
            }

        } else {
            return new GameResult(true, "-");
        }
   }


}

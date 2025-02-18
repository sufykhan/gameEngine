package api;

import boards.TicTacToeBoard;
import game.*;

public class GameEngine {
    public Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        } else{
            throw new IllegalArgumentException();
        }
    }

   public void move(Board board, Player player, Move move){
        if(board instanceof TicTacToeBoard board1){
            board1.setCell(player.symbol(), move.getCell());
        } else{
            throw new IllegalArgumentException();
        }
   }

   public Move suggestMove( Player player,Board board){
        if(board instanceof TicTacToeBoard board1){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i,j)==null){
                        return new Move(new Cell(i,j));
                    }
                }
            }
            throw new IllegalStateException();
        } else{
            throw new IllegalArgumentException();
        }
    }

    public GameResult isComplete(Board board){
        if(board instanceof TicTacToeBoard board1) {
            boolean rowComplete = true;
            for (int i = 0; i < 3; i++) {
                String firstCharacter = board1.getCell(i,0);
                rowComplete = firstCharacter!=null;
                if(firstCharacter!=null){
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(i,j))) {
                            rowComplete = false;
                            break;
                        }
                    }
                }
                if (rowComplete) {
                    return new GameResult(true, firstCharacter);
                }
            }
            boolean colComplete = true;
            for (int i = 0; i < 3; i++) {
                String firstCharacter = board1.getCell(i,0);
                colComplete =  firstCharacter!=null;
                if(firstCharacter!=null){
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(j,i))) {
                            colComplete = false;
                            break;
                        }
                    }
                }
                if (colComplete) {
                    return new GameResult(true, firstCharacter);
                }
            }

            String firstCharacter = board1.getCell(0,0);
            boolean diagonalComplete = firstCharacter!=null;
            if(firstCharacter!=null){
                for (int i = 1; i < 3; i++) {
                    if (!firstCharacter.equals(board1.getCell(i,i))) {
                        diagonalComplete = false;
                        break;
                    }
                }
            }

            if (diagonalComplete) {
                return new GameResult(true, firstCharacter);
            }
            firstCharacter = board1.getCell(0,2);
            boolean reverseDiagonalComplete = firstCharacter!=null;
            if(firstCharacter!=null){
                for (int i = 0; i < 2; i++) {
                    if (!firstCharacter.equals(board1.getCell(3 - i - 1 ,3 - i - 1))) {
                        reverseDiagonalComplete = false;
                        break;
                    }
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

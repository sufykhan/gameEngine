package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.function.BiFunction;

public class RuleEngine {
    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard board1) {
            BiFunction<Integer,Integer,String> getCell = board1::getSymbol;
            BiFunction<Integer,Integer,String> getCol = (i,j)->board1.getSymbol(j,i);

            GameState rowWin = isVictorious(getCell);
            if(rowWin!=null) return rowWin;

            GameState colWin = isVictorious(getCol);
            if(colWin!=null) return colWin;

            boolean diagonalComplete = true;
            for (int i = 0; i < 3; i++) {
                if (getCell.apply(i,i)== null || !getCell.apply(0,0).equals(getCell.apply(i,i))) {
                    diagonalComplete = false;
                    break;
                }
            }
            if (diagonalComplete) {
                return new GameState(true, getCell.apply(0,0));
            }

            boolean reverseDiagonalComplete =true;
            for (int i = 0; i < 3; i++) {
                if (getCell.apply(i,3 - i - 1)== null || !getCell.apply(0,2).equals(getCell.apply(i ,3 - i - 1))) {
                    reverseDiagonalComplete = false;
                    break;
                }
            }
            if (reverseDiagonalComplete) {
                return new GameState(true, getCell.apply(0,2));
            }

            int count=0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i,j)!=null){
                        count ++;
                    }
                }
            }
            if(count == 9){
                return new GameState(true,"-");
            } else{
                return new GameState(false,"-");
            }

        } else {
            return new GameState(true, "-");
        }
    }
    public GameState isVictorious(BiFunction<Integer, Integer,String> next){
        for (int i = 0; i < 3; i++) {
            boolean possibleStreak = true;
            for (int j = 0; j < 3; j++) {
                if (next.apply(i,j)==null || !next.apply(i,0).equals(next.apply(i,j))) {
                    possibleStreak = false;
                    break;
                }
            }
            if (possibleStreak) {
                return new GameState(true, next.apply(i,0));
            }
        }
        return null;
    }
}

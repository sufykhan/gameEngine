package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard board1) {

            BiFunction<Integer,Integer,String> getRow = board1::getSymbol;
            BiFunction<Integer,Integer,String> getCol = (i,j)->board1.getSymbol(j,i);
            Function<Integer,String> getDiagonal = (i)->board1.getSymbol(i,i);
            Function<Integer,String> getRevDiagonal = (i)->board1.getSymbol(i,3-i-1);

            GameState rowWin = findStreak(getRow);
            if(rowWin!=null) return rowWin;

            GameState colWin = findStreak(getCol);
            if(colWin!=null) return colWin;

            GameState diagonalWin = findDiagonalStreak(getDiagonal);
            if(diagonalWin!=null) return diagonalWin;

            GameState reverseDiagonalWin = findDiagonalStreak(getRevDiagonal);
            if(reverseDiagonalWin!=null) return reverseDiagonalWin;

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

    public GameState findStreak(BiFunction<Integer, Integer,String> next){
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

    public GameState findDiagonalStreak(Function<Integer,String> next){
        boolean possibleStreak = true;
        for (int i = 0; i < 3; i++) {
            if (next.apply(i)== null || !next.apply(0).equals(next.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            return new GameState(true, next.apply(0));
        }
        return null;
    }
}

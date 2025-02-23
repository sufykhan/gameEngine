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

            GameState rowWin = outerTraversal(getRow);
            if(rowWin.isOver()) return rowWin;

            GameState colWin = outerTraversal(getCol);
            if(colWin.isOver()) return colWin;

            GameState diagonalWin = traverse(getDiagonal);
            if(diagonalWin.isOver()) return diagonalWin;

            GameState reverseDiagonalWin = traverse(getRevDiagonal);
            if(reverseDiagonalWin.isOver()) return reverseDiagonalWin;

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

    public GameState outerTraversal(BiFunction<Integer, Integer,String> next){
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Function<Integer,String> nextValue = (j) -> next.apply(finalI,j);
            GameState traversal = traverse(nextValue);
            if(traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    public GameState traverse(Function<Integer,String> next){
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int i = 0; i < 3; i++) {
            if (next.apply(i)== null || !next.apply(0).equals(next.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result =  new GameState(true, next.apply(0));
        }
        return result;
    }
}

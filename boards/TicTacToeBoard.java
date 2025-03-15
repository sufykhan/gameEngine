package boards;

import api.Rule;
import api.RuleSet;
import game.*;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard, Cloneable {
    String[][] cells = new String[3][3];
    History history = new History();

    public TicTacToeBoard() {
    }

    public enum Symbol{
        X("X"),O("O");

        final String symbol;

        Symbol (String o){
            this.symbol = o;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    private TicTacToeBoard(TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            this.cells[i] = Arrays.copyOf(board.cells[i], 3); // Deep copy of each row
        }
    }

    public History getHistory() {
        return history;
    }

    public String getSymbol(int i, int j){
        return this.cells[i][j];
    }

    public void setCell(Cell cell, String symbol) {
        if(cells[cell.getRow()][cell.getCol()] == null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else{
            throw new IllegalArgumentException();
        }
    }

    public static RuleSet getRules(){
        RuleSet rules  = new RuleSet();
        rules.add(new Rule((board)->outerTraversal(board::getSymbol)));
        rules.add(new Rule ((board)->outerTraversal((i,j)-> board.getSymbol(j,i))));
        rules.add(new Rule ((board)->traverse((i)-> board.getSymbol(i,i))));
        rules.add(new Rule ((board)->traverse((i)-> board.getSymbol(i,2-i))));
        rules.add(new Rule (TicTacToeBoard::countMoves));
        return rules;
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, ticTacToeBoard.cells[i], 0, 3);
        }
        ticTacToeBoard.history = history;
        return ticTacToeBoard;
    }


    @Override
    public TicTacToeBoard move(Move move){
        TicTacToeBoard boardCopy = copy();
        history.add(boardCopy);
        setCell(move.getCell(),move.getPlayer().symbol());
        return this;
    }

    public TicTacToeBoard dummyMove(Move move){
        TicTacToeBoard boardCopy = copy();
        boardCopy.setCell(move.getCell(),move.getPlayer().symbol());
        return boardCopy;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(cells[i][j]==null) result.append("-");
                else result.append(cells[i][j]);
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public TicTacToeBoard clone() {
        return new TicTacToeBoard(this);
    }


    public static GameState countMoves(CellBoard board1){
        int count=0;
        GameState gameState = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i,j)!=null){
                    count ++;
                }
            }
        }
        if(count == 9){
            gameState = new GameState(true,"-");
        }
        return gameState;
    }

    public static GameState outerTraversal(BiFunction<Integer, Integer, String> next){
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

    public static GameState traverse(Function<Integer, String> next){
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

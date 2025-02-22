package boards;

import game.Board;
import game.Cell;
import game.Move;

import java.util.Arrays;

public class TicTacToeBoard implements Board {
    String[][] cells = new String[3][3];

    public String getSymbol(int i,int j){
        return this.cells[i][j];
    }

    public void setCell(Cell cell, String symbol) {
        if(cells[cell.getRow()][cell.getCol()] == null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public TicTacToeBoard copy(){
        TicTacToeBoard ticTacToeBoard =  new TicTacToeBoard();

        for(int i=0;i<3;i++){
            ticTacToeBoard.cells[i] = Arrays.copyOf(this.cells[i], 3); // This the deep copy, no reference to other cells
/*
          for(int j=0;j<3;j++){               This is the shallow copy and contains the reference of original board
                 ticTacToeBoard.cells[i][j] = cells[i][j];
           }
*/
        }
        return ticTacToeBoard;
    }

    @Override
    public void move(Move move){
        setCell(move.getCell(),move.getPlayer().symbol());
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
}

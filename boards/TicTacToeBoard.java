package boards;

import game.Board;
import game.Move;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];

    public String getCell(int i,int j){
        return this.cells[i][j];
    }

    @Override
    public void move(Move move){
        cells[move.getCell().getRow()][move.getCell().getCol()]=move.getPlayer().symbol();
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

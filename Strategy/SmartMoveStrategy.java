package Strategy;

import api.RuleEngine;
import boards.TicTacToeBoard;
import game.*;


public class SmartMoveStrategy extends MoveStrategy{
    private final RuleEngine ruleEngine;

    public SmartMoveStrategy() {
        this.ruleEngine = new RuleEngine();
    }

    @Override
    public Cell move(Player player, TicTacToeBoard board) {
        Cell defense = defense(player,board);
        if(defense!=null) return defense;

        Cell offense = offense(player,board);
        if(offense!=null) return offense;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    return new Cell(i,j);
                }
            }
        }
        return null;
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i,j)==null){
                    Move move = new Move(player.flip(), new Cell(i,j));

                    Board boardCopy = board.dummyMove(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return  new Cell(i,j);
                    }
                }
            }
        }
        return null;
    }

    private Cell defense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(player.flip(), new Cell(i,j));
                    Board boardCopy = board.dummyMove(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i,j);
                    }
                }
            }
        }
        return null;
    }
}
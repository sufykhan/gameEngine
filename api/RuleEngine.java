package api;

import boards.TicTacToeBoard;
import game.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RuleEngine {
    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine(){
         ruleMap.put(TicTacToeBoard.class.getName(),TicTacToeBoard.getRules());
    }

    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard board1) {
            for(Rule<TicTacToeBoard> rule: ruleMap.get(TicTacToeBoard.class.getName())){
                GameState gameState = rule.condition.apply(board1);
                if(gameState.isOver()){
                    return gameState;
                }
            }
            return new GameState(false,"-");
        } else {
            return new GameState(true, "-");
        }
    }

    public GameInfo getInfo(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(ticTacToeBoard);
            final String [] players = new String[] {"X","O"};
            Cell forkCell = null;
            for (int index =0; index <2 ;index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board b = ((TicTacToeBoard) board).clone();
                        Player player = new Player(players [index]);
                        b.move(new Move(player,new Cell(i,j)));
                        boolean canStillWin = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                               Board b1 = ((TicTacToeBoard) b).clone();
                               forkCell= new Cell(k,l);
                               b1.move(new Move(player.flip(),new Cell(k,l)));
                               if(getState(b1).getWinner().equals(player.flip().symbol())){
                                   canStillWin = true;
                                   break;
                               }
                            }
                        }
                        if(canStillWin) {
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player.flip())
                                    .forkCell(forkCell)
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }
}



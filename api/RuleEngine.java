package api;

import boards.TicTacToeBoard;
import game.*;
import stateManager.DefensivePlacement;
import stateManager.OffensivePlacement;

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
            for(Rule rule: ruleMap.get(TicTacToeBoard.class.getName())){
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

    public GameInfo getInfo(CellBoard board) {
        if (board instanceof TicTacToeBoard ticTacToeBoard) {
            GameState gameState = getState(ticTacToeBoard);
            for(TicTacToeBoard.Symbol symbol : TicTacToeBoard.Symbol.values() ){
                Player player = new Player(symbol.getSymbol());
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(ticTacToeBoard.getSymbol(i,j)!=null) continue;
                        TicTacToeBoard ticTacToeBoard1 = ticTacToeBoard.dummyMove(new Move(player,new Cell(i,j)));
                        DefensivePlacement defensivePlacement = DefensivePlacement.get();
                        Optional<Cell> defensiveMove = defensivePlacement.place(ticTacToeBoard1,player.flip());
                        if(defensiveMove.isPresent()){
                            ticTacToeBoard1 = ticTacToeBoard1.dummyMove(new Move(player.flip(),defensiveMove.get()));
                            OffensivePlacement offensivePlacement = OffensivePlacement.get();
                            Optional<Cell> offensiveMove = offensivePlacement.place(ticTacToeBoard1,player);
                            if(offensiveMove.isPresent()){
                                return new GameInfoBuilder()
                                        .isOver(gameState.isOver())
                                        .winner(gameState.getWinner())
                                        .hasFork(true)
                                        .player(player)
                                        .forkCell(new Cell(i,j))
                                        .build();
                            }
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



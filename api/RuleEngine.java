package api;

import boards.TicTacToeBoard;
import game.Board;
import game.GameState;

import java.util.HashMap;
import java.util.Map;
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
}



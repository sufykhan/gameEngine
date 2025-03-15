package boards;

import game.Board;

import java.util.ArrayList;
import java.util.List;

public class History {

    List<Board> history = new ArrayList<>();

    public void add (Board board){
        history.add(board);
    }

    public Board undo(){
        history.removeLast();
        return history.getLast();
    }

    public Board getHistoryAtPos(int index){
        int n = history.size();
        for(int i=index+1;i<n;i++){
            history.removeLast();
        }
        return history.getLast();
    }

    public void printHistory(){
        for(int i=0;i<history.size();i++){
            Board board1 = history.get(i);
            System.out.println("board at index "+ i + "is -> " +  board1);
        }
    }
}

package api;

import game.Board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleSet <T extends Board> implements Iterable<Rule<T>>{
    List<Rule<T>> rules = new ArrayList<>();
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Rule<T>> iterator() {
        return rules.iterator();
    }

    public  void add (Rule<T> rule){
        rules.add(rule);
    }
}

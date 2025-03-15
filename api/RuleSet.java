package api;

import java.util.*;

public class RuleSet  implements Iterable<Rule>{
    Set<Rule> ruleList = new HashSet<>();
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Rule> iterator() {
        return ruleList.iterator();
    }

    public  void add (Rule rule){
        ruleList.add(rule);
    }
}

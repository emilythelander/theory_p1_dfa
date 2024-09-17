package fa.dfa;

import java.util.*;

import fa.State;

/**
 * The DFA class, which implements DFAInterface, is responsible for creating and
 * modifying the deterministic finite automata objects.
 * 
 * @author Emily Thelander
 * @author Spencer Pattillo
 */
public class DFA implements DFAInterface {

    LinkedHashSet<Character> sigma;
    LinkedHashSet<DFAState> states;
    String startState;
    LinkedHashSet<DFAState> finalStates;
    LinkedHashMap<DFAState, LinkedHashSet<DFAState>> transitionTable;

    /**
     * DFA constructor. Creates DFA objects and instantiates it with empty values.
     */
    public DFA() {

        sigma = new LinkedHashSet<>();
        states = new LinkedHashSet<>();
        startState = "";
        finalStates = new LinkedHashSet<>();
        transitionTable = new LinkedHashMap<>();
    }

    @Override
    public boolean addState(String name) {

        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                return false;
            }
        }

        return states.add(new DFAState(name));
    }

    @Override
    public boolean setFinal(String name) {

        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                return finalStates.add(state);
            }
        }

        return false;
    }

    @Override
    public boolean setStart(String name) {

        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                startState = name;
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {

        for (Character alnum : sigma) {
            if (alnum.equals(symbol)) {
                return;
            }
        }
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // TODO Still incomplete
        for (int i = 0; i < s.length(); i++) {
            if (!sigma.contains(s.charAt(i))) {
                return false;
            }
        }

        if (startState == "") {
            return false;
        }

        //Do another loop through the string
        //Need to 
        return true;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        for (State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        for (State state : finalStates) {
            if (state.getName().equals(name)) {
                return state;
            }
        }

        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for (DFAState state : finalStates) {
            if (state.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        if (name.equals(startState)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        // TODO Still incomplete

        //Check that onSymb is in sigma
        if (!sigma.contains(onSymb)) {
            return false;
        }

        //There's probably a more efficient/better way to do these checks but I'm exhausted
        boolean fromExists = false;
        boolean toExists = false;
        for (DFAState state : states) {
            if (state.getName().equals(fromState)) {
                fromExists = true;
            } 
        }

        for (DFAState state : states) {
            if (state.getName().equals(toState)) {
                toExists = true;
            } 
        }

        if (!fromExists || !toExists) {
            return false;
        }

        return true;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }

}

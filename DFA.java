package fa.dfa;

import java.util.*;

import fa.State;

public class DFA implements DFAInterface {

    LinkedHashSet<Character> sigma;
    LinkedHashSet<DFAState> states;
    String startState;
    LinkedHashSet<DFAState> finalStates;
    LinkedHashMap<DFAState, LinkedHashSet<DFAState>> transitionTable;

    public DFA() {

    sigma = new LinkedHashSet<>();
    states = new LinkedHashSet<>();
    startState = "";
    finalStates = new LinkedHashSet<>();
    transitionTable = new LinkedHashMap<>();

    }

    @Override
    public boolean addState(String name) {

        return states.add(new DFAState(name));
    }

    @Override
    public boolean setFinal(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                states.remove(state);
                return finalStates.add(state);
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        for (DFAState state : states) {
            if (state.getName().equals(name)) {
                states.remove(state);
                startState = name;
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        for (State state : states){
            if (state.getName().equals(name)){
                return state;
            }
        }
        for (State state : finalStates){
            if (state.getName().equals(name)){
                return state;
            }
        }

        return null;
    }

    @Override
    public boolean isFinal(String name) {
        if (finalStates.contains(name)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        if (name.equals(startState)){
            return true;
        }
        return false;
    }

    @Override
    public boolean addTransition(String fromState, String toState, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }

}

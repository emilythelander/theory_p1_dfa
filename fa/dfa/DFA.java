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
    LinkedHashMap<DFAState, ArrayList<Map<Character,DFAState>>> transitionTable;

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
        for (int i = 0; i < s.length(); i++) {
            if (!sigma.contains(s.charAt(i))) {

                return false;
            }


        }
        State currentState = getState(startState);

        if (startState.equals("")) {
            return false;
        }

        for (int i = 0; i < s.length(); i++){


            List<Map<Character,DFAState>> transitions = transitionTable.get(currentState);

            if (transitions != null){
                for (Map<Character, DFAState> transition : transitions){
                    if (transition.containsKey(s.charAt(i))){
                        currentState = transition.get(s.charAt(i));

                    }
                }
            }
        }
        
        return finalStates.contains(currentState);

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

        boolean fromExists = false;
        boolean toExists = false;
        DFAState toState2 = null;
        DFAState fromState2 = null;

        for (DFAState state : states) {
            if (state.getName().equals(toState)) {
                fromExists = true;
                toState2 = state;

                break;
            }
        }

        for (DFAState state : states) {
            if (state.getName().equals(fromState)) {
                toExists = true;
                fromState2 = state;

                break;
            }

        }




        if (!fromExists || !toExists) {
            return false;
        }

        HashMap<Character, DFAState> transitions = new HashMap<>();
        transitions.put(onSymb,toState2);
        if (transitionTable.containsKey(fromState2)){

            transitionTable.get(fromState2).add(transitions);


        }
        if (!transitionTable.containsKey(fromState2)){
            ArrayList<Map<Character,DFAState>> list = new ArrayList<>();
            list.add(transitions);
            transitionTable.put(fromState2,list);

        }

        System.out.println("doing all the changes" + transitionTable);

        return true;
    }

    @Override
    public DFA swap(char symb1, char symb2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }

}

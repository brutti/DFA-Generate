import java.util.*;

class Main {
    static Map<State, ArrayList<Transition>> dictMap = new LinkedHashMap<>();
    static ArrayList<Transition> T = new ArrayList<Transition>();
    static ArrayList<State> S = new ArrayList<State>();
    static int label = 0;

    public static void main(String[] args) {

        ArrayList<String> lines = null;
        Input in = new Input("entrada1.txt");
        lines = in.read();

        for (String line : lines) {
            System.out.println(line);
            if(line.charAt(0) == '<') {
                Grammar(line);
            } else {
                Token(line);
            }
        }

    }

    private static void Grammar(String line) {

    }

    private static void Token(String line) {
        ArrayList<Transition> tT;
        State s;
        Transition t;
        for(int count = 0; count < line.length(); count++) {
            tT = new ArrayList<>();
            s = new State(Integer.toString(label), true, false);
            t = new Transition(line.charAt(count));
            t.transitions.add(Integer.toString(label+1));
            S.add(s);
            T.add(t);
            tT.add(t);
            dictMap.put(s, tT);
            label++;
            s = null;
            t = null;
            tT = null;
        }

        for(Map.Entry<State, ArrayList<Transition>> entry : dictMap.entrySet()) {
            State temp_s = entry.getKey();
            ArrayList<Transition> temp_t = entry.getValue();
            System.out.print(temp_s.getLabel() + " ");
            for(Transition ti : temp_t) {
                System.out.println(ti.transitions);
            }
        }
    }

}

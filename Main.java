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

        /* Exibe o automato */
        for(Map.Entry<State, ArrayList<Transition>> entry : dictMap.entrySet()) {
            State temp_s = entry.getKey();
            ArrayList<Transition> temp_t = entry.getValue();
            System.out.print(temp_s.getLabel() + "\t");
            for(Transition ti : temp_t) {
                System.out.print(ti.getTrigger() + "\t" + ti.transitions + "\t");
            }
            System.out.println();
        }

    }

    private static void Grammar(String line) {

    }

    private static void Token(String line) {
        ArrayList<Transition> tT;
        State s;
        Transition t;
        boolean flag = false;

        for(int count = 0; count < line.length(); count++) {
            tT = new ArrayList<>();
            if(S.isEmpty()) {
                s = new State(Integer.toString(label), true, false);
            } else {
                if(count == 0) {
                    s = S.get(0);
                    tT = dictMap.get(s);
                    for(Transition taux : tT) {
                        if(taux.getTrigger() == line.charAt(count)) {
                            taux.transitions.add(Integer.toString(label));
                            State sa = new State(Integer.toString(label), false, false);
                            flag = true;
                            break;
                        }
                    }
                } else {
                    s = new State(Integer.toString(label), false, false);
                }
            }
            if(!flag) {
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
                flag=false;
        }
        s = new State(Integer.toString(label), false, true);
        tT = new ArrayList<>();
        tT.add(new Transition(' '));
        S.add(s);
        dictMap.put(s, tT);
        label++;
    }

}

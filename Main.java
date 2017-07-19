import java.util.*;

class Main {
    static Map<State, ArrayList<Transition>> dictMap = new LinkedHashMap<>();
    static ArrayList<Transition> T = new ArrayList<Transition>();
    static ArrayList<State> S = new ArrayList<State>();
    static int label = 0;

    public static void main(String[] args) {

        ArrayList<String> lines = null;
        Input in = new Input("entrada.txt");
        lines = in.read();

        for (String line : lines) {
            System.out.println(line);
            if(line.charAt(0) == '<') {
                Grammar(line);
            } else if(line.charAt(0) == '-') {
                label++;
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
        /*ArrayList<Character> nt = getAlphabetSet();
        for(int i = 0; i < nt.size(); i++) {
            System.out.println(nt.get(i));
        }*/
    }

    private static void Grammar(String line) {
        State sa = null;
        State s = null;
        ArrayList<Transition> tT = new ArrayList<>();
        boolean flag = false;
        char terminal = 0;

        for(int count = 0; count < line.length(); count++) {
            if(Character.isUpperCase(line.charAt(count))) {
                if(!flag) {
                    // Trata-se do estado inicial
                    if(line.charAt(count) == 'S') {
                        s = S.get(0);
                        tT = dictMap.get(s);
                    }
                    else {
                        //Verificar se o estado já foi mapeado, fazer a busca no hashmap
                        //s = new State(Integer.toString(label), false, false);
                    }
                    flag = true;
                }
            }
            else if(Character.isLowerCase(line.charAt(count))) {
                // terminal
                boolean encontrou = false;
                terminal = line.charAt(count);
                sa = new State(Integer.toString(label), false, false);
                System.out.println("terminal: " + terminal + s.getLabel());
                for(Transition t_temp : tT) {
                    if(t_temp.getTrigger() == line.charAt(count)) {
                        t_temp.transitions.add(Integer.toString(label));
                        encontrou = true;
                        break;
                    }
                }
                if(!encontrou) {
                    // É preciso criar uma nova transicao e inseri-la no HashMap
                    Transition tz = new Transition(line.charAt(count));
                    tz.transitions.add(sa.getLabel());
                    tT.add(tz);
                    dictMap.put(s, tT);
                    if(!stateCompare(sa)) dictMap.put(sa, new ArrayList<Transition>());
                }
            }
        }
        label++;
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
                            label++;
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
            }
            flag = false;
        }
        s = new State(Integer.toString(label), false, true);
        tT = new ArrayList<>();
        tT.add(new Transition(' '));
        S.add(s);
        dictMap.put(s, tT);
    }

    /* Método personalizado para comparar Estados pelo valor
       do atributo this.label
    */
    public static boolean stateCompare(State second) {
        for (State s : dictMap.keySet()) {
            if(s.getLabel().equals(second.getLabel())) return true;
        }
        return false;
    }

    public static ArrayList<Character> getAlphabetSet() {
        ArrayList<Character> alphaSet = new ArrayList<>();
        for (ArrayList<Transition> getTr : dictMap.values()) {
            for(Transition temp_t : getTr) {
                if(!alphaSet.contains(temp_t.getTrigger()))
                    alphaSet.add(temp_t.getTrigger());
            }
        }
        return alphaSet;
    }

}

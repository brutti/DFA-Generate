import java.util.*;

class Transition {
    private char trigger;
    public ArrayList<String> transitions;

    public Transition(char t) {
        this.trigger = t;
        transitions = new ArrayList<String>();
    }

    public char getTrigger() {
        return this.trigger;
    }
    public void setTrigger(char t) {
        this.trigger = t;
    }
}

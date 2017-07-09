class State {
    private String label;
    private boolean start;
    private boolean end;

    public State() { }

    public State(String label, boolean start, boolean end) {
        this.label = label;
        this.start = start;
        this.end   = end;
    }

    public String getLabel() {
        return this.label;
    }
    public void setLabel(String l) {
        this.label = l;
    }

    public boolean getStart() {
        return this.start;
    }
    public void setStart(boolean s) {
        this.start = s;
    }

    public boolean getEnd() {
        return this.end;
    }
    public void setEnd(boolean e) {
        this.end = e;
    }

}

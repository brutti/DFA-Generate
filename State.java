class State {
    private String label;
    private boolean start;
    private boolean end;

    public State() { }

    public State(String label, boolean start, boolean end) {
        this.label = label;
        this.start = start;
        this.end   = end;
        this.setStart(this.start);
        this.setEnd(this.end);
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
        if(this.start) {
            this.label = ("->").concat(this.label);
        }
        this.start = s;
    }

    public boolean getEnd() {
        return this.end;
    }
    public void setEnd(boolean e) {
        if(this.end) {
            this.label = ("*").concat(this.label);
        }
        this.end = e;
    }

}

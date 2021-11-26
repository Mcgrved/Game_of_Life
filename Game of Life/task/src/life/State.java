package life;

public enum State {
    ALIVE('O'), DEAD(' ');

    private char sign;

    State(char sign) {
        this.sign = sign;
    }
    public char getSign() {
        return sign;
    }
}

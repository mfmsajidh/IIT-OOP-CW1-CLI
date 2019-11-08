package iit.oop.cw.shell;

import org.jline.terminal.Terminal;

public class ProgressCounter {

    private static final String CUU = "\u001B[A";

    private Terminal terminal;
    private char[] spinner = {'|', '/', '-', '\\'};

    private int spinCounter = 0;

    public ProgressCounter(Terminal terminal) {
        this(terminal, null);
    }

    public ProgressCounter(Terminal terminal, char[] spinner) {
        this.terminal = terminal;
        if (spinner != null) {
            this.spinner = spinner;
        }
    }

    public void display() {
        if (!started) {
            terminal.writer().println();
            started = true;
        }
        terminal.writer().println(CUU + "\r" + getSpinnerChar());
    }

    public void reset() {
        spinCounter = 0;
        started = false;
    }

    private char getSpinnerChar() {
        char spinChar = spinner[spinCounter];
        spinCounter++;
        if (spinCounter == spinner.length) {
            spinCounter = 0;
        }
        return spinChar;
    }

    // Getters & Setters
    public char[] getSpinner() {
        return spinner;
    }

    public void setSpinner(char[] spinner) {
        this.spinner = spinner;
    }
}

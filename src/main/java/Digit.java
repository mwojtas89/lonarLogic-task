import java.util.List;

public class Digit {
    private int digit;
    private int position;

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Digit(int digit, int position) {
        this.digit = digit;
        this.position = position;
    }

    @Override
    public String toString() {
        return "" + digit + " " + position;
    }
}

package solution_elements;

import java.util.List;

public class Number {
    private List<Digit> digits;
    private int completeNumber;

    public Number(List<Digit> digits, int completeNumber) {
        this.digits = digits;
        this.completeNumber=completeNumber;
    }

    public void setCompleteNumber(int completeNumber) {
        this.completeNumber = completeNumber;
    }

    public List<Digit> getDigits() {
        return digits;
    }

    public int getCompleteNumber() {
        return completeNumber;
    }

    @Override
    public String toString() {
        return "digits :" + digits +
                ", completeNumber=" + completeNumber;
    }
}

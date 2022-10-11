import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.*;

public class NumberFromArray {
    private int number;
    private int counter=0;

    public NumberFromArray(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getCounter() {
        return counter;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return ""+number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberFromArray that = (NumberFromArray) o;
        return number == that.number && counter == that.counter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}

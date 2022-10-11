import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;

public class Engine {
    private int globalCounter=6;

    public int[] solution (int[] arr) {
        List<NumberFromArray> numbers = createNumbers(arr);
        for(int i =0; i<numbers.size(); i++) {
            makeNumberDivisible(numbers.get(i));
            globalCounter = globalCounter - numbers.get(i).getCounter();
        }
        Collections.sort(numbers, Comparator.comparingInt(NumberFromArray ::getNumber).reversed());
        for(int i=0; i<numbers.size();i++) {
            makeBigestSum(numbers.get(i));
        }
        System.out.println("List after change : " + numbers + " , moves left :" + globalCounter);
        return convertListIntoArray(numbers);
    }

    private List<NumberFromArray> createNumbers(int[] arr) {
        List<NumberFromArray> listOfNumbers= new ArrayList<>();
        for(int i = 0; i< arr.length; i++) {
            listOfNumbers.add(new NumberFromArray(arr[i]));
        }
        return listOfNumbers;
    }

    public void makeNumberDivisible (NumberFromArray number) {
        List<Integer> digits = convertIntIntoListOfDigits(number.getNumber());

        int sumOfDigits = digits.stream()
                .reduce(0, Integer::sum);

        if(sumOfDigits%3!=0) {
            number.setCounter(3-(sumOfDigits%3));
            int localCounter = number.getCounter();
            for (int i =0; i<digits.size(); i++) {
                int localDigit = digits.get(i);
                if (localCounter!=0 && !isTheDigitValueMax(localDigit)) {
                    do {
                        localDigit++;
                        localCounter--;
                        digits.set(i, localDigit);
                    } while (localCounter!=0 && !isTheDigitValueMax(localDigit));
                }
            }
        }
        number.setNumber(convertListIntoInt(digits));
    }

    private void makeBigestSum (NumberFromArray number) {
        List<Integer> digits = convertIntIntoListOfDigits(number.getNumber());
        int localCounter = globalCounter;
        int localNumber;
        for (int i=0; i< digits.size(); i++) {
            if(digits.get(i)<7 && localCounter>=3) {
                localCounter = localCounter-3;
                localNumber=digits.get(i)+3;
                digits.set(i, localNumber);
            }
        }
        number.setNumber(convertListIntoInt(digits));
        globalCounter=localCounter;
    }


    private int convertListIntoInt (List<Integer> list) {
        int total=0;
        for (Integer i : list) {
            total = 10*total+i;
        }
        return total;
    }

    private List<Integer> convertIntIntoListOfDigits (int number) {
        List<Integer> digits = new ArrayList<>();
        int localInt = number;
        while(localInt>0) {
            digits.add(localInt%10);
            localInt = localInt/10;
        }
        reverse(digits);
        System.out.println(digits);
        return digits;
    }

    private boolean isTheDigitValueMax (int digit) {
        return digit == 9;
    }

    private int[] convertListIntoArray (List<NumberFromArray> number) {
        int[] arr = new int[number.size()];
        for (int i =0; i<number.size(); i++) arr[i] = number.get(i).getNumber();
        return arr;
    }
}

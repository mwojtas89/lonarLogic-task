import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Engine {
    private static final int UNAVALIBE_POSITION = 9;
    private static final int MOVES_NEEDED_TO_INCREASE_AND_KEEP_DIVIDABE = 3;
    private int changesCounter=6;
    private int possition = 1;

    public int[] solveTask (int[] arr) throws RuntimeException {
        if (arr.length!=3) {
            throw new RuntimeException("Please insert arr containing 3 int");
        }
        List<Number> numbers = createListOfNumbersAndDigits(arr);
        print(numbers);
        numbers = makeNumbersDividabeByThree(numbers);
        print(numbers);
        if (changesCounter>=MOVES_NEEDED_TO_INCREASE_AND_KEEP_DIVIDABE) {
            numbers = makeTheBigestSumOfNumbers(numbers);
            print(numbers);
        }
        cleanUp();
        return convertListIntoArray(numbers);
    }

    private List<Number> createListOfNumbersAndDigits (int[] arr) {
        List<Number> number = new ArrayList<>();
        for(int i = 0; i<arr.length; i++) {
            String localNumber = String.valueOf(arr[i]);
            List<Digit> digits = new ArrayList<>();
            for (int d = 0; d < localNumber.length(); d++) {
                digits.add(new Digit(Integer.parseInt(String.valueOf(localNumber.charAt(d))), possition));
                possition++;
            }
        number.add(new Number(digits ,arr[i]));
        }
        return number;
    }

    private List<Number> makeNumbersDividabeByThree (List<Number> numbers) {
        for(int i =0; i<numbers.size(); i++) {
            if(numbers.get(i).getCompleteNumber()%3!=0) {
                int localCounter = 3 - (numbers.get(i).getCompleteNumber()%3);
                for(int d=0; d< numbers.get(i).getDigits().size(); d++) {
                    int localDigit = numbers.get(i).getDigits().get(d).getDigit();
                    while(!isDigitMaxValue(localDigit) && localCounter!=0 &&
                    numbers.get(i).getDigits().get(d).getPosition()!=UNAVALIBE_POSITION) {
                        localDigit++;      //increasing digit
                        localCounter--;    //decreasing local counter to change number to number dividible by 3
                        changesCounter--;  //decreasing global moves counter
                        numbers.get(i).getDigits().get(d).setDigit(localDigit);  //setting corrected digit
                    }
                }
            }
        }
        updateCompleteNumber(numbers);
        return numbers;
    }

    private List<Number> makeTheBigestSumOfNumbers (List<Number> numbers) {
        List<Number> localList = numbers;
        Collections.sort(localList, Comparator.comparingInt(Number::getCompleteNumber).reversed());
        for (int i = 0; i< localList.size(); i++) {
            for(int d =0; d< localList.get(i).getDigits().size(); d++) {
                int localDigit = localList.get(i).getDigits().get(d).getDigit();
                int localCounter = MOVES_NEEDED_TO_INCREASE_AND_KEEP_DIVIDABE;
                if (changesCounter>=MOVES_NEEDED_TO_INCREASE_AND_KEEP_DIVIDABE)
                while(!isDigitMaxValue(localDigit) && changesCounter!=0 && localCounter!=0 &&
                numbers.get(i).getDigits().get(d).getPosition()!=9) {
                    localDigit++;
                    localCounter--;
                    changesCounter--;
                    numbers.get(i).getDigits().get(d).setDigit(localDigit);
                }
            }
        }
        updateCompleteNumber(localList);
        return localList;
    }

    private boolean isDigitMaxValue (int digit) {
        return digit==9;
    }


    private void print (List<Number> numbers) {
        for(int i = 0; i< numbers.size(); i++) {
            System.out.println(numbers.get(i).getCompleteNumber());
        }
        System.out.println("Avalibe changes : " + changesCounter);
    }

    private void updateCompleteNumber (List<Number> numbers) {
        int total=0;
        for(Number n : numbers) {
            total=0;
            for(Digit d : n.getDigits()) {
                total = 10 * total + d.getDigit();
            }
            n.setCompleteNumber(total);
        }
    }

    private int[] convertListIntoArray (List<Number> numbers) {
        int[] solution = new int[numbers.size()];
        for (int i = 0; i< numbers.size(); i++) {
            solution[i] = numbers.get(i).getCompleteNumber();
        }
        return solution;
    }

    private void cleanUp () {
        changesCounter = 6;
    }
}

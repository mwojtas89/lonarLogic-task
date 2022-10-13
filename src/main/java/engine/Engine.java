package engine;

import solution_elements.Digit;
import solution_elements.Number;
import solution_elements.Solutions;

import java.util.*;

public class Engine {
    private static final int UNAVALIBE_POSITION = 9;
    private static final int MOVES_NEEDED_TO_INCREASE_AND_KEEP_DIVIDABE = 3;
    private int changesCounter=6;
    private int possition = 1;

    public int[] solveTask (int[] arr) {
        List<Number> numbers = createListOfNumbersAndDigits(arr);
        print(numbers);
        numbers = makeNumbersDivisibleByThree(numbers);
        print(numbers);
        List<Solutions> posibleSolutions = makeTheBigestSumOfNumbers(numbers);
        numbers = choseBestResult(posibleSolutions);
        cleanUp();
        return convertListIntoArray(numbers);
    }

    private List<Number> createListOfNumbersAndDigits (int[] arr) {
        List<Number> number = new ArrayList<>();
        for (int j : arr) {
            String localNumber = String.valueOf(j);
            List<Digit> digits = new ArrayList<>();
            for (int d = 0; d < localNumber.length(); d++) {
                digits.add(new Digit(Integer.parseInt(String.valueOf(localNumber.charAt(d))), possition));
                possition++;
            }
            number.add(new Number(digits, j));
        }
        return number;
    }

    private List<Number> makeNumbersDivisibleByThree (List<Number> numbers) {
        for(Number n : numbers) {                                       //iterating through numbers
            if(n.getCompleteNumber()%3!=0) {                            //checking if number is divisible by 3
                int localCounter = 3 - (n.getCompleteNumber()%3);       //setting localCounter as steps to be made to have digit be divisible by 3
                for(Digit d : n.getDigits()) {                          //iterating through digits
                    int localDigit = d.getDigit();
                    while(!isDigitMaxValue(localDigit) && localCounter!=0 &&
                            d.getPosition()!=UNAVALIBE_POSITION) {
                        localDigit++;                                   //increasing digit
                        localCounter--;                                 //decreasing local counter
                        changesCounter--;                               //decreasing global moves counter
                        d.setDigit(localDigit);                         //setting increased digit
                    }
                }
            }
        }
        updateCompleteNumber(numbers);                                  //update of int number in class solution_elements.Number
        return numbers;
    }

    private List<Solutions> makeTheBigestSumOfNumbers (List<Number> numbers) {
        List<Solutions> solutions = new ArrayList<>();
        if (changesCounter>=MOVES_NEEDED_TO_INCREASE_AND_KEEP_DIVIDABE) {
            int posibleSolutionsCounter = 3;
            for(int i =0; i< posibleSolutionsCounter; i++) {
                List<Number> posibleSolution = createListsForSolution(numbers);
                int localCounter = 0;
                if(changesCounter==3
                        && isItPosibleToIncrease(posibleSolution.get(i).getDigits(), 3)) {
                    localCounter=3;
                }else if (changesCounter==6
                        && isItPosibleToIncrease(posibleSolution.get(i).getDigits(), 6)) {
                    localCounter=6;
                }
                for(Digit d : posibleSolution.get(i).getDigits()) {
                    int localDigit = d.getDigit();
                    while(!isDigitMaxValue(localDigit) && localCounter!=0
                            && UNAVALIBE_POSITION!=d.getPosition()) {
                        localDigit++;
                        localCounter--;
                        d.setDigit(localDigit);
                    }
                }
                updateCompleteNumber(posibleSolution);
                solutions.add(new Solutions(posibleSolution));
            }
        }
        return solutions;
    }

    private List<Number> createListsForSolution (List<Number> numbers) {
        List<Number> posibleSolution = new ArrayList<>();
        for(Number n : numbers) {
            List<Digit> posibleSolutionDigits = new ArrayList<>();
            for(Digit d : n.getDigits()) {
                posibleSolutionDigits.add(new Digit(d.getDigit(), d.getPosition()));
            }
            posibleSolution.add(new Number(posibleSolutionDigits, n.getCompleteNumber()));
        }
        return posibleSolution;
    }



    private boolean isItPosibleToIncrease (List<Digit> digits, int counter) {
        int maxValueOfDigits = digits.size()*9;
        int sumOfDigits = digits.stream().map(Digit::getDigit).reduce(0, Integer::sum);
        return (maxValueOfDigits-sumOfDigits)>=counter;
    }

    private boolean isDigitMaxValue (int digit) {
        return digit==9;
    }

    private List<Number> choseBestResult (List<Solutions> results) {
        for (Solutions s : results) {
            int result = s.getSolution().stream().map(Number::getCompleteNumber).reduce(0, Integer::sum);
            s.setSum(result);
        }
        results.sort(Comparator.comparingInt(Solutions::getSum).reversed());
        return results.get(0).getSolution();
    }


    private void print (List<Number> numbers) {
        for(Number n : numbers) {
            System.out.println(n.getCompleteNumber());
        }
        System.out.println("Avalibe changes : " + changesCounter);
    }

    private void updateCompleteNumber (List<Number> numbers) {
        int total;
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

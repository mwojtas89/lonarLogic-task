import engine.Engine;

public class RunSolution {
    public static void main(String[] args) {
        Engine engine = new Engine();
        int[] solution = engine.solveTask(new int[]{0,0,0});
        printSolution(solution);
        solution = engine.solveTask(new int[]{1,1,1});
        printSolution(solution);
        solution = engine.solveTask(new int[]{111, 33333, 222});
        printSolution(solution);
        solution = engine.solveTask(new int[]{999, 889, 888});
        printSolution(solution);

    }

    public static void printSolution (int[] solution) {
        for(int j : solution) {
            System.out.print(" " + j + " ");
        }
        System.out.println("");
    }
}

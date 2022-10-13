import engine.Engine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class EngineTest {

    @Test
    void solveTask() {
        //Given
        Engine engine = new Engine();
        Random r = new Random();
        List<int[]> listOfTest = new ArrayList<>();
        List<int[]> listOfResults = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            listOfTest.add(new int[]{r.nextInt(1000000), r.nextInt(1000000), r.nextInt(1000000)});
        }
        //When
        for(int i =0; i<listOfTest.size(); i++) {
            listOfResults.add(engine.solveTask(listOfTest.get(i)));
        }
        //Then

    }
}
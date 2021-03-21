import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class FourRussiansAlgorithmTest {

    @Test
    void fourRussiansMultiply1() {
        int size = 1;
        assertFourRussians(size);
    }

    @Test
    void fourRussiansMultiply50() {
        int size = 50;
        assertFourRussians(size);
    }

    @Test
    void fourRussiansMultiply100() {
        int size = 100;
        assertFourRussians(size);
    }

    @Test
    void fourRussiansMultiply999() {
        int size = 999;
        assertFourRussians(size);
    }

    private void assertFourRussians(int size) {
        FourRussiansAlgorithm fourRussiansAlgorithm = new FourRussiansAlgorithm();
        List<boolean[][]> matrices = createRandomMatrix(size);
        boolean[][] fourRussiansResult = fourRussiansAlgorithm.fourRussiansMultiply(matrices.get(0), matrices.get(1));
        boolean[][] normalMultiplicationResult = matrixMultiplicationDefault(matrices.get(0), matrices.get(1));
        Assertions.assertTrue(Arrays.deepEquals(fourRussiansResult, normalMultiplicationResult));
    }

    private static List<boolean[][]> createRandomMatrix(int size) {
        List<boolean[][]> result = new ArrayList<>();
        boolean[][] matrix1 = new boolean[size][size];
        boolean[][] matrix2 = new boolean[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                matrix1[i][j] = ThreadLocalRandom.current().nextBoolean();
                matrix2[i][j] = ThreadLocalRandom.current().nextBoolean();
            }
        }
        result.add(matrix1);
        result.add(matrix2);
        return result;
    }

    private static boolean[][] matrixMultiplicationDefault(boolean[][] a, boolean[][] b) {
        int n = a.length;
        boolean[][] c = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                boolean value = false;
                for (int m = 0; m < n; m++) {
                    value |= a[i][m] && b[m][j];
                    if (value)
                        break;
                }
                c[i][j] = value;
            }
        }
        return c;
    }

}
import java.util.ArrayList;
import java.util.List;

public class FourRussiansAlgorithm {

    public boolean[][] fourRussiansMultiply(boolean[][] aMatrix, boolean[][] bMatrix) {
        int n = aMatrix.length;

        if(n == 1) {
            boolean[][] C1 = new boolean[1][1];
            C1[0][0] = aMatrix[0][0] && bMatrix[0][0];
            return C1;
        }

        int m = (int) (Math.log(n) / Math.log(2));

        List<boolean[][]> aMatrixPartitioned = getColumnWisePartition(aMatrix, m, n);
        List<boolean[][]> bMatrixPartitioned = getRowWisePartition(bMatrix, m, n);

        boolean[][] cMatrix = new boolean[n][n];

        for (int i = 0; i < n/m; ++i) {
            boolean[][] rowSums = new boolean[(int) Math.pow(2, m)][n];
            int bp = 1, k = 0;
            for (int j = 1; j < Math.pow(2, m); ++j) {
                rowSums[j] = calculateRowSum(rowSums[j - (int) Math.pow(2, k)], getRowFromBottom(bMatrixPartitioned.get(i), k));

                if (bp == 1) {
                    bp = j + 1;
                    k++;
                } else {
                    bp--;
                }
            }

            boolean[][] ci = new boolean[n][n];
            for (int j = 0; j < n; ++j) {
                ci[j] = rowSums[getNum(aMatrixPartitioned.get(i)[j])];
            }

            cMatrix = matrixSum(cMatrix, ci);
        }
        return cMatrix;
    }

    private static boolean[] getRowFromBottom(boolean[][] array, int k) {
        return array[array.length - k - 1];
    }

    private static boolean[] calculateRowSum(boolean[] a, boolean[] b) {
        int size = a.length;
        boolean[] result = new boolean[size];

        for (int i = 0; i < size; ++i) {
            result[i] = a[i] || b[i];
        }

        return result;
    }

    private static List<boolean[][]> getColumnWisePartition(boolean[][] matrix, int m, int n) {
        List<boolean[][]> result = new ArrayList<>();

        for (int i = 0; i < n/m; ++i) {
            boolean[][] partition = new boolean[n][m];
            for (int col = 0; col < m; ++col) {
                boolean[] tmpColumn = getColumn(matrix, i * m + col);
                for (int row = 0; row < n; ++row) {
                    partition[row][col] = tmpColumn[row];
                }
            }
            result.add(partition);
        }
        return result;
    }

    private static List<boolean[][]> getRowWisePartition(boolean[][] matrix, int m, int n) {
        List<boolean[][]> result = new ArrayList<>();

        for (int i = 0; i < n/m; ++i) {
            boolean[][] partition = new boolean[m][n];
            for (int row = 0; row < m; ++row) {
                partition[row] = matrix[i * m + row];
            }
            result.add(partition);
        }

        return result;
    }

    private static boolean[] getColumn(boolean[][] array, int index) {
        int size = array.length;
        boolean[] column = new boolean[size];

        for (int i = 0; i < size; ++i)
            column[i] = array[i][index];

        return column;
    }

    private static int getNum(boolean[] row) {
        int num = 0;
        int size = row.length - 1;

        for (int i = 0; i <= size; ++i) {
            if (row[i]) {
                num += Math.pow(2, i);
            }
        }
        return num;
    }

    private static boolean[][] matrixSum(boolean[][] a, boolean[][] b) {
        int n = a.length;
        boolean[][] result = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                result[i][j] = a[i][j] || b[i][j];
            }
        }

        return result;
    }
}

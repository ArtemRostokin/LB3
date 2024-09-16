public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"21", "2.18", "345", "310"},
                {"570", "78", "137", "217"},
                {"348", "3.0", "8.92", "143"},
                {"337", "327", "613", "999"}
        };

        try {
            double result = processArray(array);
            System.out.println("Сумма элементов массива array: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static double processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Неправильный размер массива. Ожидается массив 4x4.");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Неправильный размер строки " + i + ". Ожидается 4 элемента.");
            }
        }

        double[][] doubleArray = new double[4][4];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    doubleArray[i][j] = Double.valueOf(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке: [" + i + "][" + j + "]");
                }
            }
        }

        double sum = 0;

        for (int i = 0; i < doubleArray.length; i++) {
            for (int j = 0; j < doubleArray[i].length; j++) {
                double value = doubleArray[i][j];
                try {
                    if (isFibonacci(value)) {
                        if (value <= 1000) {
                            throw new MyFibonacciException("В ячейке массива число Фибоначчи: [" + i + "][" + j + "] - " + value);
                        }
                    } else if (value <= 1000) {
                        throw new MyNoFibonacciException("В ячейке массива не число Фибоначчи: [" + i + "][" + j + "] - " + value);
                    }
                } catch (MyFibonacciException | MyNoFibonacciException e) {
                    e.printStackTrace();
                }
                sum += value;
            }
        }

        return sum;
    }

    public static boolean isFibonacci(double number) {
        if (number < 0) return false;
        double a = 0, b = 1;
        while (b <= 1000) {
            if (b == number) return true;
            double temp = b;
            b = a + b;
            a = temp;
        }
        return false;
    }
}






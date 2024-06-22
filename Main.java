import java.util.Arrays;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = 0;
        while (true) {
            System.out.println("Введите количество температур по палате:");
            try {
                size = Integer.parseInt(scanner.nextLine());
                if (size <= 0) {
                    System.out.println("Количество температур должно быть положительным числом. Попробуйте снова.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное целое число.");
            }
        }

        double[] tempData = new double[size];

        String[] tempStrings = new String[size];

        while (true) {
            try {
                System.out.println("Введите " + size + " значений температуры:");
                for (int i = 0; i < size; i++) {
                    tempStrings[i] = scanner.nextLine();
                }

                for (int i = 0; i < size; i++) {
                    tempData[i] = Double.parseDouble(tempStrings[i]);
                }
                
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное значение температуры.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Недостаточно значений.");
            }
        }



        String report = getReport(tempData);
        System.out.println(report);
    }

    public static String getReport(double[] tempData) {
        int healthyCount = 0;
        int sickCount = 0;

        for (double temp : tempData) {
            System.out.println(temp);
            if (temp <= 36.9) {
                healthyCount++;
            } else {
                sickCount++;
            }
        }


        double average = Arrays.stream(tempData).average().orElse(Double.NaN);
        System.out.println("Среднее значение: " + average);

        String result;
        if (sickCount == 0 && healthyCount > 0) {
            result = "Здоровая палата";
        } else if (healthyCount > sickCount) {
            result = "В основном, здоровая палата";
        } else {
            result = "В палате больше больных, чем здоровых";
        }
        return result + " (здоровых: " + healthyCount + ", больных: " + sickCount + ")";
    }
}

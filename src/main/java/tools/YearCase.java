package tools;

public class YearCase {
    public static String yearCase(int age) {
        if (age >= 11 && age <= 14) {
            return "Лет";
        }
        int ostatok = age % 10;
        if (ostatok == 1) {
            return "Год";
        }
        if (ostatok >= 2 && ostatok <= 4) {
            return "Года";
        }
        return "Лет";

    }
}

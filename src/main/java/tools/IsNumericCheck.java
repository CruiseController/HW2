package tools;

public class IsNumericCheck {
    public static boolean isNumeric(String inputString) {
        try {
            Integer.parseInt(inputString);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Неподдерживаемый тип данных, повторите попытку ");
            return false;
        }

    }
}

package Model;

public class ExpressionParser {

    public static boolean validateExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return false;
        }

        // Проверка на допустимые символы
        // Исправлено: экранирование спецсимволов в регулярном выражении
        String cleaned = expression.replaceAll("[0-9+\\-*/()%.^\\s]", "");
        if (!cleaned.isEmpty()) {
            return false;
        }

        // Проверка баланса скобок
        int balance = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0; // Упрощено: вместо if (balance != 0) return false; return true;
    }
}
package Model;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class CalculatorModel {
    private HistoryManager historyManager;
    private List<String> currentSessionHistory;

    public CalculatorModel() {
        this.historyManager = new HistoryManager();
        this.currentSessionHistory = new ArrayList<>();
        loadHistory();
    }

    public double calculate(String expression) throws Exception {
        // Очищаем выражение от пробелов
        expression = expression.replaceAll("\\s+", "");

        // Проверяем на пустое выражение
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("Выражение не может быть пустым");
        }

        // Заменяем ** на ^ для унификации
        expression = expression.replace("**", "^");

        double result = evaluateExpression(expression);

        // Сохраняем в историю
        String historyEntry = expression + " = " + result;
        historyManager.addEntry(historyEntry);
        currentSessionHistory.add(historyEntry);

        return result;
    }

    private double evaluateExpression(String expression) throws Exception {
        // Проверяем на отрицательные числа в начале
        if (expression.startsWith("-")) {
            expression = "0" + expression;
        }

        // Обрабатываем скобки
        while (expression.contains("(")) {
            int start = expression.lastIndexOf("(");
            int end = expression.indexOf(")", start);
            if (end == -1) {
                throw new IllegalArgumentException("Неверное количество скобок");
            }
            String subExpr = expression.substring(start + 1, end);
            double result = evaluateSimpleExpression(subExpr);
            expression = expression.substring(0, start) + result + expression.substring(end + 1);
        }

        return evaluateSimpleExpression(expression);
    }

    private double evaluateSimpleExpression(String expression) throws Exception {
        // Сначала обрабатываем унарный минус
        List<String> tokens = tokenize(expression);

        // Обрабатываем операции в порядке приоритета
        // 1. Возведение в степень (^) - правоассоциативная
        tokens = processPower(tokens);

        // 2. Умножение, деление, деление без остатка, остаток от деления
        tokens = processMultiplicationDivision(tokens);

        // 3. Сложение и вычитание
        tokens = processAdditionSubtraction(tokens);

        if (tokens.size() != 1) {
            throw new Exception("Ошибка в вычислении выражения");
        }

        return Double.parseDouble(tokens.get(0));
    }

    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();
        boolean isNegative = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                currentNumber.append(c);
            } else if (c == '-' && (i == 0 || isOperator(expression.charAt(i - 1)))) {
                // Унарный минус
                isNegative = true;
            } else if (isOperator(c) || c == '^') {
                if (currentNumber.length() > 0) {
                    double num = Double.parseDouble(currentNumber.toString());
                    if (isNegative) {
                        num = -num;
                        isNegative = false;
                    }
                    tokens.add(String.valueOf(num));
                    currentNumber = new StringBuilder();
                }
                tokens.add(String.valueOf(c));
            } else if (c == '(' || c == ')') {
                if (currentNumber.length() > 0) {
                    double num = Double.parseDouble(currentNumber.toString());
                    if (isNegative) {
                        num = -num;
                        isNegative = false;
                    }
                    tokens.add(String.valueOf(num));
                    currentNumber = new StringBuilder();
                }
                tokens.add(String.valueOf(c));
            }
        }

        if (currentNumber.length() > 0) {
            double num = Double.parseDouble(currentNumber.toString());
            if (isNegative) {
                num = -num;
            }
            tokens.add(String.valueOf(num));
        }

        return tokens;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    private List<String> processPower(List<String> tokens) throws Exception {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("^")) {
                if (i == 0 || i == tokens.size() - 1) {
                    throw new Exception("Неверный синтаксис возведения в степень");
                }
                double left = Double.parseDouble(result.remove(result.size() - 1));
                double right = Double.parseDouble(tokens.get(i + 1));
                double power = Math.pow(left, right);
                result.add(String.valueOf(power));
                i++; // Пропускаем правый операнд
            } else {
                result.add(token);
            }
        }

        return result;
    }

    private List<String> processMultiplicationDivision(List<String> tokens) throws Exception {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("*") || token.equals("/") || token.equals("%")) {
                if (i == 0 || i == tokens.size() - 1) {
                    throw new Exception("Неверный синтаксис операции");
                }
                double left = Double.parseDouble(result.remove(result.size() - 1));
                double right = Double.parseDouble(tokens.get(i + 1));
                double operationResult;

                switch (token) {
                    case "*":
                        operationResult = left * right;
                        break;
                    case "/":
                        if (right == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        operationResult = left / right;
                        break;
                    case "%":
                        operationResult = left % right;
                        break;
                    default:
                        throw new Exception("Неизвестная операция");
                }

                result.add(String.valueOf(operationResult));
                i++; // Пропускаем правый операнд
            } else {
                result.add(token);
            }
        }

        return result;
    }

    private List<String> processAdditionSubtraction(List<String> tokens) throws Exception {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("+") || token.equals("-")) {
                if (i == 0 || i == tokens.size() - 1) {
                    throw new Exception("Неверный синтаксис операции");
                }
                double left = Double.parseDouble(result.remove(result.size() - 1));
                double right = Double.parseDouble(tokens.get(i + 1));
                double operationResult;

                if (token.equals("+")) {
                    operationResult = left + right;
                } else {
                    operationResult = left - right;
                }

                result.add(String.valueOf(operationResult));
                i++; // Пропускаем правый операнд
            } else {
                result.add(token);
            }
        }

        return result;
    }

    public List<String> getHistory() {
        return historyManager.getHistory();
    }

    public void loadHistory() {
        historyManager.loadHistory();
    }

    public void saveHistory() {
        historyManager.saveHistory();
    }

    public void saveHistoryToFile(String filePath) {
        historyManager.saveToFile(filePath);
    }

    public void saveSelectedEntries(List<Integer> indices, String filePath) {
        historyManager.saveSelectedEntries(indices, filePath);
    }

    public String getCurrentHistoryFilePath() {
        return historyManager.getCurrentFilePath();
    }
}
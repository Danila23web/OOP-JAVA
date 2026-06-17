package controller;

import Model.CalculatorModel;
import Model.ExpressionParser;
import view.CalculatorView;

import java.util.List;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;
    private boolean running;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.running = true;
    }

    public void start() {
        view.showWelcome();

        while (running) {
            view.showMenu();
            String choice = view.getExpressionInput();

            switch (choice) {
                case "1":
                    handleCalculate();
                    break;
                case "2":
                    handleShowHistory();
                    break;
                case "3":
                    handleSaveHistory();
                    break;
                case "4":
                    handleSaveSelected();
                    break;
                case "5":
                    handleShowPath();
                    break;
                case "6":
                    handleExit();
                    break;
                default:
                    view.showError("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void handleCalculate() {
        String expression = view.getExpressionInput();

        if (!ExpressionParser.validateExpression(expression)) {
            view.showError("Недопустимые символы или несбалансированные скобки в выражении");
            return;
        }

        try {
            double result = model.calculate(expression);
            view.showResult(result, expression);
        } catch (Exception e) {
            view.showError(e.getMessage());
        }
    }

    private void handleShowHistory() {
        List<String> history = model.getHistory();
        view.showHistory(history);
    }

    private void handleSaveHistory() {
        String filePath = view.getFilePathInput();
        if (filePath == null || filePath.trim().isEmpty()) {
            model.saveHistory();
        } else {
            model.saveHistoryToFile(filePath);
        }
    }

    private void handleSaveSelected() {
        List<String> history = model.getHistory();
        if (history.isEmpty()) {
            view.showMessage("История пуста");
            return;
        }

        view.showHistory(history);
        List<Integer> selectedIndices = view.getSelectedEntries(history);

        if (selectedIndices != null && !selectedIndices.isEmpty()) {
            String filePath = view.getFilePathInput();
            model.saveSelectedEntries(selectedIndices, filePath);
        }
    }

    private void handleShowPath() {
        String path = model.getCurrentHistoryFilePath();
        view.showFilePath(path);
    }

    private void handleExit() {
        if (view.getConfirmation("Вы уверены, что хотите выйти?")) {
            view.showMessage("Сохранение истории...");
            model.saveHistory();
            view.showMessage("До свидания!");
            running = false;
        }
    }
}

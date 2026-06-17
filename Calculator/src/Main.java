import Model.CalculatorModel;
import view.CalculatorView;
import controller.CalculatorController;

public class Main {
    public static void main(String[] args) {
        // Создаем компоненты MVC
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);

        // Запускаем приложение
        controller.start();

        // Закрываем ресурсы
        view.close();
    }
}
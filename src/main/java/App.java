import controller.Controller;
import model.Model;
import view.ConverterView;

public class App
{
    public static void main( String[] args )  {

        Model model = new Model();
        Controller controller = new Controller(model);
        ConverterView converterView = new ConverterView(controller);
        converterView.startView();

    }
}

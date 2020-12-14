package TN_Hiking;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.View.CreateParcoursView;
import TN_Hiking.View.WelcomeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        GestionnaireParcours gParcours = new GestionnaireParcours();

        primaryStage.setTitle("Parcours");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/createParcoursView.fxml"));
        loader.setControllerFactory(iC->new CreateParcoursView(gParcours));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 1000,1000));
        primaryStage.show();
    }
}

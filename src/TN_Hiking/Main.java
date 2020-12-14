package TN_Hiking;

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

        primaryStage.setTitle("Parcours");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View/welcomeView.fxml"));
        loader.setControllerFactory(iC->new WelcomeView());
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 1000,1000));
        primaryStage.show();
    }
}

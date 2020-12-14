package TN_Hiking.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;


public class FxmlLoaderScreen {


    private Pane pane;

    public Pane getPane (String fileName){

        try {
            URL url = getClass().getResource(fileName + ".fxml");
            if (url == null) {
                throw new java.io.FileNotFoundException("Fichier inconnu :(");
            }

            this.pane = new FXMLLoader().load(url);

        }catch (Exception e){
            System.out.println("Pas de FXML : "+fileName);

        }
        return this.pane;


    }


}

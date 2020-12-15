package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Parcours;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import javax.lang.model.type.NullType;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;


public class FxmlLoaderScreen {


    private Pane pane;

    public Pane getPane (String fileName, GestionnaireParcours gestionnaireParcours){

        try {
            URL url = getClass().getResource(fileName + ".fxml");
            if (url == null) {
                throw new java.io.FileNotFoundException("Fichier inconnu :(");
            }

            //FXMLLoader​(URL location, ResourceBundle resources, BuilderFactory builderFactory, Callback<Class<?>,​Object> controllerFactory)
            //this.pane = new FXMLLoader().load(url);
            ResourceBundle r = new ResourceBundle() {
                @Override
                protected Object handleGetObject(String key) {
                    return null;
                }

                @Override
                public Enumeration<String> getKeys() {
                    return null;
                }
            };
            if (fileName == "createParcoursView") {
                this.pane = new FXMLLoader(url, r, null, iC -> new CreateParcoursView(gestionnaireParcours)).load();
            }
            else if (fileName == "updateParcours"){
                this.pane = new FXMLLoader(url, r, null, iC -> new UpdateParcours(gestionnaireParcours)).load();
            }
            else if (fileName == "showParcoursView") {

                this.pane = new FXMLLoader(url,r,null,iC -> new UpdateParcours(gestionnaireParcours)).load();
            }
            else if (fileName == "parcoursTracerView"){
                this.pane = new FXMLLoader(url, r, null, iC -> new ParcoursTarcerView()).load();
            }

        }catch (Exception e){
            System.out.println("Pas de FXML : "+fileName);

        }
        return this.pane;


    }


}

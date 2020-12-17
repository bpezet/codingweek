package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import com.sothawo.mapjfx.Coordinate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListeParcoursView {

    @FXML
    private ListView<String> list;

    @FXML
    private MenuBar my_bar;

    private GestionnaireParcours gestionnaireGlobale;

    public ListeParcoursView(GestionnaireParcours gestionnaireParcours){
        this.gestionnaireGlobale=gestionnaireParcours;
    }

    @FXML
    public void eventHandlerBouton(){
        try {
            Parcours etapeselected = this.gestionnaireGlobale.getParcours(this.list.getSelectionModel().getSelectedIndex());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("visualiserParcours.fxml"));
            loader.setControllerFactory(iC->new VisualiserParcours(this.gestionnaireGlobale,etapeselected));
            Parent createParcoursParent = loader.load();

            VisualiserParcours visualiserParcours = loader.getController();
            visualiserParcours.initMapAndControls();

            Scene createParcoursScene = new Scene(createParcoursParent);

            Stage window = (Stage) my_bar.getScene().getWindow();

            window.setScene(createParcoursScene);
            window.show();


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize(){
        ArrayList<Parcours> listParcours = this.gestionnaireGlobale.getParcours();
        ArrayList<String> listNom =new ArrayList<>();
        for(Parcours parcours : listParcours){
            listNom.add(parcours.getName());
        }
        list.getItems().setAll(listNom);
    }


}

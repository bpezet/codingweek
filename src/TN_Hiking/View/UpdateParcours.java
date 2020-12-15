package TN_Hiking.View;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.Models.Parcours;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class UpdateParcours {

    @FXML
    private TextField titre;

    @FXML
    private TextField note;

    @FXML
    private TextField debut;

    @FXML
    private TextField fin;

    @FXML
    private TextArea resume;

    @FXML
    private TextArea description;


    GestionnaireParcours ges;
    Parcours parcours;

    public UpdateParcours(GestionnaireParcours gestionnaireParcours){
        this.ges = gestionnaireParcours;
        this.parcours = new Parcours("ParcoursTest",5, "Moncuq");
        this.titre.setPromptText(this.parcours.getName());
        this.debut.setPromptText(this.parcours.getEtapeDebut().getName());
    }

    public void eventHandlerModificationEtape(){
        FxmlLoaderScreen object = new FxmlLoaderScreen();
        Pane view = object.getPane("showParcoursView",this.ges);
        //mainPane.setCenter(view);
    }

    

}

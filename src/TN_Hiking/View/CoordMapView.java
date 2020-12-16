package TN_Hiking.View;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CoordMapView {

    private ParcoursViewEtape parcoursViewEtape;
    private Marker markerClick;

    @FXML
    private MapView myMap;
    @FXML
    private Label latitude;
    @FXML
    private Label longitude;
    @FXML
    private Button validateButton;

    /** Constructeur */
    public CoordMapView(ParcoursViewEtape parcoursViewEtape){
        this.parcoursViewEtape = parcoursViewEtape;
        this.markerClick = Marker.createProvided(Marker.Provided.ORANGE).setVisible(true);
    }

    /** Méthodes */

    public void initMapAndControls() {
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        // add an event handler for singleclicks, set the click marker to the new position when it's visible
        myMap.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            event.consume();
            final Coordinate newPosition = event.getCoordinate().normalize();
            latitude.setText(newPosition.getLatitude().toString());
            longitude.setText(newPosition.getLongitude().toString());

            this.markerClick.setPosition(newPosition);
            this.myMap.addMarker(this.markerClick);
        });
    }

    /** Bouton Valider */
    public void validateCoordinate(ActionEvent actionEvent) {
        this.parcoursViewEtape.getLatitude().setText(this.latitude.getText());
        this.parcoursViewEtape.getLongitude().setText(this.longitude.getText());
        ((Stage) validateButton.getScene().getWindow()).close();
    }

    @FXML
    public void initialize(){
        this.myMap.initialize();
        this.myMap.setCenter(new Coordinate(48.85,2.34));
    }
}

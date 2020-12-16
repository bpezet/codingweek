package TN_Hiking.View;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CoordMapView {

    @FXML
    private MapView myMap;
    @FXML
    private Label latitude;
    @FXML
    private Label longitude;

    /** Constructeur */
    public CoordMapView(){}

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
        });
    }

    @FXML
    public void initialize(){
        this.myMap.initialize();
        this.myMap.setCenter(new Coordinate(48.85,2.34));
    }
}

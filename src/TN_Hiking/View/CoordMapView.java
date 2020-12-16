package TN_Hiking.View;

import com.sothawo.mapjfx.*;
import javafx.fxml.FXML;

public class CoordMapView {

    @FXML
    private MapView myMap;

    /** Constructeur */
    public CoordMapView(){}

    /** Méthodes */

    @FXML
    public void initialize(){
        this.myMap.initialize();
        this.myMap.setCenter(new Coordinate(48.85,2.34));
    }
}

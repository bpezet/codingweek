package TN_Hiking.View;

import TN_Hiking.Models.Etape;
import TN_Hiking.Models.Parcours;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.CoordinateLine;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.event.MapViewEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ShowEtapeView {
    private static final int ZOOM_DEFAULT = 14;

    private Parcours parcours;

    private Marker markerSet;

    private Double new_latitude =-1.0;

    private Double new_longitude=-1.0;

    private Marker newEtapeMarker;


    @FXML
    private MapView mapView = new MapView();
    @FXML
    private ListView<String> listView;
    @FXML
    private CheckBox checkTracer;

    private CoordinateLine track;

    /**Constructeur*/
    public ShowEtapeView(Parcours parcours){

        this.parcours = parcours;
        this.markerSet = Marker.createProvided(Marker.Provided.BLUE).setVisible(false);

        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.parcours.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }

        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);

        this.newEtapeMarker = Marker.createProvided(Marker.Provided.GREEN).setVisible(true);

    }


    public void initMapAndControls(){
        mapView.setZoom(ZOOM_DEFAULT);
        this.mapView.addMarker(this.markerSet);
        setupEventHandlers();
        this.track.setVisible(true);
        this.mapView.addCoordinateLine(this.track);
        this.mapView.setCenter(new Coordinate(48.692848,6.177866));
    }




    /**Les actions à faire dès lors que l'on clic sur un item de la liste*/
    @FXML
    public void eventHandlerMouseClickListView(){
        try {
            System.out.println("Tu as cliquer sur : " + this.listView.getSelectionModel().getSelectedItem());
            Etape etapeselected = this.parcours.getSpecificEtape(this.listView.getSelectionModel().getSelectedIndex());
            this.mapView.setCenter(new Coordinate(etapeselected.getLatitude(), etapeselected.getLongitude()));

            this.markerSet.setVisible(true);
            this.markerSet.setPosition(new Coordinate(etapeselected.getLatitude(),etapeselected.getLongitude()));
            this.mapView.addMarker(this.markerSet);


        }catch(Exception e){
            System.out.println("Fait gaffe bro");
        }
    }

    /**Mise à jour du tracer*/
    @FXML
    public void eventHandlerTracer(){
        this.track.setVisible(false);
        List<Coordinate> list = new ArrayList<>();
        for (Etape etape : this.parcours.getEtapes()){
            list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
        }
        this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
        this.track.setVisible(true);
        this.mapView.addCoordinateLine(this.track);
    }


    /**Mise à jour de la liste*/
    public void updateList(){
        List<String> listEtape = new ArrayList<>();
        for (Etape etape : this.parcours.getEtapes()){
            listEtape.add(etape.getName());
        }
        this.listView.getItems().setAll(listEtape);
    }

    /**Suppression d'une étape sélectionné de la liste*/
    @FXML
    public void supprimeEtape(){
        try{
            int i = this.listView.getSelectionModel().getSelectedIndex();
            this.parcours.suppressionEtape(i);
            this.updateList();
            this.eventHandlerTracer();
        }catch (Exception e){
            System.out.println("Rien a été sélectionné dans la liste :(");
        }
    }

    /**SetUp afin de connaitre les coordonnées de là où l'on a cliqué sur la carte*/
    @FXML
    public void setupEventHandlers(){
        // add an event handler for singleclicks, set the click marker to the new position when it's visible
        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
            event.consume();
            final Coordinate newPosition = event.getCoordinate().normalize();
            this.new_latitude = newPosition.getLatitude();
            this.new_longitude = newPosition.getLongitude();
            this.newEtapeMarker.setVisible(true);
            this.newEtapeMarker.setPosition(newPosition);
            this.mapView.addMarker(this.newEtapeMarker);
        });
    }

    /** CheckBox afficher le parcours */
    @FXML
    public void afficherParcours(ActionEvent actionEvent) {
        if(this.checkTracer.isSelected()) {
            //this.track.setVisible(false);
            List<Coordinate> list = new ArrayList<>();
            for (Etape etape : this.parcours.getEtapes()) {
                list.add(new Coordinate(etape.getLatitude(), etape.getLongitude()));
            }
            this.track = new CoordinateLine(list).setColor(Color.BLACK).setWidth(10);
            this.track.setVisible(true);
            this.mapView.addCoordinateLine(this.track);
        }
        else {
            this.track.setVisible(false);
        }
    }

    @FXML
    public void initialize(){
        this.mapView.initialize();
        this.mapView.setCenter(new Coordinate(49.00515,8.394922));
        this.updateList();
        this.listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.eventHandlerTracer();

    }
}

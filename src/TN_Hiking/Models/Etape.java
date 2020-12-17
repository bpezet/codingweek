package TN_Hiking.Models;

public class Etape {
    protected String name;
    protected double latitude;
    protected double longitude;


    public Etape() {
    }


    public Etape(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
            /** Getters and Setters*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void etapeShow(){
        System.out.print("Etape "+name+"(lat:"+latitude+",long:"+longitude+")\n");
    }
}

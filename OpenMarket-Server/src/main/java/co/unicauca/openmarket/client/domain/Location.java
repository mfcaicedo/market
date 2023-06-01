package co.unicauca.openmarket.client.domain;

/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class Location {
    private Integer locationId;
    private Float latitude;
    private Float longitude;
    private String place;

    public Location(Integer locationId, Float latitude, Float longitude, String place) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.place = place;
    }

    public Location() {
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer lacationId) {
        this.locationId = lacationId;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}

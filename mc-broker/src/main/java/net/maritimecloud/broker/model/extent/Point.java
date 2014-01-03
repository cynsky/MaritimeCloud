package net.maritimecloud.broker.model.extent;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Point {

    double lat;
    double lon;

    public Point() {

    }

    public Point(double lat, double lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

}

package net.maritimecloud.broker.model.extent;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Polygon extends Area {
    
    List<Point> points = new ArrayList<>();
    
    public Polygon() {
        
    }
    
    public List<Point> getPoints() {
        return points;
    }
    
    public void setPoints(List<Point> points) {
        this.points = points;
    }

}

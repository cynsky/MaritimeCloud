package net.maritimecloud.broker.model.extent;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RelativeServiceExtent extends ServiceExtent {
    
    int radius;
    
    public RelativeServiceExtent() {
        
    }
    
    public int getRadius() {
        return radius;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
    }

}

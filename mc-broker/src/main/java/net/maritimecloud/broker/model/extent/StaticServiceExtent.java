package net.maritimecloud.broker.model.extent;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StaticServiceExtent extends ServiceExtent {
    
    Area area;
    
    public StaticServiceExtent() {
        
    }
    
    public Area getArea() {
        return area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }

}

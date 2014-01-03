package net.maritimecloud.broker.model.extent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({Polygon.class})
public abstract class Area {

    public Area() {
        
    }
    
}

package net.maritimecloud.broker.model.extent;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({ StaticServiceExtent.class, RelativeServiceExtent.class })
public abstract class ServiceExtent {
    
    public ServiceExtent() {
        
    }

}

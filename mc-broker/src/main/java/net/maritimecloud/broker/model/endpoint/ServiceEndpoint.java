package net.maritimecloud.broker.model.endpoint;

public class ServiceEndpoint {
    
    String url;
    ServiceEndpointType type;
    
    public ServiceEndpoint() {
        
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public ServiceEndpointType getType() {
        return type;
    }
    
    public void setType(ServiceEndpointType type) {
        this.type = type;
    }
    
}

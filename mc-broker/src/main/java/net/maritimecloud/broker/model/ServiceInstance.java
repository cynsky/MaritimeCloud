package net.maritimecloud.broker.model;

import java.util.ArrayList;
import java.util.List;

import net.maritimecloud.broker.model.endpoint.ServiceEndpoint;
import net.maritimecloud.broker.model.extent.ServiceExtent;

public class ServiceInstance {
    
    String provider;
    String description;
    ServiceSpecificationVariant variant;
    ServiceExtent extent;
    List<ServiceEndpoint> endpoint = new ArrayList<>();
    
    public ServiceInstance() {
        
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceSpecificationVariant getVariant() {
        return variant;
    }

    public void setVariant(ServiceSpecificationVariant variant) {
        this.variant = variant;
    }

    public ServiceExtent getExtent() {
        return extent;
    }

    public void setExtent(ServiceExtent extent) {
        this.extent = extent;
    }

    public List<ServiceEndpoint> getEndpoint() {
        return endpoint;
    }
    
    public void setEndpoint(List<ServiceEndpoint> endpoint) {
        this.endpoint = endpoint;
    }

}

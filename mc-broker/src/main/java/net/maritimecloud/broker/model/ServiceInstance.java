package net.maritimecloud.broker.model;

import java.util.ArrayList;
import java.util.List;

import net.maritimecloud.broker.model.endpoint.ServiceEndpoint;
import net.maritimecloud.broker.model.extent.ServiceExtent;

public class ServiceInstance {
    
    public enum ServiceType {
        DYNAMIC, STATIC;
    }
    
    String provider;
    String name;
    String description;
    ServiceSpecification specification;
    ServiceType type;
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
    
    public ServiceSpecification getSpecification() {
        return specification;
    }
    
    public void setSpecification(ServiceSpecification specification) {
        this.specification = specification;
    }
    
    public ServiceType getType() {
        return type;
    }
    
    public void setType(ServiceType type) {
        this.type = type;
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

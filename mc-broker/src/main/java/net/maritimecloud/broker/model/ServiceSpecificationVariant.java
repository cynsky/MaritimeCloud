package net.maritimecloud.broker.model;

public class ServiceSpecificationVariant {
    
    private String protocol;
    private String method;
    private ServiceSpecification specification;
    
    public ServiceSpecificationVariant() {
        
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public ServiceSpecification getSpecification() {
        return specification;
    }
    
    public void setSpecification(ServiceSpecification specification) {
        this.specification = specification;
    }
    
}

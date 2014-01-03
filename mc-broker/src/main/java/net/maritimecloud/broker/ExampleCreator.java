package net.maritimecloud.broker;

import net.maritimecloud.broker.model.OperationalService;
import net.maritimecloud.broker.model.ServiceInstance;
import net.maritimecloud.broker.model.ServiceSpecification;
import net.maritimecloud.broker.model.ServiceSpecificationVariant;
import net.maritimecloud.broker.model.endpoint.ServiceEndpoint;
import net.maritimecloud.broker.model.endpoint.ServiceEndpointType;
import net.maritimecloud.broker.model.extent.Point;
import net.maritimecloud.broker.model.extent.Polygon;
import net.maritimecloud.broker.model.extent.StaticServiceExtent;

public class ExampleCreator {
    
    public static ServiceInstance createMsiBaltic() {
        // Make the operational service
        OperationalService op = new OperationalService();
        op.setName("MSI");        
        
        // Make service specification
        ServiceSpecification spec = new ServiceSpecification();
        spec.setOperationalService(op);
        spec.setServiceId("MSI");
        spec.setVersion("1.1");
        spec.setDescription("Maritime Safety Information Service");
        
        // Make variant
        ServiceSpecificationVariant variant = new ServiceSpecificationVariant();
        variant.setSpecification(spec);
        variant.setMethod("NAVTEX");
        
        // Make service endpoints
        ServiceEndpoint ep1 = new ServiceEndpoint();
        ep1.setType(ServiceEndpointType.NAVTEX);
        ep1.setUrl("navtex://ROGALAND");
        ServiceEndpoint ep2 = new ServiceEndpoint();
        ep2.setType(ServiceEndpointType.NAVTEX);
        ep2.setUrl("navtex://BALTICO");
        
        // Make service extent
        StaticServiceExtent extent = new StaticServiceExtent();
        Polygon polygon = new Polygon();
        polygon.getPoints().add(new Point(53.398, 8.37686));
        polygon.getPoints().add(new Point(53.7982, 19.363));
        polygon.getPoints().add(new Point(58.24649, 21.121889));
        polygon.getPoints().add(new Point(58.385162, 7.84510931));
        polygon.getPoints().add(new Point(53.398470, 8.376868058));
        extent.setArea(polygon);
        
        // Make instance
        ServiceInstance instance = new ServiceInstance();
        instance.setProvider("BALTICO");
        instance.setDescription("MSI BALTICO NAVTEX");
        instance.setVariant(variant);
        instance.setExtent(extent);
        instance.getEndpoint().add(ep1);
        instance.getEndpoint().add(ep2);
        
        return instance;
    }
    
    public static ServiceInstance createMsiDk() {
        // Make the operational service
        OperationalService op = new OperationalService();
        op.setName("MSI");        
        
        // Make service specification
        ServiceSpecification spec = new ServiceSpecification();
        spec.setOperationalService(op);
        spec.setServiceId("MSI");
        spec.setVersion("1.1");
        spec.setDescription("Maritime Safety Information Service");
        
        // Make variant
        ServiceSpecificationVariant variant = new ServiceSpecificationVariant();
        variant.setSpecification(spec);
        variant.setMethod("SOAP");
        
        // Make service endpoints
        ServiceEndpoint ep1 = new ServiceEndpoint();
        ep1.setType(ServiceEndpointType.INTERNET_URL);
        ep1.setUrl("http://msi.dma.dk/msi/warnings?wsdl");
        
        // Make service extent
        StaticServiceExtent extent = new StaticServiceExtent();
        Polygon polygon = new Polygon();
        polygon.getPoints().add(new Point(54, 7));
        polygon.getPoints().add(new Point(58.6, 5.9));
        polygon.getPoints().add(new Point(58.7, 14.5));
        polygon.getPoints().add(new Point(54, 14.15));
        extent.setArea(polygon);
        
        // Make instance
        ServiceInstance instance = new ServiceInstance();
        instance.setProvider("DMA");
        instance.setDescription("MSI DMA WebService");
        instance.setVariant(variant);
        instance.setExtent(extent);
        instance.getEndpoint().add(ep1);
        
        
        return instance;
    }
    
}

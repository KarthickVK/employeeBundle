package com.launchclub.employee.controller;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(immediate = true)
public class RestService {

    private Server server;

    /**
     * Activate the server.
     */
    @Activate
    public void activate() {
        try {
        JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();

        bean.setAddress("/employee");
        bean.setBus(BusFactory.getDefaultBus());
        bean.setProvider(new JacksonJsonProvider());
        bean.setServiceBean(new ControllerServiceImpl());
        server = bean.create();
    } catch(Exception exception) {
            exception.printStackTrace();
            System.out.println(exception);
        }
}

    /**
     * Deactivate the server.
     */
    @Deactivate
    public void deactivate() {

            if (server != null) {
                server.destroy();
            }
    }
}

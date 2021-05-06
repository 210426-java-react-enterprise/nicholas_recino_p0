package com.revature.fsmapp.util;

import com.revature.fsmapp.exceptions.ServiceNotFoundException;
import com.revature.fsmapp.services.Service;

import java.util.HashMap;

public class ServiceHandler {
    private static ServiceHandler serviceHandler;
    private HashMap<String, Service> services;

    private ServiceHandler(){
        services = new HashMap<>();
    }

    public static ServiceHandler getInstance(){
        if (serviceHandler == null)
            serviceHandler = new ServiceHandler();
        return serviceHandler;
    }

    public ServiceHandler addService(Service service, String serviceName){
        services.put(serviceName,service);
        return this;
    }

    public ServiceHandler stopService(String serviceName){
            services.remove(serviceName);
            return this;
    }

    public Service startService(String serviceName) throws ServiceNotFoundException {
        Service service = services.get(serviceName);
        if (service == null)
            throw new ServiceNotFoundException();
        return service;
    }
}

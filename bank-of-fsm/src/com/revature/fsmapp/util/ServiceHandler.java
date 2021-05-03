package com.revature.fsmapp.util;

import com.revature.fsmapp.services.Service;
import com.revature.fsmapp.util.collection.ArrayList;

import java.util.HashMap;

public class ServiceHandler {
    private HashMap<String, Service> serviceHandlers;

    public void addService(Service service, String serviceName){
        serviceHandlers.put(serviceName,service);
    }

    public void stopService(String serviceName){
            serviceHandlers.remove(serviceName);
    }

    public void startService(String serviceName){

    }

    public void initServices(){
        Service[] servicesToBeInitialized = (Service[])serviceHandlers.values().toArray();
        for(Service service: servicesToBeInitialized){
            service.init();
        }
    }



}

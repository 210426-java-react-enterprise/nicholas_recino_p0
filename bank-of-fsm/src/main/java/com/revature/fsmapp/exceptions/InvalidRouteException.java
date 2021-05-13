package com.revature.fsmapp.exceptions;

public class InvalidRouteException extends  Exception{
    public InvalidRouteException(){
        super("Invalid Route Provided, directing to the Welcome Screen instead.");
    }
}

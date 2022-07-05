package server;

import javax.xml.ws.Endpoint;

import Controller.StudentController;

public class jwsServer {

    public static void main(String[] args) {
        String url = "http://localhost:8020/";

        Endpoint.publish(url, new StudentController());
        System.out.println(url);
    }
    
}

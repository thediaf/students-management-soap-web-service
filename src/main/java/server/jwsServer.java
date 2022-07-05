package server;

import javax.xml.ws.Endpoint;

import Controller.StudentController;

public class jwsServer {

    public static void main(String[] args) {
        String url = "http://192.168.5.51:8020/";
        Endpoint.publish(url, new StudentController());
        System.out.println(url);
    }
    
}

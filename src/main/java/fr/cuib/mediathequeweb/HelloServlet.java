package fr.cuib.mediathequeweb;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request,response);
    }
    public void process(HttpServletRequest request, HttpServletResponse response){
    }

    public void destroy() {
    }
}
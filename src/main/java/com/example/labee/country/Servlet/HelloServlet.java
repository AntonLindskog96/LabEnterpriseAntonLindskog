/*
package com.example.labee.country.Servlet;

import java.io.*;

import com.example.labee.country.Repository.CountryRepository;
import jakarta.inject.Inject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    @Inject
    CountryRepository countryRepository;

    private String message;

    public void init() {
        message = "Hello DOnk";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}*/

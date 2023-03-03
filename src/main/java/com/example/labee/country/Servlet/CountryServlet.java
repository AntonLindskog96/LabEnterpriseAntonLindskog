
package com.example.labee.country.Servlet;

import com.example.labee.country.Repository.CountryRepository;
import com.example.labee.country.entity.Country;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="CountryServlet", value = "/countries")
public class CountryServlet extends HttpServlet {

    @PersistenceContext
    private EntityManager em;

    @Inject
    CountryRepository repository;

    @Inject
    Jsonb jsonb;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Get path
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            resp.setContentType("text/html");

            PrintWriter out = resp.getWriter();
            out.println("<html><body>");

            out.println("<h1>" + "Country" + "</h1>");
            out.println("<h1>" + path + "</h1>");
            for (Country country : repository.findAll())
                out.println("<div>" + country.getId() + ":" + country.getName() + "</div>");
            out.println("</body></html>");
        } else {
            long id = Long.parseLong(path.substring(1));
            var country = repository.findOne(id);
            resp.setContentType("application/json");
            if (country.isPresent()) {
                PrintWriter out = resp.getWriter();
                out.print(jsonb.toJson(country));
            } else {
                resp.setContentType("text/html");
                resp.sendError(404);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            /*report an error*/
        }
        Country[] countries = jsonb.fromJson(jb.toString(), Country[].class);
        for (Country country : countries) {
            repository.insertCountry(country);
        }
    }
}
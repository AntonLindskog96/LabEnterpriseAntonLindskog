
package com.example.labee.country.controller;

import com.example.labee.country.CountryMapper;
import com.example.labee.country.Repository.CountryRepository;
import com.example.labee.country.dto.CountryDTO;
import com.example.labee.country.entity.Country;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/countries")
public class CountryController {

    @Inject
    CountryRepository repository;

    @Inject
    CountryMapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountryDTO> getAll() {
        List<Country> countries = repository.getAll();
        return countries.stream().map(p -> new CountryDTO(p.getId(), p.getName())).collect(Collectors.toList());
    }
    @POST
    public Response addOne(@Valid Country country) {
        try {
            repository.insertCountry(country);
            return Response.created(URI.create("countries/" + country.getId())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Country getOneCountry() {
        return null;
    }

//DELETE
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        Country country = repository.getId(id);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repository.deleteCountry(id);
        return Response.noContent().build();
    }

    private List<CountryDTO> map(List<Country> all) {
        return all.stream().map(country -> new CountryDTO(country.getId(), country.getName())).toList();
    }

    //UPDATE
    // 404 if country doesn't exist, else 204.
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id,@QueryParam("name") String name) {
        Country country = repository.getId(id);
        if (country == null) {
            return Response.status(Response.Status.NOT_FOUND).build();

        }
        country.setName(country.getName());
        repository.update(id, country);
        return Response.noContent().build();

    }
}


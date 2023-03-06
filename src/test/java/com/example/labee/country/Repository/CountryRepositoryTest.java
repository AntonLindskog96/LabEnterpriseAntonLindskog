package com.example.labee.country.Repository;

import com.example.labee.country.controller.CountryController;
import com.example.labee.country.entity.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CountryRepositoryTest {

    @InjectMocks
    private CountryRepository repository;
    @Mock
    private EntityManager entitymanager;
    @Mock
    private Query query;

    Country country = new Country("test");
    Country country2 = new Country("test2");

    List<Country> countryList = List.of(country, country2);

    @Test
    void testFindOneReturnsCorrectCountry() {

        when(entitymanager.find(Country.class, 1L)).thenReturn(country);
        Optional<Country> result = repository.findOne(1L);
        assertTrue(result.isPresent());
        assertEquals(country, result.get());
    }



    @Test
    void findAllReturnCountryList() {
        when(query.getResultList()).thenReturn(countryList);
        when(entitymanager.createQuery("select c from Country c")).thenReturn(query);

        var result = repository.findAll();
        assertEquals(countryList, result);
    }




}
package com.example.labee.country.Repository;

import com.example.labee.country.controller.CountryController;
import com.example.labee.country.entity.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    private CountryRepository countryRepository;
    private CountryController countryController;


}
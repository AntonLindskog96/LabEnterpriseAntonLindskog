package com.example.labee.country;

import com.example.labee.country.dto.CountryDTO;
import com.example.labee.country.entity.Country;

import java.util.List;

public class CountryMapper {

    public List<CountryDTO> map(List<Country> countries) {

        return countries.stream().map(country -> new CountryDTO(country.getId(), country.getName())).toList();
    }

    public CountryDTO map(Country country) {
        return new CountryDTO(country.getId(), country.getName());
    }


}

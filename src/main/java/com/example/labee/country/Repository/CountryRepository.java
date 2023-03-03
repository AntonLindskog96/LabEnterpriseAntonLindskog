package com.example.labee.country.Repository;

import com.example.labee.country.entity.Country;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class CountryRepository {

    @PersistenceContext
    EntityManager em;

    public List<Country> findAll(){
        var query = em.createQuery("select c from Country c");
        return (List<Country>) query.getResultList();

    }

    public List<Country> getAll() {
        return em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
    }

    public Optional<Country> findOne(Long id){
        return Optional.ofNullable(em.find(Country.class, id));
    }

    public void insertCountry(Country country)  {
        em.persist(country);
    }

    public Country getId(long id) {
        return em.find(Country.class, id);
    }





    public void deleteCountry (long id){
        var country = findOne(id);
        country.ifPresent((c)-> em.remove(c));

    }

    public Country update(Long id, Country country){
        var entity = em.find(Country.class, id);
        entity.setName(country.getName());
        em.persist(entity);
        return entity;
    }
    public List<Country> findAllByName(String name) {
        var query = em.createQuery("select c from Country c where c.name like :name");
        query.setParameter("name", name);
        return (List<Country>) query.getResultList();
    }
}


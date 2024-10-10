/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.infra.jdbc;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import io.gongarce.ud2_mvc.infra.jdbc.entities.MailEntity;
import io.gongarce.ud2_mvc.infra.jdbc.entities.PersonEntity;
import io.gongarce.ud2_mvc.infra.jdbc.mappers.PersonEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor(onConstructor = @__({
    @Inject}))
public class JdbcPersonRepository implements PersonRepository {

    private final EntityManager entityManager;

    @Override
    public Person save(Person person) throws SavePersonException, NifExistingException {
        entityManager.getTransaction().begin();
        PersonEntity entity = PersonEntityMapper.INSTANCE.toEntity(person);

        var existingEntity = findByNif(person.getNif());
        if (existingEntity.filter((p) -> !p.getId().equals(entity.getId())).isPresent()) {
            throw new NifExistingException();
        }

        try {
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            Logger.getLogger(JdbcPersonRepository.class.getName()).log(Level.SEVERE, null, e);
            throw new SavePersonException();
        }
        return PersonEntityMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public Optional<Person> get(String nif) {
        return findByNif(nif).map(PersonEntityMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Person> getAll() {
        List<PersonEntity> persons = entityManager.createNamedQuery("Person.findAll").getResultList();
        return PersonEntityMapper.INSTANCE.toDomain(persons);
    }

    @Override
    public List<Person> getByMail(String address) {
        return getMail(address).map((mail) -> {
            Query query = entityManager.createNamedQuery("Person.findByMail");
            query.setParameter("mail", mail);
            List<PersonEntity> persons = query.getResultList();
            return PersonEntityMapper.INSTANCE.toDomain(persons);
        }).orElse(Collections.emptyList());
    }

    private Optional<MailEntity> getMail(String address) {
        Query query = entityManager.createNamedQuery("Mail.findByAddress");
        query.setParameter("address", address);
        MailEntity mail = (MailEntity) query.getSingleResultOrNull();
        return Optional.ofNullable(mail);
    }

    private Optional<PersonEntity> findByNif(String nif) {
        Query query = entityManager.createNamedQuery("Person.findByNif");
        query.setParameter("nif", nif);
        PersonEntity person = (PersonEntity) query.getSingleResultOrNull();
        return Optional.ofNullable(person);
    }
}

package io.gongarce.ud2_mvc.infra;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.gongarce.ud2_mvc.domain.mail.MailRepository;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import io.gongarce.ud2_mvc.infra.jdbc.JdbcMailRepository;
import io.gongarce.ud2_mvc.infra.jdbc.JdbcPersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Objects;

/**
 *
 * @author Gonzalo
 */
public class RepositoryModule extends AbstractModule {

    private EntityManager entityManager;
    
    @Override
    protected void configure() {
        bind(PersonRepository.class).to(JdbcPersonRepository.class).asEagerSingleton();
        bind(MailRepository.class).to(JdbcMailRepository.class).asEagerSingleton();
    }

    @Provides
    @SuppressWarnings("unused")
    EntityManager provideEntityManager() {
        if (Objects.isNull(entityManager)) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("io.gongarce.h2");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
}

package io.gongarce.ud2_mvc.infra.jdbc;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.domain.mail.Mail;
import io.gongarce.ud2_mvc.domain.mail.MailRepository;
import io.gongarce.ud2_mvc.infra.jdbc.entities.MailEntity;
import io.gongarce.ud2_mvc.infra.jdbc.mappers.MailEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor(onConstructor = @__({
    @Inject}))
public class JdbcMailRepository implements MailRepository {

    private final EntityManager entityManager;

    @Override
    public List<Mail> getAll() {
        List<MailEntity> mails = entityManager.createNamedQuery("Mail.findAll").getResultList();
        return MailEntityMapper.INSTANCE.toDomain(mails);
    }

    @Override
    public List<Mail> getByNif(String nif) {
        Query query = entityManager.createNamedQuery("Mail.findByNif");
        query.setParameter("nif", nif);
        List<MailEntity> mails = query.getResultList();
        return MailEntityMapper.INSTANCE.toDomain(mails);
    }
}

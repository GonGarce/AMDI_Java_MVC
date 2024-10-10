package io.gongarce.ud2_mvc.infra.jdbc.mappers;

import io.gongarce.ud2_mvc.domain.mail.Mail;
import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.infra.jdbc.entities.MailEntity;
import io.gongarce.ud2_mvc.infra.jdbc.entities.PersonEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Gonzalo
 */
@Mapper
public interface MailEntityMapper {

    MailEntityMapper INSTANCE = Mappers.getMapper(MailEntityMapper.class);

    Mail toDomain(MailEntity m);

    List<Mail> toDomain(List<MailEntity> m);

    @Mapping(target = "person", ignore = true)
    MailEntity toEntity(Mail m);
}

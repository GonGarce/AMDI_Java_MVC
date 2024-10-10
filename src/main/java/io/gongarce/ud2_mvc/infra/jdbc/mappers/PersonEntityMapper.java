package io.gongarce.ud2_mvc.infra.jdbc.mappers;

import io.gongarce.ud2_mvc.domain.person.Person;
import io.gongarce.ud2_mvc.infra.jdbc.entities.PersonEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Gonzalo
 */
@Mapper(uses = {MailEntityMapper.class})
public interface PersonEntityMapper {

    PersonEntityMapper INSTANCE = Mappers.getMapper(PersonEntityMapper.class);

    Person toDomain(PersonEntity p);

    List<Person> toDomain(List<PersonEntity> persons);

    PersonEntity toEntity(Person p);
}

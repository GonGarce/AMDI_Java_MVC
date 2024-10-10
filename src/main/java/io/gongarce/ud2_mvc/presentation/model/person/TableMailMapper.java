package io.gongarce.ud2_mvc.presentation.model.person;

import io.gongarce.ud2_mvc.domain.mail.Mail;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Gonzalo
 */
@Mapper
public interface TableMailMapper {
    
    public static TableMailMapper INSTANCE = Mappers.getMapper(TableMailMapper.class);
    
    TableMail toView(Mail mail);

    List<TableMail> toView(List<Mail> mails);
   
}

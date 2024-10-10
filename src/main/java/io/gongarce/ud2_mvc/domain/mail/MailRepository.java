package io.gongarce.ud2_mvc.domain.mail;

import java.util.List;

/**
 *
 * @author Gonzalo
 */
public interface MailRepository {
    List<Mail> getAll();

    List<Mail> getByNif(String nif);
}

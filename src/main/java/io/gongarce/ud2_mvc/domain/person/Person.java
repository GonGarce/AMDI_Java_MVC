package io.gongarce.ud2_mvc.domain.person;

import io.gongarce.ud2_mvc.domain.mail.Mail;
import java.util.List;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 *
 * @author Gonzalo
 */
@Value
@Builder(toBuilder = true)
@AllArgsConstructor
public class Person {
    Long id;
    @NonNull String nif;
    @NonNull String name;
    @Nullable String place;
    List<Mail> mails;
    List<String> phones;
}

package io.gongarce.ud2_mvc.application;

import com.google.inject.Inject;
import io.gongarce.ud2_mvc.domain.mail.Mail;
import io.gongarce.ud2_mvc.domain.mail.MailRepository;
import io.gongarce.ud2_mvc.domain.person.PersonRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
//@Component
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class GetMailsFromPersonUseCase implements UseCase {

    private final MailRepository mailRepository;

    public List<Mail> get(@NonNull String nif) {
        return mailRepository.getByNif(nif);
    }
}

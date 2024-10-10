package io.gongarce.ud2_mvc.presentation.controller.person;

import io.gongarce.ud2_mvc.application.GetMailsFromPersonUseCase;
import io.gongarce.ud2_mvc.presentation.App;
import io.gongarce.ud2_mvc.presentation.controller.SearchListControllerAction;
import io.gongarce.ud2_mvc.presentation.model.person.TableMail;
import io.gongarce.ud2_mvc.presentation.model.person.TableMailMapper;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Gonzalo
 */
public class MailListControllerAction implements SearchListControllerAction<TableMail> {

    @Override
    public List<TableMail> search(Optional<String> searchText) {
        if (searchText.isEmpty()) {
            return Collections.emptyList();
        }
        GetMailsFromPersonUseCase useCase = App.get(GetMailsFromPersonUseCase.class);
        return TableMailMapper.INSTANCE.toView(useCase.get(searchText.get()));
    }

}

package io.gongarce.ud2_mvc.presentation.controller.person;

import io.gongarce.ud2_mvc.application.UpdatePersonsUserCase;
import io.gongarce.ud2_mvc.domain.error.NotFoundException;
import io.gongarce.ud2_mvc.domain.person.error.NifExistingException;
import io.gongarce.ud2_mvc.domain.person.error.RequiredPropertyException;
import io.gongarce.ud2_mvc.domain.person.error.SavePersonException;
import io.gongarce.ud2_mvc.domain.person.error.WrongPhoneException;
import io.gongarce.ud2_mvc.presentation.App;
import io.gongarce.ud2_mvc.presentation.model.person.TablePerson;
import io.gongarce.ud2_mvc.presentation.model.person.TablePersonMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.gongarce.ud2_mvc.presentation.controller.SaveControllerAction;

/**
 *
 * @author 20091024054934665
 */
public class PersonUpdateControllerAction implements SaveControllerAction<TablePerson> {

    @Override
    public TablePerson save(TablePerson model) {
        UpdatePersonsUserCase useCase = App.get(UpdatePersonsUserCase.class);
        try {
            var person = useCase.update(TablePersonMapper.INSTANCE.toDomain(model));
            return TablePersonMapper.INSTANCE.toView(person);
        } catch (SavePersonException ex) {
            Logger.getLogger(PersonUpdateControllerAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongPhoneException ex) {
            Logger.getLogger(PersonUpdateControllerAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(PersonUpdateControllerAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NifExistingException ex) {
            Logger.getLogger(PersonUpdateControllerAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RequiredPropertyException ex) {
            Logger.getLogger(PersonUpdateControllerAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

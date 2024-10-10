/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.presentation.controller.person;

import io.gongarce.ud2_mvc.presentation.controller.SearchListControllerAction;
import io.gongarce.ud2_mvc.application.GetPersonsUserCase;
import io.gongarce.ud2_mvc.presentation.App;
import io.gongarce.ud2_mvc.presentation.model.person.TablePerson;
import io.gongarce.ud2_mvc.presentation.model.person.TablePersonMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@RequiredArgsConstructor(staticName = "create")
public class PersonListControllerAction implements SearchListControllerAction<TablePerson> {

    @Override
    public List<TablePerson> search(Optional<String> searchText) {
        GetPersonsUserCase useCase = App.get(GetPersonsUserCase.class);
        return TablePersonMapper.INSTANCE.toView(useCase.get(searchText));
    }
}

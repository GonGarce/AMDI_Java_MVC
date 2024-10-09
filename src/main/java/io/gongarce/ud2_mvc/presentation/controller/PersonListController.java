/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.presentation.controller;

import io.gongarce.ud2_mvc.application.GetPersonsUserCase;
import io.gongarce.ud2_mvc.presentation.App;
import io.gongarce.ud2_mvc.presentation.model.ListTableModel;
import io.gongarce.ud2_mvc.presentation.model.TablePerson;
import io.gongarce.ud2_mvc.presentation.model.TablePersonMapper;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author gag
 */
public class PersonListController {

    private final ListTableModel<TablePerson> tableModel;

    public PersonListController(ListTableModel<TablePerson> tableModel) {
        this.tableModel = tableModel;
    }

    public void search(String mail) {
        GetPersonsUserCase useCase = App.get(GetPersonsUserCase.class);
        Optional searchText = Optional.empty();
        if (Objects.nonNull(mail) && !mail.trim().equals("")) {
            searchText = Optional.of(mail);
        }

        tableModel.setData(
                TablePersonMapper.INSTANCE.toView(useCase.get(searchText)));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.presentation.model;

import io.gongarce.ud2_mvc.domain.person.Person;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author gag
 */
@Mapper
public interface TablePersonMapper {
    
    public static TablePersonMapper INSTANCE = Mappers.getMapper(TablePersonMapper.class);
    
    TablePerson toView(Person person);

    List<TablePerson> toView(List<Person> person);
}

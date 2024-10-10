package io.gongarce.ud2_mvc.presentation.model.person;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author gag
 */
@Data
@Builder(toBuilder = true, access = AccessLevel.PRIVATE)
public class TablePerson {
    String nif;
    String name;
    String place;
    
    public TablePerson from() {
        return toBuilder().build();
    }
    
    public static TablePerson of(TablePerson person) {
        return person.toBuilder().build();
    }
}

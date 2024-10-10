package io.gongarce.ud2_mvc.presentation.model.person;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Gonzalo
 */
@Data
@Builder(toBuilder = true, access = AccessLevel.PRIVATE)
public class TableMail {
    private Long id;
    private String address;
}

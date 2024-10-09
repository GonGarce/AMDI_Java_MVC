/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.domain.mail;

import lombok.NonNull;
import lombok.Value;

/**
 *
 * @author Gonzalo
 */
@Value
public class Mail {
    @NonNull Long id;
    @NonNull String address;
}

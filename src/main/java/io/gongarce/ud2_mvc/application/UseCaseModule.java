/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.gongarce.ud2_mvc.application;

import com.google.inject.AbstractModule;

/**
 *
 * @author Gonzalo
 */
public class UseCaseModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(GetPersonsUserCase.class).asEagerSingleton();
        bind(CreatePersonsUserCase.class).asEagerSingleton();
    }
}

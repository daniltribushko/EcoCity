package com.example.EcoCity.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 * <p>
 * Аннотация для проверки является ли пользователем организацией
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUserOrganization {
}

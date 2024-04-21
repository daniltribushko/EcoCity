package com.example.EcoCity.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 * <p>
 * Аннотация для проверки не является ли пользователь автором обращения
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAdminNotAppealAuthor {
}

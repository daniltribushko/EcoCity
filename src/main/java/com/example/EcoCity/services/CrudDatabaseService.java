package com.example.EcoCity.services;

/**
 * @param <T>  сущность для работы с бд
 * @param <ID> тип id сущности
 * @author Trubushko Danil
 * <p>
 * Интерфейс для сервисов по работе с сущностями в бд
 */
public interface CrudDatabaseService<T, ID> {
    /**
     * Поиск сущности по идентификатору
     *
     * @param id идентификатор сущности
     * @return сущность
     */
    T findById(ID id);

    /**
     * Сохранение сущности в бд
     *
     * @param object сущность
     */
    void save(T object);

    /**
     * Удаление сущности из бд
     *
     * @param object сущость
     */
    void delete(T object);
}

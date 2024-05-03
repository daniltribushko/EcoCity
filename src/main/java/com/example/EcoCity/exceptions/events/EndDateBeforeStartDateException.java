package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 03.05.2025
 */
public class EndDateBeforeStartDateException extends GlobalAppException {
    public EndDateBeforeStartDateException() {
        super(400, "End date can not be before start date");
    }
}

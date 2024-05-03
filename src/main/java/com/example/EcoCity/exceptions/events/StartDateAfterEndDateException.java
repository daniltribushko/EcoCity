package com.example.EcoCity.exceptions.events;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 01.05.2024
 */
public class StartDateAfterEndDateException extends GlobalAppException {
    public StartDateAfterEndDateException() {
        super(400, "Start date can not be after than end date");
    }
}

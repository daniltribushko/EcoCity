package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 21.04.2024
 */
public class AdminChangeHisAppealStatusException extends GlobalAppException {
    public AdminChangeHisAppealStatusException(String email, Long id) {
        super(409, "Admin " + email + " is trying to change the status of his appeal with " + id + " id");
    }
}

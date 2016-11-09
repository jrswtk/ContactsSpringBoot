package com.user.reg.service;

import com.user.reg.domain.Registration;

public interface EmailService {

    void sendConfirmationMail(Registration registration, String baseUrl);

}

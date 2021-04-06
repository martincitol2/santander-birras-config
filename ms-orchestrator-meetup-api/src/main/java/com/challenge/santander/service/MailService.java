package com.challenge.santander.service;


import com.challenge.santander.model.MeetUpUserCreateDTO;

import javax.mail.MessagingException;

public interface MailService {

    public void sendMail(String destination, MeetUpUserCreateDTO meetUpUserCreateDTO) throws MessagingException;
}

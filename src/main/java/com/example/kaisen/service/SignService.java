package com.example.kaisen.service;

import com.example.kaisen.repository.AuthnRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class SignService {

    @Autowired
    private AuthnRepository authnRepository;

    public boolean doSignIn(String userId, String passphrase) {

        String pass = authnRepository.select(userId).getPassphrase();



            if (pass.equals(passphrase)) {
                return true;
            }



        return false;

    }

}

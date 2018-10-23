/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enap.control.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Marco.ENAP
 */
public class PasswordUtil {

    // Encriptacion de contrasena

    public static String encriptarPassword(String passTextoPlano) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(passTextoPlano.getBytes("UTF-8"));
            byte[] passwordEncriptada = md.digest();
            return new String(Base64.getEncoder().encode(passwordEncriptada));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la contrasena", e);
        }
    }

}

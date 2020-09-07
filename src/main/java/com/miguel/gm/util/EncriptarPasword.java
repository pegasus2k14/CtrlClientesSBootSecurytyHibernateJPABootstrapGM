package com.miguel.gm.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPasword {
    //main
    public static void main(String[] args){
        
        //Encriptando password
        String pass = "abc";
        
        //imprimimos el password ya encriptado
        System.err.println("Password: " + pass);
        System.out.println("Password encriptado: "+ encriptarPassword(pass));
    }
    
    //metodo que encripta el password recibido como parametro    
    public static String encriptarPassword(String pass){
        //creamo objeto de tipo 'BCryptPasswordEncoder' 
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }
}

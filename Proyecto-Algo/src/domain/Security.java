/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Security {

    private String user;
    private String password;

    public Security(String user, String password) {
        this.user = user;
        this.password = encriptacion(password);
    }

    public String encriptacion(String contraseña) {
        char array[] = contraseña.toCharArray();

        for (int i = 0; i < array.length; i++) {
            array[i] = (char) (array[i] + (char) 5);
        }

        String encriptado = String.valueOf(array);

        return encriptado;
    }

    public String desencriptacion(String contraseña) {
        char arrayD[] = contraseña.toCharArray();

        for (int i = 0; i < arrayD.length; i++) {
            arrayD[i] = (char) (arrayD[i] - (char) 5);
        }

        String desencriptado = String.valueOf(arrayD);

        return desencriptado;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return desencriptacion(password);
    }

    public void setPassword(String password) {
        this.password = encriptacion(password);
    }

}

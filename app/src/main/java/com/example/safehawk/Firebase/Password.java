package com.example.safehawk.Firebase;

public class Password
{
    String cuenta, password, uid = "";

    public Password()
    {
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getCuenta()
    {
        return cuenta;
    }

    public void setCuenta(String cuenta)
    {
        this.cuenta = cuenta;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}

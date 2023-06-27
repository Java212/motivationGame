package ru.inspired.web;


public class User  {

    private String name;
    private String surname;
    private int telefon;

    public User(String name, String surname){
        this.name=name;
        this.surname=surname;
        this.telefon=createTelefon();
    }

   public int createTelefon (){
        return (int) (Math.random() * ((1000000 - 99999) + 1)) + 99999;
   }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getTelefon() {
        return telefon;
    }
}

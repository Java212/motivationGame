package ru.inspired.dictionary.model;

public class PersonRecord extends Person{
    private final  int id;
    private final String phone;

    public PersonRecord(int id, String name, String surname, String phone) {
        super(name, surname);
        this.id = id;
        this.phone = phone;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    public String getPhone() {
        return this.phone;
    }
}

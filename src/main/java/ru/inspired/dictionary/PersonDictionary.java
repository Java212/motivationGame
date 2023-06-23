package ru.inspired.dictionary;

import ru.inspired.dictionary.model.PersonRecord;

import java.util.LinkedList;
import java.util.List;

public class PersonDictionary {
    private static int id=0;
    private final List <PersonRecord> personRecords;
    public PersonDictionary(){
        personRecords = new LinkedList<>();
        personRecords.add(new PersonRecord(id++,"Vladimir","Ivanov", "+79174567635"));
        personRecords.add(new PersonRecord(id++,"Vlad","Ershov", "+79174567678"));
        personRecords.add(new PersonRecord(id++,"Larisa","Smirnova", "+79174567691"));
        personRecords.add(new PersonRecord(id++,"Tatyna","Ivanova", "+79174567608"));
        personRecords.add(new PersonRecord(id++,"Katy","Romanova", "+79174567646"));
        personRecords.add(new PersonRecord(id++,"Igor","Serov", "+79174567676"));
        personRecords.add(new PersonRecord(id++,"Victor","Semchev", "+79174567567"));
        personRecords.add(new PersonRecord(id++,"Sergey","Ionov", "+79174567982"));
        personRecords.add(new PersonRecord(id++,"Olga","Belova", "+79174564563"));
        personRecords.add(new PersonRecord(id++,"Petr","Goncharov", "+79174565682"));
    }

    public List<PersonRecord> getDictionary(){
        return  this.personRecords;
    }
}

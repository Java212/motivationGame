package ru.inspired.web;

import java.util.ArrayList;
import java.util.List;

public class ListUser {
    private List<User> data= new ArrayList<>();

    public ListUser() {
        data.add(new User("Tom", "Petrow" ));
        data.add(new User("Bob", "Ivanov" ));
        data.add(new User("Kurt", "Belov" ));
        data.add(new User("John", "Semenov" ));
        data.add(new User("Ivan", "Tarasov" ));
        data.add(new User("Sergey", "Krotov" ));
        data.add(new User("Igor", "Voronin" ));
        data.add(new User("Boris", "Morosov" ));
        data.add(new User("Vitaliy", "Gusev" ));
        data.add(new User("Kirill", "Anochin" ));
    }

    public List<User> getList() {
        return data;
    }
}


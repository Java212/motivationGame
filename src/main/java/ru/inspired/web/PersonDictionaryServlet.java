package ru.inspired.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.inspired.dictionary.PersonDictionary;
import ru.inspired.dictionary.model.PersonRecord;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/dictionary")
public class PersonDictionaryServlet extends HttpServlet {
    private PersonDictionary personDictionary;

    @Override
    public void init() throws ServletException {
         personDictionary = new PersonDictionary();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PersonRecord> personRecordList = personDictionary.getDictionary();
        List<String> phoneList = new LinkedList<>();
        String example = "Ураааааааааа";
        for(PersonRecord element: personRecordList){
            phoneList.add(element.getPhone());
        }
        req.setAttribute("records", personRecordList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dictionary.jsp");
        requestDispatcher.forward(req,resp);
    }
}

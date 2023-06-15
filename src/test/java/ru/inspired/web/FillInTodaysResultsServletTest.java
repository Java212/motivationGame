package ru.inspired.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.mockito.Mockito.*;

public class FillInTodaysResultsServletTest {
    @Test
    void testPrintWriterOfGet() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        FillInTodaysResultsServlet servlet = new FillInTodaysResultsServlet();
        servlet.init();
        servlet.doGet(request, response);

        writer.flush(); // it may not have been flushed yet...
        Assertions.assertTrue(stringWriter.toString().contains("<form method=\"post\" action=\"today\">"));

    }

    @Test
    void testRequestOfPost() throws  IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringReader stringReader = new StringReader("1=on&2=off");
        BufferedReader reader = new BufferedReader(stringReader);
        when(request.getReader()).thenReturn(reader);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        FillInTodaysResultsServlet servlet = new FillInTodaysResultsServlet();
        servlet.init();
        servlet.doPost(request, response);

        writer.flush(); // it may not have been flushed yet...
        Assertions.assertTrue(stringWriter.toString().contains("<h1>"));
    }
}

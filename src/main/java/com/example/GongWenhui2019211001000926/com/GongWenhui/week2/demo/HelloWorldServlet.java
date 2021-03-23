package com.GongWenhui.week2.demo;

import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
       throws IOException {

        PrintWriter writer = response.getWriter();
        writer.println("Name:GongWenhui ID:2019211001000926");
        Date date = new Date();
        writer.println("date and time " + date);

    }

}

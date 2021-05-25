package com.GongWenhui.controller;

import com.GongWenhui.dao.ProductDao;
import com.GongWenhui.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "GetImgServlet", value = "/getImg")
public class GetImgServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
        int id = 0;
        if(request.getParameter("id")!=null)
            id = Integer.parseInt(request.getParameter("id"));
        ProductDao productDao = new ProductDao();
        byte[] imgByte = new byte[0];
        try {
            imgByte = productDao.getPictureById(id, con);
            if(imgByte != null) {
                response.setContentType("image/gif");
                OutputStream out = response.getOutputStream();
                out.write(imgByte);
                out.flush();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

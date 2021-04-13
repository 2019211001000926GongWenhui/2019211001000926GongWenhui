package com.GongWenhui.week5.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sql = "select * from usertable where username = ? and password = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                request.setAttribute("username", rs.getString("username"));
                request.setAttribute("password", rs.getString("password"));
                request.setAttribute("email", rs.getString("email"));
                request.setAttribute("gender", rs.getString("gender"));
                request.setAttribute("birthDate", rs.getString("birthDate"));
                request.getRequestDispatcher("userinfo.jsp").forward(request,response);
            } else {
                request.setAttribute("message", "username or password error!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.GongWenhui.week3.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {
//        String driver = getServletConfig().getServletContext().getInitParameter("driver");
//        String url = getServletConfig().getServletContext().getInitParameter("url");
//        String username = getServletConfig().getServletContext().getInitParameter("username");
//        String password = getServletConfig().getServletContext().getInitParameter("password");
////        System.out.println(driver);
////        System.out.println(url);
////        System.out.println(username);
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url,username,password);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("jnj");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthdate");

        String sql = "insert into usertable values (?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,email);
            pst.setString(4,gender);
            pst.setString(5,birthDate);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        String sql2 = "select * from usertable";
//        String user,pwd,em,sex,date;
//        try {
//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql2);
//            PrintWriter pw = response.getWriter();
//            pw.write("<table border="+1+">");
//            pw.write("<tr>");
//            pw.write("<td>username</td>");
//            pw.write("<td>password</td>");
//            pw.write("<td>email</td>");
//            pw.write("<td>gender</td>");
//            pw.write("<td>birthDate</td>");
//            pw.write("</tr>");
//            while (resultSet.next()) {
//                // ??????id???
//                user = resultSet.getString("username");
//                pwd = resultSet.getString("password");
//                em = resultSet.getString("email");
//                sex = resultSet.getString("gender");
//                date = resultSet.getString("birthDate");
//                pw.write("<tr>");
//                pw.write("<td>"+user+"</td>");
//                pw.write("<td>"+pwd+"</td>");
//                pw.write("<td>"+em+"</td>");
//                pw.write("<td>"+sex+"</td>");
//                pw.write("<td>"+date+"</td>");
//                pw.write("</tr>");
//            }
//            pw.write("</table>");
        // ???????????????????????????

//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        PrintWriter writer = response.getWriter();
//        writer.println("<br>username :" + username);
//        writer.println("<br>password :" + password);
//        writer.println("<br>email :" + email);
//        writer.println("<br>gender :" + gender);
//        writer.println("<br>birthDate :" + birthDate);
//        writer.close();
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);
    }
}
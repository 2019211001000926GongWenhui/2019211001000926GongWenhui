package com.GongWenhui.week5.demo;

import com.GongWenhui.dao.UserDao;
import com.GongWenhui.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {
//        String driver = getServletConfig().getServletContext().getInitParameter("driver");
//        String url = getServletConfig().getServletContext().getInitParameter("url");
//        String username = getServletConfig().getServletContext().getInitParameter("username");
//        String password = getServletConfig().getServletContext().getInitParameter("password");
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url,username,password);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        con = (Connection) getServletContext().getAttribute("con");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        try {
            User user = userDao.findByUsernamePassword(con,username,password);
            if (user!=null) {
                System.out.println(request.getParameter("remember"));
                if((request.getParameter("remember")!=null)&& request.getParameter("remember").equals("1") ) {
                    Cookie usernameCookie = new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie = new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememeberMe",request.getParameter("remember"));
                    usernameCookie.setMaxAge(5);
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }
                HttpSession session = request.getSession();
                System.out.println("session id-->" + session.getId());
                session.setMaxInactiveInterval(60*60);
                session.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request,response);
            } else {
                request.setAttribute("message", "username or password error!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        String sql = "select * from usertable where username = ? and password = ?";
//        try {
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1,username);
//            pst.setString(2,password);
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                pw.write("<h1>Login Success !</h1>");
//                pw.write("<h1>Welcome,"+ username +"</h1>");
//                request.setAttribute("username", rs.getString("username"));
//                request.setAttribute("password", rs.getString("password"));
//                request.setAttribute("email", rs.getString("email"));
//                request.setAttribute("gender", rs.getString("gender"));
//                request.setAttribute("birthDate", rs.getString("birthDate"));
//                request.getRequestDispatcher("userinfo.jsp").forward(request,response);
//            } else {
//                pw.write("<h1>Username or Password is error!</h1>");
//                request.setAttribute("message", "username or password error!");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }
}

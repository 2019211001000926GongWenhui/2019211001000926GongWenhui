package com.GongWenhui.controller;

import com.GongWenhui.dao.ProductDao;
import com.GongWenhui.model.Item;
import com.GongWenhui.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    Connection con = null;
    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            if (request.getParameter("action") == null) {
                displayCart(request, response);
            } else if (request.getParameter("action").equals("add")) {
                try {
                    buy(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (request.getParameter("action").equals("remove")) {
                remove(request, response);
            }
        } else {
            response.sendRedirect("login");
        }
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Item> cart = (List<Item>) request.getSession().getAttribute("cart");
        int id = 0;
        if(request.getParameter("productId") != null) {
            id = Integer.parseInt(request.getParameter("productId"));
        }
        int index = isExisting(id,cart);
        cart.remove(index);
        request.getSession().setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath()+ "/cart");
    }

    private void buy(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
        int id = request.getParameter("productId")!=null ? Integer.parseInt(request.getParameter("productId")) : 0;
        int quantity = request.getParameter("quantity")!= null ? Integer.parseInt(request.getParameter("quantity")) : 1;
        if(id == 0|| quantity == 0) {
            return;
        }
        ProductDao productDao = new ProductDao();
        if(session.getAttribute("cart")==null) {
            List<Item> cart = new ArrayList<Item>();
            Product p = productDao.findById(id,con);
            cart.add(new Item(p, quantity));
            session.setAttribute("cart",cart);
        } else {
            List<Item> cart = (List<Item>)session.getAttribute("cart");
            int index = isExisting(id,cart);
            if (index == -1) {
                Product p = productDao.findById(id, con);
                cart.add(new Item(p, 1));
            } else {
                int newQuantity = cart.get(index).getQuantity()+1;
                cart.get(index).setQuantity(newQuantity);
            }
            session.setAttribute("cart",cart);
        }
        response.sendRedirect(request.getContextPath()+"/cart");
    }

    private int isExisting(int id, List<Item> cart) {
        for(int i=0;i<cart.size();i++) {
            if(cart.get(i).getProduct().getProductId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message","Your Cart");
        String path = "/WEB-INF/views/cart.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}

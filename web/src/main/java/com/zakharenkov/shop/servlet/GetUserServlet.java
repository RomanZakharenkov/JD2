package com.zakharenkov.shop.servlet;

import com.zakharenkov.shop.model.User;
import com.zakharenkov.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getuser")
public class GetUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        User user = UserService.getInstance().findUserById(id);
        req.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/findUserById.jsp")
                .forward(req, resp);
    }
}

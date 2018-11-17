package com.zakharenkov.shop.web.servlet;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.service.service.UserService;
import com.zakharenkov.shop.web.util.ContextRun;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        AnnotationConfigApplicationContext context = ContextRun.getContext();
        UserService userService = context.getBean("userService", UserService.class);

        Long id = Long.parseLong(req.getParameter("id"));

        User user = userService.findUserById(id);
        req.setAttribute("user", user);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/getUser.jsp")
                .forward(req, resp);
    }
}

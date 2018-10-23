package servlet;

import model.Role;
import model.User;
import service.UserService;

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
        User user = UserService.getInstance().getUser(id);
        req.setAttribute("user", user);
        System.out.println(user);

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/getUser.jsp")
                .forward(req, resp);
    }
}

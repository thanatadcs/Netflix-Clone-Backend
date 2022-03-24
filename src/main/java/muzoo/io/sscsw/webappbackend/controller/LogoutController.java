package muzoo.io.sscsw.webappbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/logout")
public class LogoutController {

    @GetMapping
    private void doGet(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        HttpSession session = request.getSession();
        try{
            session.invalidate();
            response.sendRedirect("/login");
        } catch (NullPointerException | IllegalStateException | NumberFormatException e) {
            response.sendRedirect("/menu"); //Send to root in real work
        }
    }
}

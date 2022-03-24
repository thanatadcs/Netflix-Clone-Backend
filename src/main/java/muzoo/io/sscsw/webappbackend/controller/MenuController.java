package muzoo.io.sscsw.webappbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @GetMapping
    private void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        try{
            if(session.getAttribute("login") != null && (boolean)session.getAttribute("login")){
                request.setAttribute("username", "Unknown");
                request.getRequestDispatcher("/WEB-INF/jsp/menu.jsp").include(request, response);
            }
            else{
                response.sendRedirect("/login");
            }
        } catch (NullPointerException e) {
            response.sendRedirect("/login");
        }
    }

    @PostMapping
    private void doPost(HttpServletRequest request, HttpServletResponse response){
        //
    }

}

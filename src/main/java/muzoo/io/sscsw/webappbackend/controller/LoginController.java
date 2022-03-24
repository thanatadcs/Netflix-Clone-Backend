package muzoo.io.sscsw.webappbackend.controller;

import muzoo.io.sscsw.webappbackend.security.CredentialCheck;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    CredentialCheck loginChecker = new CredentialCheck();

    @GetMapping
    private void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        try{
            if(session.getAttribute("login") != null && (boolean)session.getAttribute("login")){
                response.sendRedirect("/menu");
            }
            else{
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").include(request, response);
            }
        } catch (NullPointerException e) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").include(request, response);
        }
    }


    @PostMapping
    private void doPost(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam String username, @RequestParam String password)
            throws ServletException, IOException {
        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            boolean isMatched = loginChecker.checkUser(username, password);
            if(isMatched){
                HttpSession session = request.getSession();
                session.setAttribute("login", true);
                response.sendRedirect("/menu");
            }
            else{
                request.setAttribute("errorMessage", "Incorrect username or password");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").include(request, response);
            }
        }
        else{
            request.setAttribute("errorMessage", "Username or password is missing");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").include(request, response);
        }
    }

}

package muzoo.io.sscsw.webappbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping
    private void doGet(HttpServletRequest request, HttpServletResponse response){
        //
    }


    @PostMapping
    private void doPost(HttpServletRequest request, HttpServletResponse response){
        //
    }

}

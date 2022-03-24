package muzoo.io.sscsw.webappbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/watch")
public class WatchController {

    @GetMapping
    private void doGet(HttpServletRequest request, HttpServletResponse response){
        //
    }

    @PostMapping
    private void doPost(HttpServletRequest request, HttpServletResponse response){
        //
    }

}

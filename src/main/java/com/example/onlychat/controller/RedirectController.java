package com.example.onlychat.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RedirectController {

    @GetMapping(value = "/login")
    public void nonMappingRedirectToHome(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/");
        httpServletResponse.setStatus(302);
    }

    @GetMapping(value = "/home")
    public void nonMappingHomeRedirectToHome(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/");
        httpServletResponse.setStatus(302);
    }
}

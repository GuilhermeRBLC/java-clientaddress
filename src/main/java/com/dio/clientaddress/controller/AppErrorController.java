package com.dio.clientaddress.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public String error() {
        return "<h1>Recurso não disponível!</h1>";
    }

}

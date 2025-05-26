package com.nick.makeup_artist.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {
	@GetMapping("/")
    public String hello() {
        return "Ciao, Spring Boot!";
    }
}

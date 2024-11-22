package io.github.rodrigohirokii.jogosapi2.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogos")
public class JogosController {
    @GetMapping
    public String helloWorld(@RequestParam String nome) {
        return "Hello " + nome;
    }


}
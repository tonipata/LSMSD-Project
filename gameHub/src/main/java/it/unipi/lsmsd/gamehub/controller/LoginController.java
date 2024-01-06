package it.unipi.lsmsd.gamehub.controller;

import it.unipi.lsmsd.gamehub.DTO.LoginDTO;
import it.unipi.lsmsd.gamehub.DTO.RegistrationDTO;
import it.unipi.lsmsd.gamehub.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private ILoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        if(loginService.authenticate(loginDTO))
            return ResponseEntity.ok("Login Successfull");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO registrationDTO){
        if(loginService.registrate(registrationDTO)){
            return ResponseEntity.ok("Signin Successfull");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}

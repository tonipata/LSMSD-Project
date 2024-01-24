package it.unipi.lsmsd.gamehub.service;

import it.unipi.lsmsd.gamehub.DTO.LoginDTO;
import it.unipi.lsmsd.gamehub.utils.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface ILoginService {
    public AuthResponse authenticate(LoginDTO loginDTO);
    public ResponseEntity<Object> roleUser(String userId);
}

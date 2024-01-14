package it.unipi.lsmsd.gamehub.service;

import it.unipi.lsmsd.gamehub.DTO.LoginDTO;
import it.unipi.lsmsd.gamehub.utils.AuthResponse;

public interface ILoginService {
    public AuthResponse authenticate(LoginDTO loginDTO);
}

package it.unipi.lsmsd.gamehub.service;

import it.unipi.lsmsd.gamehub.DTO.LoginDTO;
import it.unipi.lsmsd.gamehub.DTO.RegistrationDTO;

public interface ILoginService {
    public boolean authenticate(LoginDTO loginDTO);
    public boolean registrate(RegistrationDTO registrationDTO);
}

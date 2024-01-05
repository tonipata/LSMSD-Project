package it.unipi.lsmsd.gamehub.service;

import it.unipi.lsmsd.gamehub.DTO.LoginDTO;

public interface ILoginService {
    public boolean authenticate(LoginDTO loginDTO);
}

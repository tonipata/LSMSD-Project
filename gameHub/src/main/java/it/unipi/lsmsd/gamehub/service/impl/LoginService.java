package it.unipi.lsmsd.gamehub.service.impl;

import it.unipi.lsmsd.gamehub.DTO.LoginDTO;
import it.unipi.lsmsd.gamehub.DTO.RegistrationDTO;
import it.unipi.lsmsd.gamehub.model.User;
import it.unipi.lsmsd.gamehub.repository.LoginRepository;
import it.unipi.lsmsd.gamehub.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public boolean authenticate(LoginDTO loginDTO) {
        // retrieve value
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        User u = loginRepository.findByUsername(username);
        return Objects.equals(u.getPassword(), password);

    }

    public boolean registrate(RegistrationDTO registrationDTO){
        try {


            //registrate value
            String name = registrationDTO.getName();
            String surname = registrationDTO.getSurname();
            String username = registrationDTO.getUsername();
            String password = registrationDTO.getPassword();
            String email = registrationDTO.getEmail();
            //User u=loginRepository.findByUsername(username);
            User existingUser = loginRepository.findByUsername(username);

            // If the user with the same username exists, return false
            if (existingUser != null) {
                return false;
            }

            // If the user with the same username doesn't exist, you can proceed with registration logic
            // We want to create a new User object and save it to the database

            User newUser = new User();
            newUser.setName(name);
            newUser.setSurname(surname);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setEmail(email);

            // Save the new user to the database
            loginRepository.save(newUser);

            // Return true to indicate successful registration
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
           return false;
        }

    }
}

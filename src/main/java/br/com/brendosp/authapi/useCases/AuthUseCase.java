package br.com.brendosp.authapi.useCases;

import br.com.brendosp.authapi.dtos.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUseCase {

    public final AuthenticationManager authenticationManager;
    public final JwtUseCase jwtUseCase;

    public String login(LoginRequestDTO loginRequestDTO) {
        var auth = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());

        Authentication authenticatedUser = authenticationManager.authenticate(auth);

        return jwtUseCase.generateToken((UserDetails) authenticatedUser.getPrincipal());
    }
}

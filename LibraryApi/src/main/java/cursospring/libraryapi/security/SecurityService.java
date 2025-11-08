package cursospring.libraryapi.security;

import cursospring.libraryapi.model.Usuario;
import cursospring.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UsuarioService service;

    public Usuario obterUsuarioPorAuth(Authentication auth){
//        UserDetails userDetails = (UserDetails)auth.getPrincipal();
//        return service.obterPorLogin(userDetails.getUsername());
        if (auth instanceof CustomAuthentication customAuthentication){
            return customAuthentication.getUsuario();
        }
        return null;
    }

}

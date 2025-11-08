package cursospring.libraryapi.security;

import cursospring.libraryapi.model.Usuario;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CustomAuthentication implements Authentication {

    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuario.getRoles().stream().map(
                SimpleGrantedAuthority::new
//                role -> new SimpleGrantedAuthority("ROLE_"+role)
        ).toList();
    }

    @Override
    public Object getCredentials() {
        return null; // senha do usuario
    }

    @Override
    public Object getDetails() {
        return null; // detalhes que vc queira colocar
    }

    @Override
    public Object getPrincipal() {
        return usuario; //objeto usuário
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return usuario.getLogin();
    }
}

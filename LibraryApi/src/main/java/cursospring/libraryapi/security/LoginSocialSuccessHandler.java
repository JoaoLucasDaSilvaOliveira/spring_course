package cursospring.libraryapi.security;

import cursospring.libraryapi.model.Usuario;
import cursospring.libraryapi.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UsuarioService service;
    private final PasswordEncoder encoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken auth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2User auth2User = auth2Token.getPrincipal();
        String email = auth2User.getAttribute("email");

        Usuario usuario = service.encontrarUsuarioPorEmail(email);

        if (usuario == null){
            usuario = salvarUsuarioNaBaseDeDados(auth2User.getAttributes());
        }

        CustomAuthentication customAuthentication = new CustomAuthentication(usuario);

        SecurityContextHolder.getContext().setAuthentication(customAuthentication);

        super.onAuthenticationSuccess(request, response, customAuthentication);
    }

    public Usuario salvarUsuarioNaBaseDeDados(Map<String, Object> attributes){
        Usuario usuario = new Usuario();
        usuario.setLogin((String)attributes.get("name"));
        usuario.setEmail((String)attributes.get("email"));
        usuario.setRoles(List.of("OPERADOR"));
        usuario.setSenha(encoder.encode(String.valueOf(UUID.randomUUID())));

        return service.salvar(usuario);
    }
}

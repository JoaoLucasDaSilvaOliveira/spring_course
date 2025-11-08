package cursospring.libraryapi.service;

import cursospring.libraryapi.model.Usuario;
import cursospring.libraryapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public Usuario salvar(Usuario usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public Usuario obterPorLogin (String login){
        return repository.findByLogin(login);
    }

    public Usuario encontrarUsuarioPorEmail(String email) {
        return repository.findUsuarioByEmail(email);
    }
}

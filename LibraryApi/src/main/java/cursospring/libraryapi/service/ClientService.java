package cursospring.libraryapi.service;

import cursospring.libraryapi.model.Client;
import cursospring.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client salvar (Client client){
        client.setClientSecret(encoder.encode(client.getClientSecret()));
        return repository.save(client);
    }

    public Client obterPorClientId(String clientId){
        return repository.findByClientId(clientId);
    }

}

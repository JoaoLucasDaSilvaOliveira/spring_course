package cursospring.arquiteturaspring.montadora.api;

import cursospring.arquiteturaspring.montadora.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class TesteFabricaController {

    @Autowired
    //Como temos vários beans já configurados, precisamos dizer qual usar:
    //@Qualifier("motorTurbo")
    //usando annotations personalizadas com qualifiers embutidos
    @Aspirado
    private Motor motor;

    @PostMapping
    public CarroStatus ligarCarro (@RequestBody Chave chave){
        var carro = new HondaCivic(motor);
        return carro.ligar(chave);
    }
}

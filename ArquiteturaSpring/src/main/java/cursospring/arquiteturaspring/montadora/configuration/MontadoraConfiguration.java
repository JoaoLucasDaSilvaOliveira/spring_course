package cursospring.arquiteturaspring.montadora.configuration;

import cursospring.arquiteturaspring.montadora.Motor;
import cursospring.arquiteturaspring.montadora.TipoMotor;
import cursospring.arquiteturaspring.montadora.api.Aspirado;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    //aqui cria-se uma dependência a ser injetada. Isso significa que eu criei um modelo de motor que pode ser injetado pelo spring em alguma classe
    // IMPORTANTE! Quando temos mais de um bean em um arquivo de configuração precisamos dizer qual bean usar onde será injetada o modelo
    //podemos ou não mudar o nome do bean, por padrão é o nome da função, ex abaixo bean name= "motorAspirado"
    @Bean(name = "motorAspirado")
    @Aspirado
    public Motor motorAspirado() {
        var motor = new Motor();
        motor.setTipoMotor(TipoMotor.ASPIRADO);
        motor.setCavalos("150");
        motor.setCilindros("4");
        motor.setLitragem(100.0);
        motor.setModelo("XPTO-0");
        return motor;
    }

    @Bean
    public Motor motorTurbo() {
        var motor = new Motor();
        motor.setTipoMotor(TipoMotor.TURBO);
        motor.setCavalos("200");
        motor.setCilindros("6");
        motor.setLitragem(150.0);
        motor.setModelo("TURB");
        return motor;
    }

    @Bean
    @Primary
    public Motor motorEletrico() {
        var motor = new Motor();
        motor.setTipoMotor(TipoMotor.ELETRICO);
        motor.setCavalos("300");
        motor.setCilindros("2");
        motor.setLitragem(0.0);
        motor.setModelo("ELETRIC");
        return motor;
    }
}

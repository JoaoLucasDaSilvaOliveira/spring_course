package cursospring.arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args); //ESTE É O MODO MAIS COMUM/FACIL DE INICIAR A APLICAÇÃO

        //forma que funciona por baixo dos panos:
        SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(Application.class);

        applicationBuilder.bannerMode(Banner.Mode.OFF); //RETIRA O BANNER DO CONSOLE

        applicationBuilder.run(args);

        ConfigurableApplicationContext context = applicationBuilder.context(); //contexto da aplicação

        //var produtoRepository = context.getBean("produtoRepository");// obtem um bean por nome

        applicationBuilder.profiles("producao", "homologacao"); //define perfis de onde a aplicação vai rodar, pode conter vars específicas;

        ConfigurableEnvironment environment = context.getEnvironment(); // retorna o ambiente da aplicação

        String property = environment.getProperty("spring.application.name");// retorna uma propriedade em específico;
        System.out.println("Nome da aplicação: " + property);
    }

}

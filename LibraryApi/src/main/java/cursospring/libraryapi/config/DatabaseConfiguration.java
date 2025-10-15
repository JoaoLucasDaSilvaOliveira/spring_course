package cursospring.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//AULA DE CONFIGURAÇÃO DE DATASOURCES E POOL DE CONEXÕES
@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    public DataSource dataSource (){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setPassword(password);
        ds.setUsername(username);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public DataSource hikariDataSource (){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setPassword(password);
        hikariConfig.setUsername(username);
        hikariConfig.setDriverClassName(driver);

        //configs de conexões
        hikariConfig.setMaximumPoolSize(10);// máximo de conexões solicitadas ao banco
        hikariConfig.setMinimumIdle(1);//tamanho inicial do pool
        hikariConfig.setPoolName("Meu-Pool");
        hikariConfig.setMaxLifetime(600000); // 600 mil milisegundos (10 min)
        hikariConfig.setConnectionTimeout(3000); // tenta conseguir uma conexão do pool em até 3000 ms
        hikariConfig.setConnectionTestQuery("SELECT 1"); // query de teste

        return new HikariDataSource(hikariConfig);
    }
}

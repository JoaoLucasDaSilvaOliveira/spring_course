package cursospring.libraryapi.config;

import cursospring.libraryapi.security.LoginSocialSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity, LoginSocialSuccessHandler successHandler) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //config de app web
                .formLogin(
                        config -> config.loginPage("/login").permitAll()
                ) //pagina padrão: Customizer.withDefaults()
                .authorizeHttpRequests(
                        configurer -> {
                            configurer.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();
//                            configurer.requestMatchers("/autores/**").hasRole("ADMIN"); // PODEMOS TER TAMBEM A IMPLEMENTAÇÃO COM HttpMethod.POST/DELETE/...
//                            configurer.requestMatchers("/autores/**").hasAnyRole("ADMIN", "USER");
                            configurer.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(oauth2 -> {
                    oauth2
                            .loginPage("/login")
                            .successHandler(successHandler);
                })
                .oauth2ResourceServer(auth -> auth.jwt(Customizer.withDefaults()))
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService (UsuarioService usuarioService){
//        UserDetails user1 = User.builder()
//                .username("Joao")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("admin")
//                .password(encoder.encode("456"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);

//        return new CustomUserDetailsService(usuarioService);
//    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults (){
        return new GrantedAuthorityDefaults("");
    }

}

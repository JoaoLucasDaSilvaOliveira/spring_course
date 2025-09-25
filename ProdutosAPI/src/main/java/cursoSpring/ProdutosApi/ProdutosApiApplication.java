package cursoSpring.ProdutosApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//aula01
// /*@RestController*/
public class ProdutosApiApplication {
	//aula 01
	/*@GetMapping("/api/hello")
	public String hello (){
		return "Hello World!";
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ProdutosApiApplication.class, args);
	}

}

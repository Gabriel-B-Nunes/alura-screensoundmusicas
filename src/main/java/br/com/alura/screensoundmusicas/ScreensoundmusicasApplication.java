package br.com.alura.screensoundmusicas;

import br.com.alura.screensoundmusicas.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundmusicasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreensoundmusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}

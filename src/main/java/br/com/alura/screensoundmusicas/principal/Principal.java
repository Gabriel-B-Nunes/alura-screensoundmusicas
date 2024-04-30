package br.com.alura.screensoundmusicas.principal;

import br.com.alura.screensoundmusicas.model.Artista;
import br.com.alura.screensoundmusicas.model.Musica;
import br.com.alura.screensoundmusicas.model.TipoArtista;
import br.com.alura.screensoundmusicas.repository.ArtistaRepository;
import br.com.alura.screensoundmusicas.service.ConsultaChatGPT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ArtistaRepository repository;

    public Principal(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    *** Screen Sound Músicas ***
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artista
                    5 - Pesquisar dados sobre um artista
                    
                    0 - sair
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastraArtistas();
                    break;

                case 2:
                    cadastraMusicas();
                    break;

                case 3:
                    listaMusicas();
                    break;

                case 4:
                    buscaMusicasPorArtista();
                    break;

                case 5:
                    pesquisaArtista();
                    break;

                case 0:
                    opcao = 0;
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private void pesquisaArtista() {
        System.out.println("Informe o nome do artista");
        String nomeArtista = scanner.nextLine();
        String pesquisa = ConsultaChatGPT.pesquisaArtista(nomeArtista);
        System.out.println(pesquisa);
    }

    private void buscaMusicasPorArtista() {
        System.out.println("Informe o nome do artista");
        String nomeArtista = scanner.nextLine();
        List<Musica> musicaList = repository.buscaMusicaPorArtista(nomeArtista);
        if (!musicaList.isEmpty()) {
            musicaList.forEach(System.out::println);
        } else {
            System.out.println("Nenhuma música encontrada");
        }
    }

    private void listaMusicas() {
        List<Musica> musicaList = repository.listaMusicas();
        if (!musicaList.isEmpty()) {
            musicaList.forEach(System.out::println);
        } else {
            System.out.println("Nenhuma música encontrada");
        }
    }

    private void cadastraMusicas() {
        String opcao = "S";
        List<Musica> musicaList = new ArrayList<>();
        while (opcao.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome do artista");
            String nomeArtista = scanner.nextLine();
            Optional<Artista> artista = repository.findByNomeContainingIgnoreCase(nomeArtista);
            if (artista.isPresent()) {
                System.out.println("Informe o nome da música");
                String nomeMusica = scanner.nextLine();
                System.out.println("Informe o álbum");
                String nomeAlbum = scanner.nextLine();
                Musica musica = new Musica(nomeMusica, nomeAlbum, artista.get());
                musicaList.add(musica);
                artista.get().setMusicaList(musicaList);

                repository.save(artista.get());

                System.out.println("Cadastrar outra música? (S/N)");
                opcao = scanner.nextLine();
            } else {
                System.out.println("Artista não encontrado!");
                opcao = "N";
            }
        }
    }

    private void cadastraArtistas() {
        String opcao = "S";
        while (opcao.equalsIgnoreCase("S")) {
            System.out.println("Informe o nome do(s) artista(s)");
            String nomeArtista = scanner.nextLine();
            System.out.println("Informe o tipo do artista (solo, dupla ou banda)");
            String tipo = scanner.nextLine();
            TipoArtista tipoArtista = TipoArtista.fromString(tipo);
            Artista artista = new Artista(nomeArtista, tipoArtista);

            repository.save(artista);

            System.out.println("Cadastrar outro artista? (S/N)");
            opcao = scanner.nextLine();
        }
    }
}

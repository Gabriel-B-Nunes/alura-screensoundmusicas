package br.com.alura.screensoundmusicas.repository;

import br.com.alura.screensoundmusicas.model.Artista;
import br.com.alura.screensoundmusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeArtista);

    @Query("SELECT m FROM Artista a JOIN a.musicaList m")
    List<Musica> listaMusicas();

    @Query("SELECT m FROM Artista a JOIN a.musicaList m WHERE a.nome ILIKE %:nomeArtista%")
    List<Musica> buscaMusicaPorArtista(String nomeArtista);
}

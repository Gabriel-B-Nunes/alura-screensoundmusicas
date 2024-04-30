package br.com.alura.screensoundmusicas.model;

public enum TipoArtista {
    SOLO ("solo"),
    DUPLA ("dupla"),
    BANDA ("banda"),
    ;

    private String tipoArtista;

    TipoArtista(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public static TipoArtista fromString (String texto) {
        for (TipoArtista tipoArtista : TipoArtista.values()) {
            if (tipoArtista.tipoArtista.equalsIgnoreCase(texto)) {
                return tipoArtista;
            }
        }
        throw new RuntimeException("Não encontramos uma categoria para a string: " + texto);
    }
}

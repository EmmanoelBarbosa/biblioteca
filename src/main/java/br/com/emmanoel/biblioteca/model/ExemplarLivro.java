package br.com.emmanoel.biblioteca.model;

import br.com.emmanoel.biblioteca.enums.StatusExemplar;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exemplares")
@Data
public class ExemplarLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusExemplar status;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
}

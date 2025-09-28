package br.com.emmanoel.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "livros")
@Data
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @ManyToMany

    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<ExemplarLivro> exemplares = new ArrayList<>();
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private Set<Emprestimo> emprestimos;
}
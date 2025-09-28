package br.com.emmanoel.biblioteca.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public void setPessoa(Pessoa pessoa) {
    }


    public void setLivro(Livro livro) {
    }

    public void setDataEmprestimo(LocalDate now) {
    }
}

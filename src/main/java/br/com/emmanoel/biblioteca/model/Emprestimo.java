package br.com.emmanoel.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "emprestimos")
@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHoraEmprestimo = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @OneToMany
    @JoinColumn(name = "emprestimo_id")
    private List<ExemplarLivro> exemplares = new ArrayList<>();

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

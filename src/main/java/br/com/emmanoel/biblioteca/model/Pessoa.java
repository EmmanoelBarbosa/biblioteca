package br.com.emmanoel.biblioteca.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;


    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Set<Emprestimo> emprestimos;

}
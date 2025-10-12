package br.com.emmanoel.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "pessoas")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    private String cpf;

    @Email
    @NotBlank
    private String email;


    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Set<Emprestimo> emprestimos;

}
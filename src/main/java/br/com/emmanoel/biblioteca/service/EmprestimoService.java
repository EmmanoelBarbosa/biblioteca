package br.com.emmanoel.biblioteca.service;

import br.com.emmanoel.biblioteca.model.Emprestimo;
import br.com.emmanoel.biblioteca.model.Livro;
import br.com.emmanoel.biblioteca.model.Pessoa;
import br.com.emmanoel.biblioteca.repository.EmprestimoRepository;
import br.com.emmanoel.biblioteca.repository.LivroRepository;
import br.com.emmanoel.biblioteca.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private LivroRepository livroRepository;

    public Emprestimo realizarEmprestimo(Long pessoaId, Long livroId) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        Livro livro = livroRepository.findById(livroId).orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setPessoa(pessoa);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());

        return emprestimoRepository.save(emprestimo);
    }
    public Optional<Emprestimo> buscarPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

}
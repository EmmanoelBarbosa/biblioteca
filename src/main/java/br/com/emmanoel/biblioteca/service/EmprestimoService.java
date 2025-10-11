package br.com.emmanoel.biblioteca.service;

import br.com.emmanoel.biblioteca.enums.StatusExemplar;
import br.com.emmanoel.biblioteca.model.Emprestimo;
import br.com.emmanoel.biblioteca.model.ExemplarLivro;
import br.com.emmanoel.biblioteca.model.Livro;
import br.com.emmanoel.biblioteca.model.Pessoa;
import br.com.emmanoel.biblioteca.repository.EmprestimoRepository;
import br.com.emmanoel.biblioteca.repository.ExemplarRepository;
import br.com.emmanoel.biblioteca.repository.LivroRepository;
import br.com.emmanoel.biblioteca.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    private static final Logger logger = LoggerFactory.getLogger(EmprestimoService.class);

    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;
    public EmprestimoService(EmprestimoRepository emprestimoRepository, ExemplarRepository exemplarRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
    }

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
    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        logger.info("Tentando realizar empréstimo para pessoa id={}...", emprestimo.getPessoa().getId());

        for (ExemplarLivro ex : emprestimo.getExemplares()) {
            ExemplarLivro exemplar = exemplarRepository.findById(ex.getId())
                    .orElseThrow(() -> {
                        logger.error("Exemplar id={} não encontrado", ex.getId());
                        return new RuntimeException("Exemplar não encontrado");
                    });

            if (exemplar.getStatus() != StatusExemplar.DISPONIVEL) {
                logger.error("Exemplar id={} não está disponível (status={})", exemplar.getId(), exemplar.getStatus());
                throw new RuntimeException("Exemplar não disponível para empréstimo: " + exemplar.getId());
            }

            exemplar.setStatus(StatusExemplar.EMPRESTADO);
            exemplarRepository.save(exemplar);
            logger.info("Exemplar id={} marcado como EMPRESTADO", exemplar.getId());
        }
        Emprestimo salvo = emprestimoRepository.save(emprestimo);
        logger.info("Empréstimo id={} salvo com sucesso", salvo.getId());
        return salvo;
    }

    public List<Emprestimo> buscarPorIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        return emprestimoRepository.findByDataHoraEmprestimoBetween(inicio, fim);
    }
    public Optional<Emprestimo> buscarPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

}
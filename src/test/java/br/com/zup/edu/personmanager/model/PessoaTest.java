package br.com.zup.edu.personmanager.model;

import br.com.zup.edu.personmanager.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessoaTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PessoaRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Transactional
    @Test
    public void entidadeTransient() {

        Pessoa pessoa = new Pessoa("José Denes", "12345678", "Denes", LocalDate.now());

        assertNull(pessoa.getId());
        assertFalse(entityManager.contains(pessoa));
    }

    @Transactional
    @Test
    public void entidadeDeTransientParaManaged() {

        Pessoa pessoa = new Pessoa("José Denes", "12345678", "Denes", LocalDate.now());
        entityManager.persist(pessoa);

        assertNotNull(pessoa.getId());
        assertTrue(entityManager.contains(pessoa));
    }

    @Transactional
    @Test
    public void entidadeDeManagedParaDetached() {

        Pessoa pessoa = new Pessoa("José Denes", "12345678", "Denes", LocalDate.now());
        entityManager.persist(pessoa);
        entityManager.detach(pessoa);

        assertFalse(entityManager.contains(pessoa));
    }

    @Transactional
    @Test
    public void entidadeDeDetachedParaManaged() {

        Pessoa pessoa = new Pessoa("José Denes", "12345678", "Denes", LocalDate.now());
        entityManager.persist(pessoa);
        entityManager.detach(pessoa);

        Pessoa pessoaMerge = entityManager.merge(pessoa);

        assertEquals(pessoa.getId(),pessoaMerge.getId());
        assertTrue(entityManager.contains(pessoaMerge));
    }

    @Transactional
    @Test
    public void entidadeDeManagedParaRemoved() {

        Pessoa pessoa = new Pessoa("José Denes", "12345678", "Denes", LocalDate.now());
        entityManager.persist(pessoa);
        entityManager.remove(pessoa);

        assertNull(entityManager.find(Pessoa.class, pessoa.getId()));
        assertFalse(entityManager.contains(pessoa));
    }

    @Transactional
    @Test
    public void entidadeDeRemovedParaManaged() {

        Pessoa pessoa = new Pessoa("José Denes", "12345678", "Denes", LocalDate.now());
        entityManager.persist(pessoa);
        entityManager.remove(pessoa);
        entityManager.persist(pessoa);

        assertNotNull(entityManager.find(Pessoa.class, pessoa.getId()));
        assertTrue(entityManager.contains(pessoa));
    }

}
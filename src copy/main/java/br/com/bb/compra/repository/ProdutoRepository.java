package br.com.bb.compra.repository;
import br.com.bb.compra.model.Produto;
import br.com.bb.compra.model.entity.ClienteEntity;
import br.com.bb.compra.model.entity.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import lombok.RequiredArgsConstructor;
import br.com.bb.compra.converter.ProdutoConverter;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import java.math.BigDecimal;

@RequiredArgsConstructor
@ApplicationScoped
public class ProdutoRepository {

    private final ProdutoRepository produtoRepository;

    @Inject
    EntityManager em;

    @Transactional
    public void save(ProdutoEntity entity) {
        em.persist(entity);
    }

    public ProdutoEntity findById(Long id) {
        return em.find(ProdutoEntity.class, id);
    }

    public List<ProdutoEntity> findAll() {
        final TypedQuery<ProdutoEntity> typedQuery = em.createQuery("select c from ProdutoEntity c", ProdutoEntity.class);
        return typedQuery.getResultList();
    }

    //@Override
    public void delete(Long id) {
        em.remove(id);
    }

}

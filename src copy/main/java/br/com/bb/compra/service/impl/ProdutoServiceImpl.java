package br.com.bb.compra.service.impl;

import br.com.bb.compra.converter.ProdutoConverter;
import br.com.bb.compra.model.Produto;
import br.com.bb.compra.model.entity.ProdutoEntity;
import br.com.bb.compra.repository.ProdutoRepository;
import br.com.bb.compra.service.ProdutoService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

//@RequestScoped para cada request
//@SessionScoped para cada sessao => cookie JSESSIONID
//@Singleton instancia classe no startup
//@ApplicationScoped instancia quando voce usa pela primeira vez => cria uma classe proxy

@ApplicationScoped
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    @Override
    @Transactional
    public Produto salvar(Produto novoProduto) {
        final ProdutoEntity produtoEntity = ProdutoConverter.convertProductTo(novoProduto);
        repository.save(produtoEntity);
        return ProdutoConverter.convertEntityTo(produtoEntity);
    }

    //@Override
    //@Transactional
    //public List<Produto> salvarLista(List<Produto> novoProduto) {
    //    final List<ProdutoEntity> produtoEntityList = ProdutoConverter.convertProductTo(novoProduto);
    //    repository.save(produtoEntityList);
    //    return ProdutoConverter.convertEntityTo(produtoEntityList);
    //}

    //@Override
    public Produto buscaPorId(Long id) {
        final ProdutoEntity produtoSalvo = repository.findById(id);
        if (produtoSalvo.getId() == null) {
            throw new RuntimeException();
        }
        return ProdutoConverter.convertEntityTo(produtoSalvo);
    }

    //@Override
    //public boolean isEmpty() {
    //    return repository.count() == 0;
    //}

    @Override
    public boolean excluir(Long id) {
        if (repository.findById(id) == null) {
            throw new RuntimeException();
        } else {
            repository.delete(id);
            return true;
        }
        
    }

    @Override
    public List<Produto> buscarTodos() {
        return convert(repository.findAll());
    }

    //@Override
    private Produto convert(ProdutoEntity entity) {
        return Produto.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .desconto(entity.getDesconto())
                .estoque(entity.getEstoque())
                .foto(entity.getFoto())
                .build();
    }

    private List<Produto> convert(List<ProdutoEntity> entities) {
        return entities.stream().map(this::convert)
                .collect(Collectors.toList());
    }

}

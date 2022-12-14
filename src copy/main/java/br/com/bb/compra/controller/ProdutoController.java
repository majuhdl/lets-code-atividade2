package br.com.bb.compra.controller;

import br.com.bb.compra.model.Produto;
import br.com.bb.compra.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GET
    @Path("/{id}")
    public Response getId(@PathParam("id") Long id) {
        return Response.ok(produtoService.buscaPorId(id)).build();
    }

    @POST
    public Response salvar(@Valid Produto produto) {
        return Response.ok(produtoService.salvar(produto)).build();
    }

    @DELETE
    public Response excluir(@PathParam("id") Long id) {
        return Response.ok(produtoService.excluir(id)).build();
    }

    @GET
    @Path("/all")
    public Response buscarTodos() {
        return Response.ok(produtoService.buscarTodos()).build();
    }

}

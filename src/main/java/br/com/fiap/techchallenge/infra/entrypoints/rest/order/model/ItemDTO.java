package br.com.fiap.techchallenge.infra.entrypoints.rest.order.model;


import java.util.ArrayList;
import java.util.List;

public class ItemDTO {

    private List<ItemPedidoDTO> lanches = new ArrayList<>();
    private List<ItemPedidoDTO> acompanhamento = new ArrayList<>();
    private List<ItemPedidoDTO> bebida = new ArrayList<>();
    private List<ItemPedidoDTO> sobremesa = new ArrayList<>();

    public ItemDTO() { }

    public ItemDTO(List<ItemPedidoDTO> sobremesa, List<ItemPedidoDTO> bebida, List<ItemPedidoDTO> acompanhamento, List<ItemPedidoDTO> lanches) {
        this.sobremesa = sobremesa;
        this.bebida = bebida;
        this.acompanhamento = acompanhamento;
        this.lanches = lanches;
    }

    public List<ItemPedidoDTO> getLanches() {
        return lanches;
    }

    public void setLanches(List<ItemPedidoDTO> lanches) {
        this.lanches = lanches;
    }

    public List<ItemPedidoDTO> getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(List<ItemPedidoDTO> acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public List<ItemPedidoDTO> getBebida() {
        return bebida;
    }

    public void setBebida(List<ItemPedidoDTO> bebida) {
        this.bebida = bebida;
    }

    public List<ItemPedidoDTO> getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(List<ItemPedidoDTO> sobremesa) {
        this.sobremesa = sobremesa;
    }
}
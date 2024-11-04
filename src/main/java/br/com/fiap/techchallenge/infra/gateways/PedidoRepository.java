package br.com.fiap.techchallenge.infra.gateways;

import br.com.fiap.techchallenge.application.gateways.IPedidoRepository;
import br.com.fiap.techchallenge.domain.ErrosEnum;
import br.com.fiap.techchallenge.domain.entities.cliente.Cliente;
import br.com.fiap.techchallenge.domain.entities.pagamento.StatusPagamento;
import br.com.fiap.techchallenge.domain.entities.pagamento.StatusPagamentoEnum;
import br.com.fiap.techchallenge.domain.entities.pedido.Pedido;
import br.com.fiap.techchallenge.domain.entities.pedido.StatusPedido;
import br.com.fiap.techchallenge.domain.entities.pedido.StatusPedidoEnum;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.PedidoEntity;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.ClienteEntity;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.ProdutoPedidoEntity;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.StatusPagamentoEntity;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.entities.StatusPedidoEntity;
import br.com.fiap.techchallenge.infra.exception.BaseException;
import br.com.fiap.techchallenge.infra.exception.ClienteException;
import br.com.fiap.techchallenge.infra.exception.PedidoException;
import br.com.fiap.techchallenge.infra.mapper.cliente.ClienteMapper;
import br.com.fiap.techchallenge.infra.mapper.pedido.PedidoMapper;
import br.com.fiap.techchallenge.infra.mapper.produtopedido.ProdutoPedidoMapper;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.ClienteEntityRepository;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.PedidoEntityRepository;
import br.com.fiap.techchallenge.infra.dataproviders.database.persistence.remover.ProdutoPedidoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

@Slf4j
public class PedidoRepository implements IPedidoRepository {

    private final PedidoEntityRepository pedidoEntityRepository;
    private final PedidoMapper pedidoMapper;
    private final ClienteEntityRepository clienteEntityRepository;
    private final ProdutoPedidoRepository produtoPedidoRepository;
    private final ProdutoPedidoMapper produtoPedidoMapper;

    public PedidoRepository(PedidoEntityRepository pedidoEntityRepository, PedidoMapper pedidoMapper, ClienteEntityRepository clienteEntityRepository, ProdutoPedidoRepository produtoPedidoRepository, ProdutoPedidoMapper produtoPedidoMapper) {
        this.pedidoEntityRepository = pedidoEntityRepository;
        this.pedidoMapper = pedidoMapper;
        this.clienteEntityRepository = clienteEntityRepository;
        this.produtoPedidoRepository = produtoPedidoRepository;
        this.produtoPedidoMapper = produtoPedidoMapper;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        Cliente cliente = buscarCliente(pedido);

        pedido.setCliente(cliente);
        pedido.setStatusPagamento(new StatusPagamento(StatusPagamentoEnum.PENDENTE.getStatus()));
        pedido.setStatus(new StatusPedido(StatusPedidoEnum.RECEBIDO.getStatus()));

        PedidoEntity pedidoEntity = pedidoEntityRepository.saveAndFlush(pedidoMapper.fromDomainToEntity(pedido));
        List<ProdutoPedidoEntity> produtoPedidos = produtoPedidoMapper.fromListDomainToListEntity(pedido.getProdutoPedidos());
        produtoPedidos.parallelStream().forEach(item -> item.setPedidoEntity(pedidoEntity));
        pedidoEntity.setProdutos(produtoPedidos);
        produtoPedidoRepository.saveAllAndFlush(produtoPedidos);
        return pedidoMapper.fromEntityToDomain(pedidoEntity);
    }

    @Override
    public Pedido atualizarStatusDoPedido(Pedido pedidoAtualizado) {
        log.info("Atualizando status do pedido.");
        definirDataFinalizacaoPedido(pedidoAtualizado);
        return pedidoMapper.fromEntityToDomain(pedidoEntityRepository.saveAndFlush(pedidoMapper.fromDomainToEntity(pedidoAtualizado)));
    }

    private Cliente buscarCliente(Pedido request) {
        if (request.getCliente() == null || request.getCliente().getCpf().isBlank() || request.getCliente().getCpf().isEmpty()) {
            return null;
        }
        Optional<ClienteEntity> clienteEntity = clienteEntityRepository.findByCpf(request.getCliente().getCpf());
        if (clienteEntity.isEmpty()) {
            throw new ClienteException(ErrosEnum.CLIENTE_CPF_NAO_EXISTE);
        }
        return new ClienteMapper().fromEntityToDomain(clienteEntity.get());
    }

    private void definirDataFinalizacaoPedido(Pedido novo) {
        StatusPedidoEnum statusNovo = StatusPedidoEnum.byId(novo.getStatus().getId());
        if (StatusPedidoEnum.FINALIZADO.equals(statusNovo)) {
            novo.setDataFinalizacao(LocalDateTime.now());
        }
    }

    @Override
    public Pedido atualizarPagamentoDoPedido(Pedido pedido) {
        log.info("Atualizando status de pagamento do pedido.");
        return pedidoMapper.fromEntityToDomain(pedidoEntityRepository.saveAndFlush(pedidoMapper.fromDomainToEntity(pedido)));
    }

    @Override
    public StatusPagamento consultarStatusPagamentoDoPedido(Long id) {
        log.info("Consultar status de pagamento do pedido.");
        Optional<PedidoEntity> pedidoOptional = pedidoEntityRepository.findById(id);
        if (pedidoOptional.isEmpty()) {
            throw new PedidoException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO);
        }
        Pedido pedido = pedidoMapper.fromEntityToDomain(pedidoOptional.get());
        return pedido.getStatusPagamento();
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {
        log.info("buscarPedidoPorId");
        try {
            PedidoEntity pedido = this.pedidoEntityRepository.findById(id).orElseThrow(() -> new PedidoException(ErrosEnum.PEDIDO_CODIGO_IDENTIFICADOR_INVALIDO));
            return pedidoMapper.fromEntityToDomain(pedido);
        } catch (Exception e) {
            log.error("O identificador informado não existe no banco de dados.");
            return null;
        }
    }

    @Override
    public List<Pedido> listarPedidos() {
        log.info("listarPedidos");
        List<PedidoEntity> listPedidoEntity = removerPedidosFinalizadosCancelados(obterPedidosOrdenadosPeloStatus());

        return pedidoMapper.fromListEntityToListDTO(listPedidoEntity);
    }

    private List<PedidoEntity> obterPedidosOrdenadosPeloStatus() {
        return pedidoEntityRepository.findAll(Sort.by("status.id").descending());
    }

    private List<PedidoEntity> removerPedidosFinalizadosCancelados(List<PedidoEntity> listaPedido) {
        Predicate<PedidoEntity> exceptDone = pedidoEntity -> !(pedidoEntity.getStatus().getId().equals(StatusPedidoEnum.FINALIZADO.getId()));
        Predicate<PedidoEntity> exceptCancelled = pedidoEntity -> !(pedidoEntity.getStatus().getId().equals(StatusPedidoEnum.CANCELADO.getId()));
        return listaPedido.stream().filter(exceptDone.and(exceptCancelled)).sorted(this::ordenarPorHorarioRecebimento).sorted(this::ordernarPorStatus).toList();
    }

    private int ordenarPorHorarioRecebimento(PedidoEntity pedidoEntity, PedidoEntity pedidoEntity1) {
        return pedidoEntity.getDataCriacao().compareTo(pedidoEntity1.getDataCriacao());
    }

    private int ordernarPorStatus(PedidoEntity pedidoEntity, PedidoEntity pedidoEntity1) {
        return pedidoEntity1.getStatus().getId().compareTo(pedidoEntity.getStatus().getId());
    }

    @Override
    public List<Pedido> listarPedidosPorStatus(String status) {
        StatusPedidoEnum statusPedidoEnum = StatusPedidoEnum.byStatus(status);

        List<Pedido> pedidos = this.listarPedidos();
        if (pedidos == null || pedidos.isEmpty()) {
            return new ArrayList<>();
        }

        Predicate<Pedido> byStatus = sp -> sp.getStatus().getId().equals(statusPedidoEnum.getId());
        return pedidos.stream().filter(byStatus).toList();
    }

}

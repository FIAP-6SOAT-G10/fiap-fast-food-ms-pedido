package br.com.fiap.techchallenge.infra.mapper;

import br.com.fiap.techchallenge.domain.entities.payment.PaymentRequest;
import br.com.fiap.techchallenge.infra.entrypoints.queue.model.PaymentRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentMapper {

    public PaymentRequest fromDataTransferObjetToDomain(PaymentRequestDTO paymentRequestDTO) {
        return new PaymentRequest(
                paymentRequestDTO.getExternalOrderId(),
                paymentRequestDTO.getInternalOrderId(),
                paymentRequestDTO.getPayer(),
                paymentRequestDTO.getPaymentAmount(),
                paymentRequestDTO.getPaymentDate(),
                paymentRequestDTO.getPaymentStatus());
    }

//    public List<Item> fromListDTOToListDomain(List<ItemDTO> list) {
//        return list.stream().map(this::fromDTOToDomain).toList();
//    }
//
//    public ItemEntity fromDomainToEntity(Item item) {
//        return new ItemEntity(item.getItemId(), item.getPrice(), item.getQuantity());
//    }
//
//    public List<ItemEntity> fromListDomainToListEntity(List<Item> list) {
//        return list.stream().map(this::fromDomainToEntity).toList();
//    }
//
//    public ItemDTO fromDomainToDTO(Item item) {
//        return new ItemDTO(item.getItemId(), item.getPrice(), item.getQuantity());
//    }
//
//    public List<ItemDTO> fromListDomainToListDTO(List<Item> list) {
//        return list.stream().map(this::fromDomainToDTO).toList();
//    }
//
//    public Item fromEntityToDomain(ItemEntity item) {
//        return new Item(item.getItemId(), item.getPrice(), item.getQuantity());
//    }
//
//    public List<Item> fromListEntityToListDomain(List<ItemEntity> list) {
//        return list.stream().map(this::fromEntityToDomain).toList();
//    }

}

package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.OrderDetailsDTO;
import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.*;
import lk.ijse.spring.repo.ItemRepo;
import lk.ijse.spring.repo.OrderDetailsRepo;
import lk.ijse.spring.repo.OrdersRepo;
import lk.ijse.spring.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/
@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    /*public ModelMapper modelMapper(){
        return new ModelMapper();
    }*/

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    /*@Autowired
    private ModelMapper mapper;*/

    @Override
    public void purchaseOrder(OrdersDTO dto) {
        Orders orders = new Orders();
        orders.setOid(dto.getOid());
        orders.setDate(dto.getDate());

        CustomerDTO customer = dto.getCustomer();
        Customer customerEntity = new Customer(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        orders.setCustomer(customerEntity);

        List<OrderDetailsDTO> orderDetailsDtoList = dto.getOrderDetails();
        List<OrderDetails> orderDetailsList = orderDetailsDtoList.stream()
                .map(orderDetailsDto -> {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setOid(orderDetailsDto.getOid());
                    orderDetails.setItemCode(orderDetailsDto.getItemCode());
                    orderDetails.setQty(orderDetailsDto.getQty());
                    orderDetails.setUnitPrice(orderDetailsDto.getUnitPrice());
                    return orderDetails;
                })
                .collect(Collectors.toList());
        orders.setOrderDetails(orderDetailsList);


        if(!ordersRepo.existsById(dto.getOid())){
            ordersRepo.save(orders);
            if(dto.getOrderDetails().size() < 1)throw new RuntimeException("No items added!");

            //update item
            for (OrderDetails orderDetails : orders.getOrderDetails()) {
                Item item = itemRepo.findById(orderDetails.getItemCode()).get();
                item.setQtyOnHand(item.getQtyOnHand()-orderDetails.getQty());
                itemRepo.save(item);
            }
        }else {
            throw new RuntimeException("Purchase order failed!");
        }

    }

    @Override
    public void deleteOrder(String oid) {
        if(ordersRepo.existsById(oid)){
            ordersRepo.deleteById(oid);
        }else {
            throw new RuntimeException("Order delete failed!");
        }
    }

    @Override
    public void updateOrder(OrdersDTO dto) {
        /*Orders orders = mapper.map(dto, Orders.class);
        if(ordersRepo.existsById(dto.getOid())){
            mapper.map(dto, Orders.class);
            if(dto.getOrderDetails().size() < 1)throw new RuntimeException("No items found!");


            for (OrderDetails od : orders.getOrderDetails()) {
                Item item = itemRepo.findById(od.getItemCode()).get();
                OrderDetails previous = orderDetailsRepo.findById(new OrderItem_PK(od.getOid(), od.getItemCode()));

                int newQty = od.getQty();
                int prevQty = previous.getQty();
                if(newQty > prevQty){
                    int dif=newQty-prevQty;
                    item.setQtyOnHand(item.getQtyOnHand()-dif);

                }else if(newQty < prevQty) {
                    int dif =prevQty -newQty;
                    item.setQtyOnHand(item.getQtyOnHand()+dif);
                }
                itemRepo.save(item);
            }
            //delete the old order
            ordersRepo.deleteById(dto.getOid());

            //finally update order
            ordersRepo.save(orders);

        }else{
            throw new RuntimeException("Order update failed!");
        }*/
    }

    @Override
    public OrdersDTO searchOrder(String oid) {
        /*if(ordersRepo.existsById(oid)){
            return mapper.map(ordersRepo.findById(oid),OrdersDTO.class);
        }else{
            throw new RuntimeException("Search Order Failed!");
        }*/
        return null;
    }

    @Override
    public List<OrderDetails> getAllOrder() {
      return null;
    }
}

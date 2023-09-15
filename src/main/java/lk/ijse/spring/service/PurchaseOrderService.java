package lk.ijse.spring.service;

import lk.ijse.spring.dto.OrdersDTO;
import lk.ijse.spring.entity.OrderDetails;

import java.util.List;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/

public interface PurchaseOrderService {
    void purchaseOrder(OrdersDTO dto);
    void deleteOrder(String oid);
    void updateOrder(OrdersDTO dto);
    OrdersDTO searchOrder(String oid);
    List<OrderDetails> getAllOrder();
}

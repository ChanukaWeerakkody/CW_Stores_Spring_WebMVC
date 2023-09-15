package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrdersDTO {
    private String oid;
/*
    @JsonFormat(pattern = "yyyy-MM-dd")
*/
    private String date;
    private CustomerDTO customer;
    private List<OrderDetailsDTO> orderDetails;

}

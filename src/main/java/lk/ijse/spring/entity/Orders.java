package lk.ijse.spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.OrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
@Entity
public class Orders {
    @Id
    private String oid;

    private String date;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customerId",referencedColumnName = "id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;



}

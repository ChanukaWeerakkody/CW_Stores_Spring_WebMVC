package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@IdClass(OrderItem_PK.class)
public class OrderDetails {
    @Id
    private String oid;

    @Id
    private String itemCode;
    private int qty;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "oid",referencedColumnName = "oid",insertable = false,updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "itemCode" , referencedColumnName = "code", insertable = false,updatable = false)
    private Item item;


}

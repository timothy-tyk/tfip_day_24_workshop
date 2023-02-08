package workshop24.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
  private Integer id;
  private Integer orderId;
  private String product;
  private Float unitPrice;
  private Float discount;
  private Integer quantity;

  public OrderDetails(Integer orderId){
    this.orderId = orderId;
  }
}

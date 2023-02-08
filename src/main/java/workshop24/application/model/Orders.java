package workshop24.application.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
  private Integer orderId;
  private Date orderDate;
  private String customerName;
  private String shipAddress;
  private String notes;
  private Float tax;
}

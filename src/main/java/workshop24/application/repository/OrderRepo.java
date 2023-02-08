package workshop24.application.repository;
import workshop24.application.model.Orders;
import java.util.List;
import workshop24.application.model.OrderDetails;




public interface OrderRepo {
  public Orders createOrder(Orders order);
  public List<OrderDetails> getOrderDetailsById(Integer id);
  public Integer addOrderDetails(List<OrderDetails> orderDetails);
}

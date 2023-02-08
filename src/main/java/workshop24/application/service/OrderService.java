package workshop24.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import workshop24.application.repository.OrderRepoImpl;
import workshop24.application.model.Orders;
import workshop24.application.exception.OrderException;
import workshop24.application.model.OrderDetails;
import java.util.List;

@Service
public class OrderService {
  @Autowired
  OrderRepoImpl orderRepoImpl;

  public Orders createOrder(Orders order){
    Orders orderFromDB = orderRepoImpl.createOrder(order);
    return orderFromDB;
  }

  public List<OrderDetails> getOrderDetailsById(Integer id){
    List<OrderDetails> orderDetailList = orderRepoImpl.getOrderDetailsById(id);
    if(orderDetailList == null || orderDetailList.isEmpty()){
      return null;
    }
    else{
      return orderDetailList;
    }
  }

  @Transactional(rollbackFor = OrderException.class)
  public void addOrderDetails(List<OrderDetails> orderDetails) throws OrderException{
    Integer add = orderRepoImpl.addOrderDetails(orderDetails); 
    if(orderDetails.size()>3){
      throw new OrderException();
    }
  }
}

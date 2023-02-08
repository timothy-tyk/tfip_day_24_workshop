package workshop24.application.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import workshop24.application.model.OrderDetails;
import workshop24.application.model.Orders;


@Repository
public class OrderRepoImpl implements OrderRepo{
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public Orders createOrder(Orders order) {
    System.out.println("createOrder");
    Integer added = jdbcTemplate.update(Queries.createNewOrder, new Date(System.currentTimeMillis()), order.getCustomerName(),order.getShipAddress(),order.getNotes(),order.getTax());
    if(added>0){
      Orders ord = jdbcTemplate.queryForObject(Queries.getOrder, BeanPropertyRowMapper.newInstance(Orders.class),order.getCustomerName(), order.getShipAddress());
      return ord;
    }
    return null;
  }

  @Override
  public List<OrderDetails> getOrderDetailsById(Integer id){
   List<OrderDetails> orderDetailList =  jdbcTemplate.query(Queries.getAllOrdersById, BeanPropertyRowMapper.newInstance(OrderDetails.class), id);
   return orderDetailList;
  }
  
  @Override
  public Integer addOrderDetails(List<OrderDetails> orderDetails){
    Integer count = 0;
    for(OrderDetails ord : orderDetails){
      jdbcTemplate.update(Queries.addOrderDetails, ord.getOrderId(),ord.getProduct(),ord.getUnitPrice(),ord.getDiscount(),ord.getQuantity());
      count++;
    }
    // Integer added = jdbcTemplate.update(Queries.addOrderDetails, orderDetails.getOrderId(),orderDetails.getProduct(),orderDetails.getUnitPrice(),orderDetails.getDiscount(),orderDetails.getQuantity());
    return count;
  }

}

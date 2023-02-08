package workshop24.application.repository;

public class Queries {
  public static final String createNewOrder = "INSERT INTO orders(order_date, customer_name, ship_address, notes, tax) VALUES(?,?,?,?,?)";
  public static final String getOrder = "SELECT * FROM orders WHERE customer_name=? AND ship_address=?";
  public static final String getOrderById = "SELECT * FROM orders WHERE id=?";    
  public static final String getAllOrdersById = "SELECT * FROM order_details WHERE order_id = ?";
  public static final String addOrderDetails = "INSERT INTO order_details(order_id, product, unit_price, discount, quantity) VALUES (?,?,?,?,?)";
}

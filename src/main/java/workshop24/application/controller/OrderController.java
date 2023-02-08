package workshop24.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import workshop24.application.model.Orders;
import workshop24.application.exception.OrderException;
import workshop24.application.model.OrderDetails;
import workshop24.application.service.OrderService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;


@Controller
@RequestMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
  @Autowired
  OrderService ordSvc;

  @GetMapping
  public String getLoginForm(Model model){
    // Create form to capture user (order) details
    model.addAttribute("order", new Orders());
    return "login";
  }
  @PostMapping("/login")
  public String showOrderForm(Model model, Orders order, HttpSession session){
    Orders ord = ordSvc.createOrder(order);
    session.setAttribute("order", ord);
    return "redirect:/order";
  }
  
  @GetMapping("/order")
  public String getOrderForm(Model model, HttpSession session){
    // Save User Data (Order) in Session
    Orders order = (Orders)session.getAttribute("order");

    // If List of Items (OrderDetails) is null, init new list and save in Session
    if(session.getAttribute("orderList")==null){
      List<OrderDetails> orderList = new ArrayList<OrderDetails>();
      session.setAttribute("orderList", orderList);
    }
    // If List of items not null, get list and add to model to be rendered
    List<OrderDetails> orderList = (List<OrderDetails>)session.getAttribute("orderList");
    model.addAttribute("user", order);
    model.addAttribute("orderForm", new OrderDetails(order.getOrderId()));
    model.addAttribute("orderDetails", orderList);
    return "orders";
  }

  @PostMapping("/order")
  public String postOrderDetails(Model model, OrderDetails details, HttpSession session) throws OrderException{
    List<OrderDetails> orderList = (List<OrderDetails>)session.getAttribute("orderList");
    System.out.println(details.toString());
    orderList.add(details);
    session.setAttribute("orderList", orderList);
    // ordSvc.addOrderDetails(details);
    return "redirect:/order";
  }

  @PostMapping("/confirm")
  public String getConfirmPage(Model model, HttpSession session)throws OrderException{
    Orders order = (Orders)session.getAttribute("order");

    List<OrderDetails> ordersList = (List<OrderDetails>)session.getAttribute("orderList");
    System.out.println(ordersList.toString());
    // get orderList from session and add to DB via service
    ordSvc.addOrderDetails(ordersList);

    model.addAttribute("user", order);
    model.addAttribute("orderDetails", ordersList);

    // After confirming, reset session attributes for next session
    session.setAttribute("order", null);
    session.setAttribute("orderList", null);
    session.invalidate();

    return "confirm";
  }
}

// Transactional, not more than 3 orders
// last page for order confirmation -> redirect back to login page
// refactor getOrderById to get order BY ID instead of name and add

// batch update so that transactional can work better, rollback everything instead of 4th item

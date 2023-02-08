package workshop24.application.exception;

public class OrderException extends Exception{
  public OrderException(){
    super();
  }
  public OrderException(String msg){
    super(msg);
  }
}

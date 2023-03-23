package bookshopparthenope.Model.CartManagement;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.BookFactory;
import bookshopparthenope.Model.UserManagement.DBService;
import java.sql.SQLException;

import static bookshopparthenope.LoginController.userCart;

/**
 * La classe CartItemVisitor è un'interfaccia che definisce i metodi che devono essere implementati dalle classi che intendono visitare gli elementi del carrello (implementati tramite l'interfaccia CartItem), in modo da poter eseguire operazioni specifiche su di essi.
 * In particolare, la classe definisce due metodi visit che prendono come parametro oggetti di tipo BookFactory e Book, rispettivamente, che sono le classi concrete che possono essere visitate dal visitor.
 */
public class CartTotal implements  CartItemVisitor{
  private Cart cart;
  private float total;

  public CartTotal (Cart cart){
    this.cart=cart;
    this.total= 0.0F;
  }

  @Override
  public void visit(BookFactory book) {
    total += book.getPrice();
  }

  @Override
  public void visit(Book book) {
    total += book.getPrice();
  }

  public float getTotal() throws SQLException, ClassNotFoundException {
    total = DBService.totalSum(userCart);
    return total;
  }
}

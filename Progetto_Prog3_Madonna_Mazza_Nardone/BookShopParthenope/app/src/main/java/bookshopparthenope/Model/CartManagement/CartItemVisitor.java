package bookshopparthenope.Model.CartManagement;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.BookFactory;

/**
 * La classe CartItemVisitor è un'interfaccia che definisce i metodi che devono essere implementati dalle classi che intendono visitare gli elementi del carrello (implementati tramite l'interfaccia CartItem), in modo da poter eseguire operazioni specifiche su di essi.
 * In particolare, la classe definisce due metodi visit che prendono come parametro oggetti di tipo BookFactory e Book, rispettivamente, che sono le classi concrete che possono essere visitate dal visitor.
 */
public interface CartItemVisitor {
   void visit(BookFactory book);
   void visit(Book book);

   //public void visit (DVD dvd);
}

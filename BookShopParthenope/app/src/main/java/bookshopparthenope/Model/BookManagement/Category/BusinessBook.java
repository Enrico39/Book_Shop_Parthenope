package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class BusinessBook extends Book {
  public BusinessBook(String subcategory) {
    super(subcategory); }

  public BusinessBook sBook(){
    return switch (subcategory) {
      case "Carriera" -> new Carriera();
      case "Economia" -> new Economia();
      case "Finanza" -> new Finanza();
      case "Industria" -> new Industria();
      case "Internazionale" -> new Internazionale();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Business"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}

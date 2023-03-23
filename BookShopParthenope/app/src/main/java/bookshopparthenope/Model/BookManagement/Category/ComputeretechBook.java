package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class ComputeretechBook extends Book {
  public ComputeretechBook(String subcategory) {
    super(subcategory); }

  public ComputeretechBook sBook(){
    return switch (subcategory) {
      case "Apple" -> new Apple();
      case "CAD" -> new CAD();
      case "Certificazioni" -> new Certificazioni();
      case "Informatica" -> new Informatica();
      case "Database" -> new Database();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Computer e Tech"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}

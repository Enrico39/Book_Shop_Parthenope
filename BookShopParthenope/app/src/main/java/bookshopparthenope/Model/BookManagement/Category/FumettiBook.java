package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class FumettiBook extends Book {
  public FumettiBook(String subcategory) {
    super(subcategory); }

  public FumettiBook sBook(){
    return switch (subcategory) {
      case "Comici" -> new Comici();
      case "Marvel" -> new Marvel();
      case "Misteriosi" -> new Misteriosi();
      case "DC" -> new DC();
      case "Fantasy" -> new Fantasy();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Fumetti"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}

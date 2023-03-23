package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class IntrattenimentoBook extends Book {
  public IntrattenimentoBook(String subcategory) {
    super(subcategory); }


  public IntrattenimentoBook sBook(){
    return switch (subcategory) {
      case "Rompicapo" -> new Rompicapo();
      case "Barzellette" -> new Barzellette();
      case "Giochi" -> new Giochi();
      case "Film" -> new Film();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Intrattenimento"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }


}

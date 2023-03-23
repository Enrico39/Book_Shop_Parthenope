package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class CucinaBook extends Book {
  public CucinaBook(String subcategory) {
    super(subcategory); }

  public CucinaBook sBook(){
    return switch (subcategory) {
      case "Asiatica" -> new Asiatica();
      case "Cucina Calda" -> new Cucinacalda();
      case "BBQ" -> new BBQ();
      case "Arti Culinarie" -> new Articulinarie();
      case "Dolci" -> new Dolci();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Cucina"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}

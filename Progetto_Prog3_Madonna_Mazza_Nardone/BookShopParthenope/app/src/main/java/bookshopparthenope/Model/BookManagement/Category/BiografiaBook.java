package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class BiografiaBook extends Book {
  public BiografiaBook(String subcategory) {
    super(subcategory); }

  public BiografiaBook sBook(){
    return switch (subcategory) {
      case "Etnica e Culturale" -> new Etnicaeculturale();
      case "Europea" -> new Europea();
      case "Storica" -> new Storica();
      case "Personaggi Famosi e Leader" -> new Personaggifamosieleader();
      case "Militare" -> new Militare();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Biografia"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}

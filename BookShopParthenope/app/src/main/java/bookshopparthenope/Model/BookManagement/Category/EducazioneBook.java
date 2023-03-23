package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class EducazioneBook extends Book {
  public EducazioneBook(String subcategory) {
    super(subcategory); }

  public EducazioneBook sBook(){
    return switch (subcategory) {
      case "Almanacchi" -> new Almanacchi();
      case "Atlanti e Mappe" -> new Atlantiemappe();
      case "Cataloghi" -> new Cataloghi();
      case "Scolastici" -> new Scolastici();
      default -> null;
    };
  }

  @Override
  public String getCategory() {
    return "Educazione"; }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {

  }

}

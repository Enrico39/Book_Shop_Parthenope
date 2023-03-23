package bookshopparthenope.Model.BookManagement.Category;

import bookshopparthenope.Model.BookManagement.Book;
import bookshopparthenope.Model.BookManagement.Subcategory.*;

import java.sql.SQLException;

public class LetteraturaBook extends Book {
  public  LetteraturaBook(String subcategory) {
    super(subcategory);


  }

  public LetteraturaBook sBook(){
      return switch (subcategory) {
          case "Antologie" -> new Antologie();
          case "Classici" -> new Classici();
          case "Contemporanei" -> new Contemporanei();
          case "Lingue Straniere" -> new Linguestraniere();
          case "Letterature" -> new Letterature();
          default -> null;

      };
  }

  @Override
  public String getCategory() {
    return "Letteratura"; }

    @Override
    public void insertFactoryBook() throws SQLException, ClassNotFoundException {

    }


}

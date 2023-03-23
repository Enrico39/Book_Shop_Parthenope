package bookshopparthenope.Model.BookManagement.Subcategory;
import bookshopparthenope.AggiuntaLibro;
import bookshopparthenope.Model.BookManagement.Category.LetteraturaBook;
import bookshopparthenope.Model.UserManagement.DBService;

import java.sql.SQLException;

public class Antologie extends LetteraturaBook {
  public Antologie() {

    super("Antologie");
  }
  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {
    DBService.insertBook(AggiuntaLibro.getTitolo(),AggiuntaLibro.getAutore(),AggiuntaLibro.getAnno(),AggiuntaLibro.getPagine(),AggiuntaLibro.getQuantita(),getCategory(),subcategory,AggiuntaLibro.getIsbn(),AggiuntaLibro.getPrice(), AggiuntaLibro.getDescription());

  }

}

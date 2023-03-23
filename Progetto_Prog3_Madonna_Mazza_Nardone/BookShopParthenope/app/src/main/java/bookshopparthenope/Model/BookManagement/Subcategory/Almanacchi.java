package bookshopparthenope.Model.BookManagement.Subcategory;

import bookshopparthenope.AggiuntaLibro;
import bookshopparthenope.Model.BookManagement.Category.EducazioneBook;
import bookshopparthenope.Model.UserManagement.DBService;

import java.sql.SQLException;


/**
 * Questa classe rappresenta la sottocategoria "Almanacchi" dei libri di Educazione. Estende la classe EducazioneBook e implementa il metodo insertFactoryBook, che consente di inserire un nuovo libro di questa categoria nel database attraverso l'utilizzo della classe DBService.
 * Il metodo fa riferimento ad alcune variabili statiche della classe AggiuntaLibro per recuperare le informazioni del libro da inserire, come il titolo, l'autore, il prezzo e la descrizione.
 *
 * ATTENZIONE: TALE COMMENTO E' VALIDO ANCHE PER LE ALTRE CLASSI NELLA DIRECTORY "Subcategory"
 */
public class Almanacchi extends EducazioneBook {
  public Almanacchi(){
    super("Almanacchi");
  }

  @Override
  public void insertFactoryBook() throws SQLException, ClassNotFoundException {
    DBService.insertBook(AggiuntaLibro.getTitolo(),AggiuntaLibro.getAutore(),AggiuntaLibro.getAnno(),AggiuntaLibro.getPagine(),AggiuntaLibro.getQuantita(),getCategory(),subcategory,AggiuntaLibro.getIsbn(),AggiuntaLibro.getPrice(), AggiuntaLibro.getDescription());

  }
}

package bookshopparthenope.Model.CartManagement;

import java.sql.SQLException;

/**
 * Questa classe Invoker rappresenta un oggetto che invoca l'esecuzione di un comando, tramite l'utilizzo del design pattern Command. L'invocatore ha un riferimento ad un oggetto CommandQuantity e richiama il suo metodo execute() per eseguire il comando.
 */
public class Invoker {
    private CommandQuantity command;

    public void setCommand(CommandQuantity command) {
        this.command = command;
    }

    public int executeCommand() throws SQLException, ClassNotFoundException {
        return command.execute();
    }
}

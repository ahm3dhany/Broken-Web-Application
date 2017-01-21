package broken.db;

import java.io.FileReader;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.h2.tools.RunScript;
/**
 *
 * @author ahm3dhany
 */
@Service
public class QuoteService {
    
    private final String databaseAddress = "jdbc:h2:file:./database";

    public QuoteService() throws Exception {
        init();
    }

    public void init() throws Exception {
        String databaseAddress = "jdbc:h2:file:./database";
        Connection connection = DriverManager.getConnection(databaseAddress, "sa", "");

        try {
            RunScript.execute(connection, new FileReader("sql/database-schema.sql"));
            RunScript.execute(connection, new FileReader("sql/database-import.sql"));
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
        connection.close();
    }

    public List<Quote> getAllQuotes() throws Exception {
        Connection connection = DriverManager.getConnection(databaseAddress, "sa", "");
        List<Quote> quotes = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT id, content FROM Quotes");
        while (resultSet.next()) {
            quotes.add(new Quote(resultSet.getInt("id"), resultSet.getString("content")));
        }
        resultSet.close();
        connection.close();
        return quotes;
    }

    public void addQuote(Quote quote) throws Exception {
        Connection connection = DriverManager.getConnection(databaseAddress, "sa", "");

        // Robert'); DROP TABLE Quotes;--
        // https://xkcd.com/327/
        /* Unsafe */
        String query = "INSERT INTO Quotes (id, content) VALUES ('" + quote.getId().toString() + "', '" + quote.getContent() + "')";
        Statement statement = connection.createStatement();
        statement.execute(query);
        statement.close();
        
        /* Safe
         String query = "INSERT INTO Quotes (id, content) VALUES (?, ?)";
         PreparedStatement pstmt = connection.prepareStatement(query);
         pstmt.setInt(1, quote.getId());
         pstmt.setString(2, quote.getContent());
         pstmt.execute();
         pstmt.close();
         */

        connection.close();
    }

    public void deleteQuote(int id) throws Exception {
        Connection connection = DriverManager.getConnection(databaseAddress, "sa", "");
        String query = "DELETE FROM Quotes WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, Integer.toString(id));
        pstmt.execute();
        pstmt.close();
        connection.close();
    }
    
}

package proyectosd.models;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/**
 *
 * @author Gilberto Leon <ripflame@gmail.com>
 */
public class AccountOperation {

    private User user;
    private OperationType operation;
    private double balance;
    private Date date;
    private final Charset ENCODING = StandardCharsets.UTF_8;

    /**
     * Append the contents of this object to the file.
     *
     * @param fileName
     * @throws IOException
     */
    public void saveToFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING,
                        StandardOpenOption.APPEND,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE)) {
            writer.write(this.toString());
            writer.newLine();
        }
    }

    /**
     * This method reads from a file and it does not belongs here
     *
     * @param fileName
     * @throws IOException
     * @deprecated
     */
    public void readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path, ENCODING.name());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormated = sdf.format(this.getDate());

        String accountOperationString = "" + this.getUser().getId() + ", "
                + this.getUser().getName() + ", " + this.getUser().getFirstLastName()
                + ", " + this.getUser().getSecondLastName() + ", " + operation.toString()
                + ", " + this.getBalance() + ", " + dateFormated;

        return accountOperationString;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

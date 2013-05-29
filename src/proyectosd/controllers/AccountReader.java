package proyectosd.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import proyectosd.models.AccountOperation;
import proyectosd.models.OperationType;
import proyectosd.models.User;

/**
 *
 * @author Gilberto Leon <ripflame@gmail.com>
 */
public class AccountReader {

    private final Charset ENCODING = StandardCharsets.UTF_8;

    /**
     * This method reads from a file and it maps the contents to an object
     *
     * @param fileName
     * @throws IOException
     */
    public ArrayList<AccountOperation> readFile(String fileName) throws IOException, ParseException {
        ArrayList<AccountOperation> ao = new ArrayList();
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path, ENCODING.name());
        while (scanner.hasNextLine()) {
            ao.add(parseAccountLine(scanner.nextLine()));
        }

        return ao;
    }

    private AccountOperation parseAccountLine(String line) throws ParseException{
        String data[] = line.split(",");

        User user = new User();
        user.setId(Integer.parseInt(data[0]));
        user.setName(data[1]);
        user.setFirstLastName(data[2]);
        user.setSecondLastName(data[3]);

        AccountOperation ao = new AccountOperation();
        ao.setUser(user);

        switch (data[4]) {
            case "DEPOSIT":
                ao.setOperation(OperationType.DEPOSIT);
                break;

            case "WITHDRAW":
                ao.setOperation(OperationType.WITHDRAW);
                break;

            case "CONSULT":
                ao.setOperation(OperationType.CONSULT);
                break;
        }

        ao.setBalance(Double.parseDouble(data[5]));

        String dateString = data[6];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse(dateString);
        ao.setDate(date);

        return ao;
    }
}

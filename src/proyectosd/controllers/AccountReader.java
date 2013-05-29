package proyectosd.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectosd.models.AccountOperation;
import proyectosd.models.Operations;
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
    public ArrayList<AccountOperation> readFile(String fileName) throws IOException, Exception {
        ArrayList<AccountOperation> ao = new ArrayList();
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path, ENCODING.name());
        while (scanner.hasNextLine()) {
            ao.add(parseAccountLine(scanner.nextLine()));
        }

        return ao;
    }

    private AccountOperation parseAccountLine(String line) throws Exception{
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
                ao.setOperation(Operations.DEPOSIT);
                break;

            case "WITHDRAW":
                ao.setOperation(Operations.WITHDRAW);
                break;

            case "CONSULT":
                ao.setOperation(Operations.CONSULT);
                break;
        }

        ao.setBalance(Double.parseDouble(data[5]));

        String dateString = data[6];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        date = sdf.parse(dateString);
        ao.setDate(date);

        return ao;
    }
}

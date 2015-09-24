package GeneralHelpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by CMG_TEST on 11.09.2015.
 */
public class Read_From_File_Test {



    public static Map<String, String> buildDataTest(Map<String, String> questions, String filePath) throws IOException {

            Scanner scanner = null;
            try {
                scanner = new Scanner(new FileInputStream(new File(filePath)));
                while (scanner.hasNext()) {
                    String question = scanner.nextLine();
                    String answer = scanner.nextLine();
                    questions.put(question, answer);
                }
            } catch (FileNotFoundException ex) {

            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }


        Iterator<Map.Entry<String, String>> itr = questions.entrySet().iterator();

        while (itr.hasNext()) {

            Map.Entry<String, String> value = itr.next();

            String question = value.getKey();
            System.out.println("Question: " + value.getKey() + " - Answer: " + value.getValue());
            System.out.println("Get answer by key: " + questions.get(question));

        }

        return questions;
    }
}

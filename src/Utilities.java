import java.io.*;
import java.util.ArrayList;

public class Utilities {

    private static String writerName = "Output.txt";
    private static PrintWriter writer = null;

    public static void writeSetName(String name) {
        writerName = name;
    }

    public static void writeToFile(String line) {
        if (writer == null)
            try {
                writer = new PrintWriter(writerName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        writer.println(line);
        writer.flush();
    }

    public static void writeClose() {
        if (writer != null)
            writer.close();
    }

    private static String readerName = "TestCase.txt";
    private static BufferedReader reader = null;

    public static void readSetName(String name) {
        readerName = name;
    }

    public static ArrayList<String> readFromFile() {
        if (reader == null)
            try {
                reader = new BufferedReader(new FileReader(readerName));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return null;
            }
        ArrayList<String> list = new ArrayList<String>();

        String line = "";
        try {
            line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return list;
    }

    public static void readClose() {
        if (reader != null)
            try {
                reader.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
    }

}

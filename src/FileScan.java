import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        String read = "";
        ArrayList<String> lines = new ArrayList<>();

        try {

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            Path file = null;
            if (args.length == 1) {

                file = Paths.get(workingDirectory.getPath() + "\\src\\" + args[0]);
                System.out.println("CMD Read!");
            } else if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                file = selectedFile.toPath();
            }else  // user closed the file dialog wihtout choosing
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }

            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file, CREATE));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));
            int wordCount = 0;
            int letterCount = 0;
            int line = 0;
            String[] words = null;
            while (reader.ready()) {
                rec = reader.readLine();
                lines.add(rec);
                line++;
                // echo to screen
                System.out.printf("\nLine %4d %-60s ", line, rec);
                words = rec.split(" ");
                wordCount += words.length;
                letterCount += rec.length();

            }
            System.out.println("\n" + file.getFileName());
            System.out.println("Wordcount: " + wordCount);
            System.out.println("Characters: " + letterCount);
            System.out.println("lines: " + line);

            reader.close(); // must close the file to seal it and flush buffer
            System.out.println("\n\nData file read!");

        }  // end of TRY
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
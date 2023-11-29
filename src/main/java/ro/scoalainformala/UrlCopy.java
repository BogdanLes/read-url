package ro.scoalainformala;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class UrlCopy {

    public static void main(String[] args) {

        final String URL_PATH = "https://gist.githubusercontent.com/phillipj/4944029/raw/75ba2243dd5ec2875f629bf5d79f6c1e4b5a8b46/alice_in_wonderland.txt";
        String FILE_PATH = "alice.txt";

        System.out.println("Enter the file path(ex. D:/Java/filename.txt): ");
        String testFileIn;
        Path testFile;
        boolean v = false;

        //verify path exists
        do {
            Scanner sc = new Scanner(System.in);
            testFileIn = sc.nextLine();
            testFile = Paths.get(testFileIn);
            testFile = testFile.getParent();
            if (Files.exists(testFile)) {
                v = true;
            } else {
                System.err.println("No such directory. Enter new value.");
            }
        } while (v == false);
        FILE_PATH = testFileIn;


        try {
            URL resourceUrl = new URL(URL_PATH);

            try (BufferedWriter out = new BufferedWriter(new FileWriter(FILE_PATH))) {
                BufferedReader in = new BufferedReader(new InputStreamReader(resourceUrl.openStream()));

                String line;
                int nrWords;
                String newLine;
                while ((line = in.readLine()) != null) {
                    nrWords = line.trim().split("\\s+").length;
                    if (line.trim().isEmpty()) {
                        nrWords = 0;
                    }
                    //System.out.println(nrWords + " " + line);
                    newLine = nrWords + " " + line;
                    out.write(newLine);
                    out.newLine();
                }
            } catch (IOException e) {
                System.err.println("An error has occurred!");
            }

        } catch (MalformedURLException e) {
            System.err.println("An error has occurred!");
        }

    }

}

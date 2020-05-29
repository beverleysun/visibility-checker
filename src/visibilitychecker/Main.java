package visibilitychecker;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        // Input from user
        System.out.println("Input directory path of .java files:");
        Scanner scan = new Scanner(System.in);
        String folderName = scan.nextLine();

        // Get file names in the folder specified by user
        Directory folder = new Directory(folderName);
        List<String> fileNames = folder.getFileNames();

        // Check .java files
        Checker checker = new Checker(fileNames,folderName);
    }
}

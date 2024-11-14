package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class OldFileMain {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/example.txt");
        File directory = new File("temp/exampleDir");

        System.out.println("File exists: " + file.exists());
        System.out.println("File created: " + file.createNewFile());
        System.out.println("Directory created: " + directory.mkdir());
//        System.out.println("File deleted: " + file.delete());
        System.out.println("Is file: " + file.isFile());
        System.out.println("Is directory: " + file.isDirectory());
        System.out.println("File name: " + file.getName());
        System.out.println("File size: " + file.length() + "bytes");
        File newFile = new File("temp/newExample.txt");
        System.out.println("File renamed: " + file.renameTo(newFile));
        System.out.println("Last modified: " + new Date(newFile.lastModified()) + "ms");
    }

}

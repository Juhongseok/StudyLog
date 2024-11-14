package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class NewFilesMain {

    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");

        System.out.println("File exists: " + Files.exists(file));
        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            System.out.println(file + " File already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException e) {
            System.out.println(directory + " Directory already exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Files.delete(file);
//        System.out.println("File deleted");
        System.out.println("Is regular file: " + Files.isRegularFile(file));
        System.out.println("Is directory: " + Files.isDirectory(directory));
        System.out.println("File name: " + file.getFileName());
        System.out.println("File size: " + Files.size(file) + "bytes");

        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, REPLACE_EXISTING);
        System.out.println("File moved/renamed");
        System.out.println("Last modified: " + Files.getLastModifiedTime(newFile));

        BasicFileAttributes attributes = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("==== Attributes ====");
        System.out.println("Creation time: " + attributes.creationTime());
        System.out.println("Is directory: " + attributes.isDirectory());
        System.out.println("Is regular file: " + attributes.isRegularFile());
        System.out.println("Is symbolic link: " + attributes.isSymbolicLink());
        System.out.println("Size: " + attributes.size());
    }

}

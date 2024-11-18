package io.text;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {
        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        FileWriter writer = new FileWriter(FILE_NAME, UTF_8);
        writer.write(writeString);
        writer.close();

        StringBuilder content = new StringBuilder();
        FileReader reader = new FileReader(FILE_NAME, UTF_8);
        int ch;
        while ((ch = reader.read()) != -1) {
            content.append((char) ch);
        }
        reader.close();

        System.out.println("content = " + content);
    }

}

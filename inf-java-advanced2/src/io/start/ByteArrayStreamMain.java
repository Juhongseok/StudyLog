package io.start;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayStreamMain {

    public static void main(String[] args) throws IOException {
        byte[] input = {1, 2, 3};

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(input);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        byte[] bytes = bis.readAllBytes();
        System.out.println("bytes = " + Arrays.toString(bytes));
    }

}

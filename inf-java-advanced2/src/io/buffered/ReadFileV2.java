package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV2 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[BUFFER_SIZE];
        int fileSize = 0;
        int size;

        // read, write 호출 시 OS 시스템 콜을 통해 명령어 전달 --> 오버헤드 유발 --> 성능 저하 --> 시스템 콜 호출 횟수 줄이기
        while((size = fis.read(buffer)) != -1) {
            fileSize += size;
        }
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + FILE_NAME);
        System.out.println("File size: " + fileSize / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

}

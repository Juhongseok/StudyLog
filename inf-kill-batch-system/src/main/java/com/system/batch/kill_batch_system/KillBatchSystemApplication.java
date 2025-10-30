package com.system.batch.kill_batch_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KillBatchSystemApplication {

    public static void main(String[] args) {
        // 배치 작업의 성공/실패 상태를 exit code 사용하여 외부 시스템에 전달
        // 실무에서 모니터링 및 제어에 필수
        System.exit(SpringApplication.exit(SpringApplication.run(KillBatchSystemApplication.class, args)));
    }

}

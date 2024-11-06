package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new RunnableTask("taskA"));
        executorService.execute(new RunnableTask("taskB"));
        executorService.execute(new RunnableTask("taskC"));
        executorService.execute(new RunnableTask("longTask", 100_000));

        log(executorService);
        log("== shutdown 시작");
        shutdownAndAwaitTermination(executorService);
        log("== shutdown 완료");
    }

    private static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                executorService.shutdownNow();

                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

}

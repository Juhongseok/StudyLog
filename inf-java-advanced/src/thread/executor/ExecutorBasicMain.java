package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.*;
import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ExecutorBasicMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0,
                TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>());
        log("== 초기 상태 ==");
        printState(executorService);
        executorService.execute(new RunnableTask("taskA"));
        executorService.execute(new RunnableTask("taskB"));
        executorService.execute(new RunnableTask("taskC"));
        executorService.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");
        printState(executorService);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(executorService);

        log("== shutdown 완료 ==");
        /*executorService.shutdown();
        if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
        printState(executorService);*/
        executorService.close();
        printState(executorService);
    }

}

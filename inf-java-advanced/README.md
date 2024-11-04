# Executor Framework
`멀티스레딩 및 병렬 처리를 쉽게 사용할 수 있도록 돕는 기능 모음`<br>
작업 실행의 관리 및 스레드 풀 관리를 효율적으로 처리해서 개발자가 직접 스레드를 생성하고 관리하는 복잡함 줄여줌

**직접 스레드를 생성해서 사용하는 것보다 Executor 사용해야겠다라는 생각하기!!**<br>
Spring Framework 내부에서 자주 사용

```java
package java.util.concurrent;

public interface Executor {
    void execute(Runnable command);
}
// -------------- //

// Executor 보다는 ExecutorService 주로 사용
public interface ExecutorService extends Executor, AutoCloseable {

    <T> Future<T> submit(Callable<T> task);
    
    @Override
    default void close() {
        // ...
    }
}
```

## Future
**Runnable** : 반환 값이 없어서 불편함, checkedException 던질 수 없음 (메서드 내부에서 처리해야 함)<br>
**Callable** : java.util.concurrent 제공, Runnable 비슷한 작업을 하지만 반환 값이 있음, Exception 던질 수 있음

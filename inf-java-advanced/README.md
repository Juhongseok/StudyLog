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

**Future**: 미래의 결과를 받을 수 있는 객체<br>
`Future<Integer> future = executorService.submit(new MyCallable());`

여기서 Callable 언제 실행 될지 모름 --> 결과 반환 여부 알 수 없음 (호출 스레드가 실행X, 스레드 풀의 다른 스레드가 실행)<br>
해서 결과값 대신 Future 객체 반환

Future의 구현체인 FutureTask 내부에 Callable을 가지고 있음, Thread가 FutureTask.run 실행 시 내부에서 Callable 실행

### Future.get을 실행했을 때
1. 결과가 처리가 됐을 경우
    - 결과 반환해줌
2. 결과가 아직 처리가 되지 않았을 경우
   - 내부에서 `awaitDone()` 메소드 호출
   - 완료가 될때까지 대기상태 돌입 (무한루프)
   - 완료 되면 결과 반환  

### Future를 반환하는 이유
Future 반환 없이 결과값을 직접 반환한다면 `WATING` 상태이기 때문에 각 작업마다 블로킹 상태로 기다려야 함

Future 반환한다면 나중에 `get()`을 호출 할 때 완료가 되지 않았을 경우만 블로킹 즉 `get()`을 호출하지 않는다면(작업이 완료가 된경우 제외)<br>
여러 작업을 `ExecutorService`에 요청할 수 있음

**가능하면 실행해야할 작업을 모두 요청이 끝난 후에 결과 값을 받아오자!!**

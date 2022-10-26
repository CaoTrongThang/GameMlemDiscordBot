package src.ctt.GameMlemBot.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadManager {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void createExecutorThread(Thread thread) {
        executorService.execute(thread);
    }
}

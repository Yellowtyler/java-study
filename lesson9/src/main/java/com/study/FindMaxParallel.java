package com.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class FindMaxParallel extends RecursiveTask<Integer> {
    private int[] data;
    public FindMaxParallel(int[] data) {
        this.data = data;
    }

    protected Integer compute() {
        if(this.data.length > 50000000) {
            List<FindMaxParallel> subtasks = createSubtasks();
            for(FindMaxParallel subtask : subtasks) {
                subtask.fork();
            }
            int result = Integer.MIN_VALUE;
            for (FindMaxParallel subtask : subtasks) {
                if(result < subtask.join()) {
                    result = subtask.join();
                }
            }
            return result;
        } else {
            System.out.println(Thread.currentThread().getName());
            return IntStream.of(data).max().getAsInt();
        }
    }

    private List<FindMaxParallel> createSubtasks() {
        return new ArrayList<>(Arrays.asList(
                new FindMaxParallel(Arrays.copyOfRange(data, 0, data.length / 2)),
                new FindMaxParallel(Arrays.copyOfRange(data, data.length / 2, data.length))
        ));
    }

}

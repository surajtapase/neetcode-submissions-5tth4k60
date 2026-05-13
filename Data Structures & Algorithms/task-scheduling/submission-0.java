class Solution {
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) return tasks.length;

        int[] freq = new int[26];
        for (char t : tasks) freq[t - 'A']++;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq){
            if (f > 0) maxHeap.add(f);
        }

        Queue<int[]> waitQueue = new LinkedList<>();

        int time = 0;
        int completedTasks = 0;

        while (completedTasks < tasks.length){
            time++;

            if (!waitQueue.isEmpty() && waitQueue.peek()[1] == time){
                maxHeap.add(waitQueue.poll()[0]);
            }

            if(!maxHeap.isEmpty()){
                int currentFreq = maxHeap.poll();

                completedTasks++;

                if (currentFreq - 1 > 0){
                    waitQueue.add(new int[]{currentFreq - 1, time + n + 1});
                }
            }
        }
        return time;
    }
}

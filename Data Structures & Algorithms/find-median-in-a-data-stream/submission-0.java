class MedianFinder {

    private PriorityQueue<Integer> small;

    private PriorityQueue<Integer> large;

    public MedianFinder() {
        small = new PriorityQueue<>(Collections.reverseOrder());

        large = new PriorityQueue<>();
        
    }
    
    public void addNum(int num) {
        small.add(num);

        large.add(small.poll());

        if(small.size() < large.size()){
            small.add(large.poll());
        }
    }
    
    public double findMedian() {
        if (small.size() > large.size()){
            return (double) small.peek();
        } else {
            return (small.peek() + large.peek()) / 2.0;
        }
    }
}

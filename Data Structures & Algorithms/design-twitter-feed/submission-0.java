class Twitter {

    private static int timestamp = 0;

    private class Tweet {
        int id;
        int time;
        Tweet (int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    private Map<Integer, List<Tweet> > userTweets;

    private Map<Integer, Set<Integer> > following;

    public Twitter() {
        userTweets = new HashMap<>();
        following = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        userTweets.putIfAbsent(userId, new ArrayList<>());
        userTweets.get(userId).add(new Tweet(tweetId, timestamp++));
    }
    
    public List<Integer> getNewsFeed(int userId) {
        // Min-Heap banayenge jo 10 sabse "Recent" (bade timestamp) tweets rakhega
        // Min-Heap isliye taaki hum sabse purane ko poll() kar sakein
        PriorityQueue<Tweet> minHeap = new PriorityQueue<>((a, b) -> a.time - b.time);

        // 1. User ke apne tweets feed mein hone chahiye
        List<Tweet> myTweets = userTweets.getOrDefault(userId, new ArrayList<>());
        for (Tweet t : myTweets){
            minHeap.add(t);
            if (minHeap.size() > 10) minHeap.poll();
        }

         // 2. Jinhe user follow karta hai, unke tweets bhi add karo
        Set<Integer> friends = following.getOrDefault(userId, new HashSet<>());
        for (int friendId : friends) {
            // Note: Ek user khud ko bhi follow kar sakta hai, handle carefully
            if (friendId == userId) continue; 
            
            List<Tweet> friendTweets = userTweets.getOrDefault(friendId, new ArrayList<>());
            for (Tweet t : friendTweets) {
                minHeap.add(t);
                if (minHeap.size() > 10) minHeap.poll();
            }
        }

        // 3. Heap se result nikal kar "Recent to Oldest" order mein convert karo
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            // Heap se purana pehle niklega, toh hum index 0 par add karenge reverse karne ke liye
            result.add(0, minHeap.poll().id);
        }
        return result;
    }
    
   public void follow(int followerId, int followeeId) {
        following.putIfAbsent(followerId, new HashSet<>());
        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        // Khud ko unfollow karne ka koi logic nahi banta, par safety check
        if (following.containsKey(followerId) && followerId != followeeId) {
            following.get(followerId).remove(followeeId);
        }
    }
}

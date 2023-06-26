import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Cache cache = new Cache(3000);

        cache.put("A");
        cache.put("A");

        Thread.sleep(1000);

        cache.put("B");
        cache.put("B");
        Thread.sleep(2000);
        cache.put("A");
        cache.put("A");

        Thread.sleep(1000);
        cache.put("B");
        cache.put("B");
        cache.put("A");

        Thread.sleep(1000);
        cache.put("A");
        Thread.sleep(1000);
        cache.put("B");
        cache.put("B");

        Thread.sleep(1000);
        cache.put("A");
        cache.put("B");

        Map<String, Integer> mp = cache.get();

        for (String each : mp.keySet()) {
            System.out.println(each + " " + mp.get(each));
        }
    }
}

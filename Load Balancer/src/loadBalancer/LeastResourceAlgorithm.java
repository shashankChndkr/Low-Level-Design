package loadBalancer;

import java.util.Comparator;
import java.util.PriorityQueue;



public class LeastResourceAlgorithm implements LoadBalancer{

    class Pair<K,V>{

        private  K key ;
        private V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }



    private PriorityQueue<Pair<Integer, String>> pq ;

    public LeastResourceAlgorithm(){
        this.pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
    }

    @Override
    public void addServer(String server) {
        pq.add(new Pair<>(0 , server));

    }

    @Override
    public String getServer(Integer weight) {
        if(pq.isEmpty()){
            return null;
        }

        Pair p = pq.poll();
        p.setKey(Integer.valueOf(p.getKey().toString()) + weight);

        pq.add(p);

        return p.getValue().toString();

    }
}

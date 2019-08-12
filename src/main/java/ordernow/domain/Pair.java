package ordernow.domain;

public class Pair<K,V> {
    public K key;
    public V value;

    public Pair(K k, V v){
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public String toString(){
        String ret = key+":"+value;
        return ret;
    }
}

package edu.kit.iti.structuredtext;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by weigl on 13.06.14.
 */
public class ChainMap<K,V> implements Map<K, V> {
    List<Map<K, V>> fallbacks = new ArrayList<>();

    public Map<K, V> combine() {
        HashMap<K, V> m = new HashMap<>();

        for (Map<K, V> map : fallbacks) {
            for (Map.Entry<K, V> e : map.entrySet()) {
                m.put(e.getKey(), e.getValue());
            }
        }
        return m;
    }

    @Override
    public int size() {
        return combine().size();
    }

    @Override
    public boolean isEmpty() {
        for (Map<K, V> map : fallbacks) {
            if (!map.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        for (Map<K, V> map : fallbacks) {
            if (map.containsKey(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map<K, V> map : fallbacks) {
            if (map.containsValue(value))
                return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for (Map<K, V> map : fallbacks) {
            if (map.containsKey(key))
                return map.get(key);
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        return fallbacks.get(0).put(key, value);
    }

    @Override
    public V remove(Object key) {
        return fallbacks.get(0).remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        fallbacks.get(0).putAll(m);
    }

    @Override
    public void clear() {
        fallbacks.get(0).clear();
    }

    @Override
    public Set<K> keySet() {
        return combine().keySet();
    }

    @Override
    public Collection<V> values() {
        return combine().values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return combine().entrySet();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        V a = get(key);
        if (a != null) return a;
        return defaultValue;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (Map<K, V> map : fallbacks) {
            map.forEach(action);
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        for (Map<K, V> map : fallbacks) {
            map.replaceAll(function);
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        for (Map<K, V> map : fallbacks) {
            return map.putIfAbsent(key, value);
        }
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        boolean t = false;
        for (Map<K, V> map : fallbacks) {
           t = t || map.replace(key, oldValue, newValue);
        }
        return t;
    }

    @Override
    public V replace(K key, V value) {
        for (Map<K, V> map : fallbacks) {
            return map.replace(key,value);
        }
        return null;
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    public void push() {
        push(new HashMap<K,V>());
    }

    public void push(Map<K, V> map) {
        fallbacks.add(0,map);
    }

    public Map<K,V> pop(){
        return fallbacks.remove(0);
    }
}

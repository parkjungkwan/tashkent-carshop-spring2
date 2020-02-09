package texas.sbv.car.proxy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class Table<K1, K2, V> {


    private Map<K1, Map<K2, V>> rowMap;
    private Map<K2, Map<K1, V>> colMap;

    public Table() {
        rowMap = new HashMap<>();
        colMap = new HashMap<>();
    }

    public void put(K1 rowKey, K2 colKey, V value) {
        Map<K2, V> row;
        Map<K1, V> col;

        if (rowMap.containsKey(rowKey)) {
            row = rowMap.get(rowKey);
        } else {
            row = new HashMap<K2, V>();
            rowMap.put(rowKey, row);
        }

        if (colMap.containsKey(colKey)) {
            col = colMap.get(colKey);
        } else {
            col = new HashMap<K1, V>();
            colMap.put(colKey, col);
        }

        row.put(colKey, value);
        col.put(rowKey, value);
    }

    public V get(K1 rowKey, K2 colKey) {
        Map<K2, V> row;
        row = rowMap.get(rowKey);
        if (row != null) {
            return row.get(colKey);
        }
        return null;
    }

    public Map<K2, V> getRow(K1 rowKey) {
        if (rowMap.containsKey(rowKey)) {
            return new HashMap<>(rowMap.get(rowKey));
        }
        return null;
    }

    public Map<K1, V> getCol(K2 colKey) {
        if (colMap.containsKey(colKey)) {
            return new HashMap<>(colMap.get(colKey));
        }
        return null;
    }

    public Set<K1> getRowKeys() {
        return new HashSet<>(rowMap.keySet());
    }

    public Set<K2> getColKeys() {
        return new HashSet(colMap.keySet());
    }

    public void clear(){
        rowMap.clear();
        colMap.clear();
    }
}
package texas.sbv.car.proxy;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
@Component
public class Box<T> {
	private ArrayList<T> list;
	public Box(){list = new ArrayList<T>();}
	public void add(T item) {list.add(item);}
	public T get(int i) {return list.get(i);}
	public ArrayList<T> get() {return list;}
	public int size() {return list.size();}
	public String toString() {return list.toString();}
	public void clear() {list.clear();}
	public ArrayList<T> getList() {return list;}
}
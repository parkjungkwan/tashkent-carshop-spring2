package texas.sbv.car.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;
@Component
public class Trunk<T> {
	private HashMap<String, T> map;

	private Trunk(){
		map = new HashMap<>();
	}
	public void put(List<String> x, List<T> y) {
		for (int i = 0; i < x.size(); i++) {
			map.put(x.get(i), y.get(i));
		}
	}
	
	public T get(String k) {
		Function<String, T> f = p -> map.get(p);
		return f.apply(k);
	}
	public HashMap<String, T> get() {return map;}
	public int size() {return map.size();}
	public void clear(){
		map.clear();
	}
}

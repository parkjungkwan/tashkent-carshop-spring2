package texas.sbv.car.proxy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class Proxy {
	public int integer(String param) {
		Function<String, Integer> f = Integer :: parseInt;
		return f.apply(param);
	}
	public String string(Object param) {
		Function<Object, String> f = String :: valueOf;
		return f.apply(param);
	}
	public Long longify(String param) {
		Function<String, Long> f = Long :: parseLong;
		return f.apply(param);
	}
	public boolean equals(String p1, String p2) {
		BiPredicate<String, String> b = String :: equals;
		return b.test(p1, p2);
	}
	public int random(int a, int b) {
		BiFunction<Integer, Integer, Integer> f =(t,u)->(int)(Math.random()*(u-t))+t;
		return f.apply(a, b);
	}

	public String arrayToString(String array){
		return array.replace("[", "").replace("]", "");
	}

	public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	
}


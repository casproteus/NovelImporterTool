package homework.testdome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Rare {
    public static int nthMostRare(int[] elements, int n) {
    	Map<Integer, Integer> counted = new HashMap<>();
    	
        for (int i : elements) {
            if (counted.containsKey(i)) {
                counted.put(i, counted.get(i) + 1);
            } else {
                counted.put(i, 1);
            }
        }
        
        Set<Entry<Integer, Integer>> keySet = counted.entrySet();
		List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<>(keySet);
		Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				return ( o1.getValue() - o2.getValue());
			}
		});
		
		for (Entry<Integer, Integer> entry : infoIds) {
			entry.getKey();
		}
		return infoIds.get(n-1).getValue();
	
//        Map<Integer, Integer> sortedMap = counted.entrySet().stream().sorted(Entry.comparingByValue())
//                           .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (k, v) -> k, LinkedHashMap::new));
//        
//        List<Integer> occurrences = sortedMap.entrySet().stream().map(Entry::getValue).distinct().collect(Collectors.toList());
//        
//        Optional<Integer> answer = sortedMap.entrySet().stream()
//                         .map(Entry::getValue)
//                         .filter(v -> v.intValue() == occurrences.get(n-1)).findFirst();
//        
//        return answer.get();
    }

    public static void main(String[] args) {
        int x = nthMostRare(new int[] { 5, 4, 3, 2, 1, 5, 4, 3, 2, 5, 4, 3, 5, 4, 5 }, 2);
        System.out.println(x);
    }
}

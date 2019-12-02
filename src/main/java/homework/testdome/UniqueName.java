package homework.testdome;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class UniqueName {
    public static String firstUniqueName(String[] names) {
    	Integer ZERO = 0; // to avoid repeated autoboxing below
        final LinkedHashMap<String, Integer> map = new LinkedHashMap<>(names.length);

        // build the map
        for (String s : names) {
            Integer count = map.getOrDefault(s, ZERO);
            map.put(s, count + 1);
        }

        // find the first unique entry. Note that set order is deterministic here.
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        // if we get this far, there was no unique string in the list
        return null;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqueName(new String[] { "Abbi", "Adeline", "Abbi", "Adalia" }));
    }
}

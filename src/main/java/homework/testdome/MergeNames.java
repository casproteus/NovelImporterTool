package homework.testdome;
import java.util.HashSet;

public class MergeNames {
	public String[] uniqueNames(String[] names1, String[] names2) {
		HashSet<String> set = new HashSet<String>();
		for (String str : names1) {
			set.add(str);
		}

		for (String str : names2) {
			set.add(str);
		}

		return set.toArray(new String[0]);
	}
	
	public static void main(String[] args) {
		String[] names1 = new String[] { "Ava", "Emma", "Olivia" };
		String[] names2 = new String[] { "Olivia", "Sophia", "Emma" };
		System.out.println(String.join(", ", new MergeNames().uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
	}
}

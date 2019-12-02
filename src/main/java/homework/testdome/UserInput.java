package homework.testdome;
import java.util.ArrayList;
import java.util.List;

public class UserInput {
    
    public static class TextInput {
    	
    	List<Character> list = new ArrayList<Character>();
    	
    	public void add(char c) {
    		list.add(c);
    	}
    	
    	public String getValue() {
    		StringBuilder sb = new StringBuilder();
    		for (Character character : list) {
    			sb.append(character);
			}
    		return sb.toString(); 
    	}
    }

    public static class NumericInput extends TextInput{
    	public void add(char c) {
    		if('0' <= c &&  c <= '9') {
    			super.add(c);
    		}
    	}
    }

    public static void main(String[] args) {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}
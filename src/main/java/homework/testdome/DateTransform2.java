package homework.testdome;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateTransform2 {

    public static List<String> changeDateFormat(List<String> dates) {
    	DateFormat format0 = new SimpleDateFormat("yyyyMMdd");
    	DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
    	DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
    	DateFormat format3 = new SimpleDateFormat("MM-dd-yyyy");
    	List<String> fr = new ArrayList<String>();
    	for (String dateStr : dates) {
    		if(dateStr.indexOf('/') == 4 && dateStr.lastIndexOf('/') == 7 && dateStr.length() == 10) {
    			String s = dateStr.substring(5, 7);
    			int i = 0;
    			try {
    				i = Integer.valueOf(s);
    			}catch(Exception e) {
    			}
    			if (i <= 0 || i > 12) {
    				continue;
    			}
    			try {
        			fr.add(format0.format(format1.parse(dateStr)));
        		}catch(Exception e) {
        		}
    		}else if(dateStr.indexOf('/') == 2 && dateStr.lastIndexOf('/') == 5 && dateStr.length() == 10) {
    			String s = dateStr.substring(3, 5);
    			int i = 0;
    			try {
    				i = Integer.valueOf(s);
    			}catch(Exception e) {
    			}
    			if (i <= 0 || i > 12) {
    				continue;
    			}
    			try {
        			fr.add(format0.format(format2.parse(dateStr)));
        		}catch(Exception e) {
        		}
    		}else if(dateStr.indexOf('-') == 2 && dateStr.lastIndexOf('-') == 5 && dateStr.length() == 10) {
    			String s = dateStr.substring(0, 2);
    			int i = 0;
    			try {
    				i = Integer.valueOf(s);
    			}catch(Exception e) {
    			}
    			if (i <= 0 || i > 12) {
    				continue;
    			}
    			try {
        			fr.add(format0.format(format3.parse(dateStr)));
        		}catch(Exception e) {
        		}
    		}
		}
    	return fr;
    }
    
	public static void main(String[] args) {        
        List<String> dates = changeDateFormat(Arrays.asList("2010/03/30", "15/12/2016", "11-15-2012", "20130720"));
        for(String date : dates) {
            System.out.println(date);
        }
	}

}

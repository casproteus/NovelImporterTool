package homework.testdome;

import java.util.*;
import java.text.SimpleDateFormat;

public class MovieNight {
    public static Boolean canViewAll(Collection<Movie> movies) {
    	
    	List<Date> stars = new ArrayList<Date>();
    	List<Date> ends = new ArrayList<Date>();
        for (Movie movie : movies) {
			Date ds = movie.getStart();
			Date de = movie.getEnd();
			for(int i = 0; i < stars.size(); i++) {
				if(!(ds.after(ends.get(i)) || de.before(stars.get(i)))) {
					return false;
				}
			}
			
			stars.add(ds);
			ends.add(de);
			
		}
        return true;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

        System.out.println(MovieNight.canViewAll(movies));
    }
}

class Movie {
    private Date start, end;
    
    public Movie(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
    
    public Date getStart() {
        return this.start;
    }
    
    public Date getEnd() {
        return this.end;
    } 
}

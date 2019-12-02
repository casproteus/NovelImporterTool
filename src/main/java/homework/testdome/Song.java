package homework.testdome;
import java.util.HashSet;
import java.util.Set;

public class Song {
    private String name;
    private Song nextSong;
    static Set<Song> set = new HashSet<Song>();
    
    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
    	
//    	if(nextSong == null) {
//    		return false;
//    	}else {
//    		set.add(this);
//    		if(set.contains(nextSong)) {
//    			return true;
//    		}else {
//    			return nextSong.isRepeatingPlaylist();
//    		}
//    	}
    	
		Song slow = this.nextSong;
		Song fast = slow == null ? null : slow.nextSong;
		
		while (fast != null) {
			if (slow == this || slow == fast)
				return true;
			slow = slow.nextSong;
			fast = fast.nextSong;
			if (fast != null)
				fast = fast.nextSong;
		}
		return false;

    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}

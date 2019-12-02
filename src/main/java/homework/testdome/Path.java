package homework.testdome;

import java.util.ArrayList;

public class Path {
	private String path;

    public Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void cd(String newPath) {
        int i=0;
        String[] targetPath=newPath.split("/"); 
        String[] currentPath=this.path.split("/");
        int newPathLength=targetPath.length;
        
        ArrayList<String> pathList=new ArrayList<String>(); //this cannot be an array, since it needs to have variable size
        for(int j=1; j<currentPath.length; j++){
        	pathList.add(currentPath[j]);
        }
        
        if(targetPath[0].equals("")){
        	//absolute pathname
        	pathList.clear();
        	pathList.add(targetPath[1]);
        	i=i+2;
        }
        
        while(i < newPathLength){
        	int k = pathList.size() - 1;
        	if(k >= 0) {
	        	if(targetPath[i].equals("..")){
	        		//parent directory
	        		pathList.remove(k);
	        	}else{
	        		//adding a child directory
	        		pathList.add(targetPath[i]);
	        	}
        	}
        	i++;
        }
       
        StringBuilder updatedPath=new StringBuilder();
        if(pathList.size() == 0) {
        	updatedPath.append("/");
        }else {
	        for(int l=0; l<pathList.size(); l++){
	        	updatedPath.append("/"+pathList.get(l));
	        }
        }
        
        //System.out.println(updatedPath);
        this.path=updatedPath.toString();
    }

    public static void main(String[] args) {
        Path path = new Path("/a/b/c/d");
        path.cd("../../../..");
        System.out.println(path.getPath());
    }
}

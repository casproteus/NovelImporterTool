package homework.testdome;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Folders {
	
    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
    	List<String> list = new ArrayList<String>();
    	Document document = null;
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			NodeList nodes = document.getElementsByTagName("folder");
			for(int i = 0; i < nodes.getLength(); i++) {
				String name = nodes.item(i).getAttributes().item(0).getNodeValue();
				if(name.startsWith(String.valueOf(startingLetter))) {
					list.add(name);
				}
			}
		} catch (Exception exp) {
			return null;
		}
		
    	return list;
    }
    
    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<folder name=\"c\">" +
                    "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                    "</folder>" +
                    "<folder name=\"users\" />" +
                "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }
}
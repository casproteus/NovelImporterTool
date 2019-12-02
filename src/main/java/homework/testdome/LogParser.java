package homework.testdome;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class LogParser {
    public static Collection<Integer> getIdsByMessage(String xml, String message) throws Exception {
    	List<Integer> list = new ArrayList<Integer>();
    	Document document = null;
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			NodeList nodes = document.getElementsByTagName("entry");
			for(int i = 0; i < nodes.getLength(); i++) {
				Node msg = ((Element)nodes.item(i)).getElementsByTagName("message").item(0);
				if(message.equals(msg.getTextContent())){
				
					String id = nodes.item(i).getAttributes().item(0).getNodeValue();
					list.add(Integer.valueOf(id));
				}
			}
		} catch (Exception exp) {
			return null;
		}
		
    	return list;
    }
    
    public static void main(String[] args) throws Exception {
        String xml = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<log>\n" + 
                "    <entry id=\"1\">\n" + 
                "        <message>Application started</message>\n" + 
                "    </entry>\n" + 
                "    <entry id=\"2\">\n" + 
                "        <message>Application ended</message>\n" + 
                "    </entry>\n" + 
                "</log>";
        
        Collection<Integer> ids = getIdsByMessage(xml, "Application ended");
        for(int id: ids)
            System.out.println(id); 
    }
}

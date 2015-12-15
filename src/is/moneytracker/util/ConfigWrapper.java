/**
 *
 */
package is.moneytracker.util;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.util.Pair;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Van-Duyet Le
 */
@XmlRootElement(name = "persons")
public class ConfigWrapper {

    private List<Pair<String, String>> data;

    @XmlElement(name = "person")
    public List<Pair<String, String>> getData() {
        return data;
    }

    public void setData(List<Pair<String, String>> data) {
        this.data = data;
    }

    public String get(String name) {
    	Iterator<Pair<String, String>> i = data.iterator();
    	while (i.hasNext()) {
    		Pair<String, String> current = i.next();
    		if (current.getKey() == name) return current.getValue();
    	}

    	return "";
    }
}

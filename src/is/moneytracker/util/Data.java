/**
 *
 */
package is.moneytracker.util;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.util.Pair;

/**
 * @author Van-Duyet Le
 *
 */
public class Data {
	private String filePath = "config.xml";
	private List<Pair<String, String>> dataList;

	public void loadDataFromFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(ConfigWrapper.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        ConfigWrapper wrapper = (ConfigWrapper) um.unmarshal(file);

	        dataList.clear();
	        dataList.addAll(wrapper.getData());

	        // Save the file path to the registry.
	        // setPersonFilePath(file);

	    } catch (Exception e) { // catches ANY exception
	        Message.Error("Could not load data from file:\n" + file.getPath());
	    }
	}

	public void saveDataToFile(File file) {
	    try {
	        JAXBContext context = JAXBContext
	                .newInstance(ConfigWrapper.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        ConfigWrapper wrapper = new ConfigWrapper();
	        wrapper.setData(dataList);

	        // Marshalling and saving XML to the file.
	        m.marshal(wrapper, file);

	        // Save the file path to the registry.
	        // setPersonFilePath(file);
	    } catch (Exception e) { // catches ANY exception
	        Message.Error("Could not save data to file:\n" + file.getPath());
	    }
	}


	public boolean set(String key, String value) {
		return false;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}

package victor.training.patterns.strategy;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface ConfigProvider {
	Properties getProperties();
}

class ConfigFileProvider implements ConfigProvider {
	
	public Properties getProperties() {
		try (InputStream is = ConfigFileProvider.class.getResourceAsStream("/application.properties")) {
			Properties properties = new Properties();
			properties.load(is);
			return properties; 
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class ConfigDatabaseProvider implements ConfigProvider {

	public Properties getProperties() {
		// Real implem goes here
		Properties properties = new Properties();
		properties.setProperty("someProp", "from database");
		return properties; 
	}
	
}


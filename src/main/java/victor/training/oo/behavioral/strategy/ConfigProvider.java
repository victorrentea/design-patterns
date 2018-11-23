package victor.training.oo.behavioral.strategy;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

public interface ConfigProvider {
	Properties getProperties();
}

@Service
@Profile("localProps")
class ConfigFileProvider implements ConfigProvider {
	
	@SneakyThrows
	public Properties getProperties() {
		try (InputStream is = ConfigFileProvider.class.getResourceAsStream("/application.properties")) {
			Properties properties = new Properties();
			properties.load(is);
			return properties; 
		}
	}
}

@Service
@Profile("!localProps")

class ConfigDatabaseProvider implements ConfigProvider {

	public Properties getProperties() {
		// Real implem goes here
		Properties properties = new Properties();
		properties.setProperty("someProp", "from database");
		return properties; 
	}
	
}


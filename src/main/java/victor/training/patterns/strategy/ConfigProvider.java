package victor.training.patterns.strategy;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Profile;

import java.io.InputStream;
import java.util.Properties;

public interface ConfigProvider {
	Properties getProperties();
}
@Profile("file.config")
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
@Profile("db.config")
class ConfigDatabaseProvider implements ConfigProvider {

	public Properties getProperties() {
		// Real implem goes here
		Properties properties = new Properties();
		properties.setProperty("someProp", "from database");
		return properties; 
	}
	
}


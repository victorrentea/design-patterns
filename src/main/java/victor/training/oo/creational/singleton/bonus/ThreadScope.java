package victor.training.oo.creational.singleton.bonus;

import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope {

	public Object get(String name, ObjectFactory<?> factory) {
		Object result = null;

		Map<String, Object> beans = ThreadScopeContextHolder.currentThreadScopeAttributes().getBeanMap();

		if (!beans.containsKey(name)) {
			result = factory.getObject();

			beans.put(name, result);
		} else {
			result = beans.get(name);
		}

		return result;
	}

	public Object remove(String name) {
		Object result = null;

		Map<String, Object> hBeans = ThreadScopeContextHolder.currentThreadScopeAttributes().getBeanMap();

		if (hBeans.containsKey(name)) {
			result = hBeans.get(name);

			hBeans.remove(name);
		}

		return result;
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		ThreadScopeContextHolder.currentThreadScopeAttributes().registerRequestDestructionCallback(name, callback);
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}
}

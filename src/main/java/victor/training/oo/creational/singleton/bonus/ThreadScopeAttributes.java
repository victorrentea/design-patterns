package victor.training.oo.creational.singleton.bonus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ThreadScopeAttributes {

    protected final Map<String, Object> beans = new HashMap<String, Object>();
    protected final Map<String, Runnable> destructionCallbacks = new LinkedHashMap<String, Runnable>();

    protected final Map<String, Object> getBeanMap() {
        return beans;
    }

    /**
     * Register the given callback as to be executed after request completion.
     * 
     * @param name
     *        The name of the bean.
     * @param callback
     *        The callback of the bean to be executed for destruction.
     */
    protected final void registerRequestDestructionCallback(String name, Runnable callback) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback must not be null");
        }
        destructionCallbacks.put(name, callback);
    }

    public final void clear() {
        processAllDestructionCallbacks();
        beans.clear();
    }

    private final void processAllDestructionCallbacks() {
        for (String name : destructionCallbacks.keySet()) {
            Runnable callback = destructionCallbacks.get(name);

            callback.run();
        }
        destructionCallbacks.clear();
    }
}
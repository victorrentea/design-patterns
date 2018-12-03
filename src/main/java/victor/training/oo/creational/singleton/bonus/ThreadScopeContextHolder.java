package victor.training.oo.creational.singleton.bonus;

public class ThreadScopeContextHolder {

	private static final ThreadLocal<ThreadScopeAttributes> threadScopeAttributesHolder = new InheritableThreadLocal<ThreadScopeAttributes>() {
		protected ThreadScopeAttributes initialValue() {
			return new ThreadScopeAttributes();
		}
	};

	public static ThreadScopeAttributes getThreadScopeAttributes() {
		return threadScopeAttributesHolder.get();
	}

	public static void setThreadScopeAttributes(ThreadScopeAttributes accessor) {
		ThreadScopeContextHolder.threadScopeAttributesHolder.set(accessor);
	}

	/**
	 * Gets current <code>ThreadScopeAttributes</code>.
	 */
	public static ThreadScopeAttributes currentThreadScopeAttributes() throws IllegalStateException {
		ThreadScopeAttributes accessor = threadScopeAttributesHolder.get();

		if (accessor == null) {
			throw new IllegalStateException("No thread scoped attributes.");
		}

		return accessor;
	}

}

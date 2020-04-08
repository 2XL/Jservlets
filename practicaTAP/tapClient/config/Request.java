package config;

import java.lang.reflect.Method;

public class Request {

	private String actionName;
	private Object[] args;

	public Request() {
		actionName = null;
		args = null;
	}

	public Request(String actionName, Object[] args) {
		this.actionName = actionName;
		this.args = args;
	}

	public Method getAction() throws NoSuchMethodException {
		Method action = null;
		try {
			Method[] methods = IServer.class.getMethods();
			for (Method m : methods) {
				if (m.getName().equals(actionName)) {
					action = m;
					break;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new NoSuchMethodException(e.getMessage());
		}
		return action;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}

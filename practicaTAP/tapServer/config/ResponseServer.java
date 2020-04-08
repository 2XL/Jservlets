package config;

public class ResponseServer {
	private Object value;

	public ResponseServer() {
		value = null;
	}
	
	public ResponseServer(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}

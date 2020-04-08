package config;

public class Response {
	private Object value;

	public Response() {
		value = null;
	}

	public Response(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}

package cc.kasumi.commons.json;

import com.google.gson.JsonObject;

public interface JsonSerializer<T> {

	JsonObject serialize(T t);

}

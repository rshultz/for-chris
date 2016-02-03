package piazza.services.ingest.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.apache.commons.collections.IteratorUtils;

public class NumericKeyValueJsonDeserializer extends JsonDeserializer<List<NumericKeyValue>>{

	@Override
	public List<NumericKeyValue> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		TypeReference<List<NumericKeyValue>> typeRef = new TypeReference<List<NumericKeyValue>>(){};
		ObjectMapper mapper = new ObjectMapper();
        JsonNode root = jp.getCodec().readTree(jp);
		String numericKeyValue = root.get("numericKeyValue").asText();
        System.out.println(numericKeyValue);
		return mapper.readValue( numericKeyValue, typeRef);
	}

}

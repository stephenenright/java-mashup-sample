package sample.utils;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class JsonUtils {

	
	public static <T> List<T> parseItemToList(String value, String attName, Class<T> cls) {
		List<T> resultList = new LinkedList<T>();
		
		if(value==null) {
			return resultList;
		}
		
	
		JsonNode node = parse(value);
		JsonNode itemNode = node.get(attName);
		
		if(itemNode==null) {
			return resultList;
		}
		
		
		return parseToList(itemNode, cls);
		
	}
	
	
	public static <T> List<T> parseToList(JsonNode node, Class<T> cls) {
		List<T> resultList = new LinkedList<T>();
		
		if(node==null) {
			return resultList;
		}

		Iterator<JsonNode> iter = node.iterator();
		
		
		while(iter.hasNext()) {
			resultList.add(parse(iter.next(), cls));
		}
		
		return resultList;
	}
	
	public static <T> List<T> parseToList(String value, Class<T> cls) {
		List<T> resultList = new LinkedList<T>();
		
		if(value==null) {
			return resultList;
		}
		
		return parseToList(parse(value), cls);
		
	}
	
	public static JsonNode parse(String value) {
		if(value==null) {
			return null;
		}
		return Json.parse(value);
	}
	
	public static <A>  A parse(JsonNode node, Class<A> cls) {
		return Json.fromJson(node, cls);
	}
	
	
	
	public static class Json {

	    private static final ObjectMapper defaultObjectMapper = new ObjectMapper();
	    
		static {
			defaultObjectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			defaultObjectMapper.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
			defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
	    
	    private static volatile ObjectMapper objectMapper = null;

	    // Ensures that there always is *a* object mapper
	    private static ObjectMapper mapper() {
	        if (objectMapper == null) {
	            return defaultObjectMapper;
	        } else {
	            return objectMapper;
	        }
	    }

	    
	    public static JsonNode toJson(final Object data) {
	        try {
	            return mapper().valueToTree(data);
	        } catch(Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
		public static void toJson(OutputStream out, Object value) {
			try {
				
				mapper().writeValue(out, value);
			}
			catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	    
	   
	    public static <A> A fromJson(JsonNode json, Class<A> clazz) {
	        try {
	            return mapper().treeToValue(json, clazz);
	        } catch(Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    
	    public static ObjectNode newObject() {
	        return mapper().createObjectNode();
	    }

	   
	    public static String stringify(JsonNode json) {
	        return json.toString();
	    }

	 
	    public static JsonNode parse(String src) {
	        try {
	            return mapper().readValue(src, JsonNode.class);
	        } catch(Throwable t) {
	            throw new RuntimeException(t);
	        }
	    }

	  
	    public static JsonNode parse(java.io.InputStream src) {
	        try {
	            return mapper().readValue(src, JsonNode.class);
	        } catch(Throwable t) {
	            throw new RuntimeException(t);
	        }
	    }


	    public static void setObjectMapper(ObjectMapper mapper) {
	        objectMapper = mapper;
	    }
	}
}


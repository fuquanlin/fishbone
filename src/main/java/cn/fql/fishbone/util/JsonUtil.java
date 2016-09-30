package cn.fql.fishbone.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T deserializeJson(String json, Class<T> t) {
        try {
        	if(json != null){
        		return mapper.readValue(json, t);
        	}
        } catch (IOException e) {
        	LOG.error(e.getMessage(), e);
        } catch (Exception e) {
        	LOG.warn(e.getMessage(), e);
        }
        return null;
    }

    public static <T> String serializeObject(T json) {
        try {
            return mapper.writeValueAsString(json);
        } catch (IOException e) {
        	LOG.error(e.getMessage(), e);
        } catch (Exception e) {
        	LOG.warn(e.getMessage(), e);
        }
        return "";
    }
    
}

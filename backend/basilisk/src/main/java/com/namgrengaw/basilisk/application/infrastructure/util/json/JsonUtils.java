package com.namgrengaw.basilisk.application.infrastructure.util.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.namgrengaw.basilisk.application.infrastructure.exceptions.BusinessLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public abstract class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static JsonNode getJson(String path) {
        File file = new File(path);
        return getJson(file);
    }

    public static JsonNode getJson(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(file);
        } catch(IOException e) {
            logger.error(e.getMessage());
        }
        throw new BusinessLogicException("An error has occurred trying to find the file!");
    }

}

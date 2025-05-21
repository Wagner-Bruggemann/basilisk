package com.namgrengaw.basilisk.application.infrastructure.exceptions.response;

import com.namgrengaw.basilisk.application.infrastructure.util.time.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static com.namgrengaw.basilisk.application.infrastructure.util.time.TimeUtils.getActualTimestamp;

public class ErrorResponse {


    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorResponse.class);
    public static final String CONTROLLER_ADVICE_ERROR_LOG = "A error with status code {} of type {} has ocurred in the endpoint \"{}\": {}";

    private int status;
    private String error;
    private String message;
    private String timestamp;
    private String path;

    public ErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = getActualTimestamp().toString();
        this.path = path;
        LOGGER.error(CONTROLLER_ADVICE_ERROR_LOG, status, error, path, message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

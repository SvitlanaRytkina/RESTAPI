package com.rest.utils;

import com.rest.model.WebApiException;
import com.rest.model.WebApiResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static com.rest.constants.FileConstants.MESSAGE_CODE_201;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

public class ErrorUtils {
    private static Logger LOG = Logger.getLogger(ErrorUtils.class);

    public static boolean isSuccessful(JSONObject response) {
        LOG.info("Checking if response code is OK");
        WebApiResponse status = ResponseUtils.readResponseStatus(response);
        LOG.info(String.format("Status code: %s;\n Status message: %s", status.getCode(), status.getMessage()));
        return status.getCode() == (OK.getStatusCode());
    }

    public static boolean isSuccessfullyCreated(JSONObject response) {
        LOG.info("Checking if response code is CREATED");
        WebApiResponse status = ResponseUtils.readResponseStatus(response);
        LOG.info(String.format("Status code: %s;\n Status message: %s", status.getCode(), status.getMessage()));
        boolean isCodeEqual = status.getCode() == (CREATED.getStatusCode());
        boolean isMessageEqual = status.getMessage().equals(MESSAGE_CODE_201);
        return isCodeEqual && isMessageEqual;
    }

    public static WebApiException parseError(JSONObject response) {
        LOG.info("Parsing the response error code and response error message");
        WebApiResponse error = ResponseUtils.readResponseStatus(response);
        LOG.info(String.format("Error code: %s;\n Error message: %s", error.getCode(), error.getMessage()));
        return new WebApiException(error.getCode(), error.getMessage());
    }
}

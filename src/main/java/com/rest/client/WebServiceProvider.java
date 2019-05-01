package com.rest.client;

import com.rest.model.User;
import com.rest.model.WebApiException;
import com.rest.model.WebApiResponse;
import com.rest.utils.ErrorUtils;
import com.rest.utils.ResponseUtils;
import com.rest.utils.XlsUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rest.constants.FileConstants.FILE_PATH;
import static com.rest.constants.UrlConstants.*;

public class WebServiceProvider {
    private WebApi api;

    public WebServiceProvider() {
        api = new WebApi(PROTOCOL, HOST);
    }

    public List<User> getListOfUsers() throws WebApiException {
        List<User> result;
        Map<String, String> args = new HashMap<String, String>() {{
            put(FORMAT, FORMAT_JSON);
            put(ACCESS_TOKEN, XlsUtils.readAccessToken(FILE_PATH));
        }};

        JSONObject response = new JSONObject(api.sendGetRequest(URL_GET, args));
        if (ErrorUtils.isSuccessful(response)) {
            result = ResponseUtils.readResponseResults(response);
        } else {
            throw ErrorUtils.parseError(response);
        }
        return result;
    }

    public User getUserById(int id) throws WebApiException {
        User result;
        Map<String, String> args = new HashMap<String, String>() {{
            put(FORMAT, FORMAT_JSON);
            put(ACCESS_TOKEN, XlsUtils.readAccessToken(FILE_PATH));
        }};

        JSONObject response = new JSONObject(api.sendGetRequest(String.format(URL_GET_BY_ID, id), args));
        if (ErrorUtils.isSuccessful(response)) {
            result = ResponseUtils.readResponseResult(response);
        } else {
            throw ErrorUtils.parseError(response);
        }
        return result;
    }

    public User addNewUser(User user) throws WebApiException {
        User result;
        Map<String, String> args = new HashMap<String, String>() {{
            put(FORMAT, FORMAT_JSON);
            put(ACCESS_TOKEN, XlsUtils.readAccessToken(FILE_PATH));
        }};
        JSONObject response = new JSONObject(api.sendPostRequest(URL_GET, args, user));
        if (ErrorUtils.isSuccessfullyCreated(response)) {
            result = ResponseUtils.readResponseResult(response);
        } else {
            throw ErrorUtils.parseError(response);
        }
        return result;
    }

    public WebApiResponse removeUserById(int id) {
        Map<String, String> args = new HashMap<String, String>() {{
            put(FORMAT, FORMAT_JSON);
            put(ACCESS_TOKEN, XlsUtils.readAccessToken(FILE_PATH));
        }};
        JSONObject response = new JSONObject(api.sendDeleteRequest(String.format(URL_GET_BY_ID, id), args));
        return ResponseUtils.readResponseStatus(response);
    }

    public User updateUser(User user) throws WebApiException {
        User result;
        Map<String, String> args = new HashMap<String, String>() {{
            put(FORMAT, FORMAT_JSON);
            put(ACCESS_TOKEN, XlsUtils.readAccessToken(FILE_PATH));
        }};
        JSONObject response = new JSONObject(api.sendPutRequest(String.format(URL_GET_BY_ID, user.getId()), args, user));
        if (ErrorUtils.isSuccessful(response)) {
            result = ResponseUtils.readResponseResult(response);
        } else {
            throw ErrorUtils.parseError(response);
        }
        return result;
    }
}

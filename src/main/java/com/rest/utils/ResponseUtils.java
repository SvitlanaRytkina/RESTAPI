package com.rest.utils;

import com.rest.model.User;
import com.rest.model.WebApiResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.rest.constants.ApiKeyConstants.*;

public class ResponseUtils {
    private static Logger LOG = Logger.getLogger(ResponseUtils.class);

    public static WebApiResponse readResponseStatus(JSONObject jsonObject) {
        LOG.info("Reading the meta data from response");
        JSONObject meta = jsonObject.getJSONObject(API_META);
        return new WebApiResponse(meta.getInt(API_CODE), meta.getString(API_MESSAGE));
    }

    public static List<User> readResponseResults(JSONObject response) {
        LOG.info("Reading the result data from response");
        JSONArray resultList = response.getJSONArray("result");
        List<User> users = new ArrayList<>();
        resultList.forEach(o -> {
            if (o instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) o;
                users.add(setUserInfo(jsonObject));
            }
        });
        return users;
    }

    public static User readResponseResult(JSONObject response) {
        LOG.info("Reading the result data from response");
        JSONObject jsonObject = response.getJSONObject("result");
        return setUserInfo(jsonObject);
    }

    private static User setUserInfo(JSONObject jsonObject) {
        User user = new User();
        user.setId(jsonObject.getInt(API_ID));
        user.setName(jsonObject.getString(API_NAME));
        user.setGender(jsonObject.getString(API_GENDER));
        user.setDateOfBirth(jsonObject.getString(API_DATE_OF_BIRTH));
        user.setEmail(jsonObject.getString(API_EMAIL));
        user.setPhone(jsonObject.getString(API_PHONE));
        user.setWebsite(jsonObject.getString(API_WEBSITE));
        user.setAddress(jsonObject.getString(API_ADDRESS));
        user.setStatus(jsonObject.getString(API_STATUS));
        user.setLink(jsonObject.getJSONObject(API_LINKS).getJSONObject(API_SELF).getString(API_HREF));
        user.setAvatarLink(jsonObject.getJSONObject(API_LINKS).getJSONObject(API_AVATAR).getString(API_HREF));
        return user;
    }

    public static String getJsonFromUserInfo(User user) {
        Map<String, String> map = new HashMap<String, String>() {{
            put(API_EMAIL, user.getEmail());
            put(API_FIRST_NAME, user.getName().split(" ")[0]);
            put(API_LAST_NAME, user.getName().split(" ")[1]);
            put(API_GENDER, user.getGender());
            put(API_DATE_OF_BIRTH, user.getDateOfBirth());
            put(API_PHONE, user.getPhone());
            put(API_WEBSITE, user.getWebsite());
            put(API_ADDRESS, user.getAddress());
            put(API_STATUS, user.getStatus());
            put(API_AVATAR, user.getAvatarLink());
        }};
        return map.entrySet().stream().map(el -> String.format("\"%s\": \"%s\"", el.getKey(), el.getValue())).collect(Collectors.joining(",\n", "{", "\n}"));
    }
}

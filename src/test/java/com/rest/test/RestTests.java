package com.rest.test;

import com.rest.client.WebServiceProvider;
import com.rest.model.User;
import com.rest.model.WebApiException;
import com.rest.model.WebApiResponse;
import com.rest.utils.XlsUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static com.rest.constants.FileConstants.FILE_PATH;
import static com.rest.constants.FileConstants.MESSAGE_CODE_204;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static org.testng.Assert.assertEquals;

public class RestTests {
    private static Logger LOG = Logger.getLogger(RestTests.class);
    private WebServiceProvider provider = new WebServiceProvider();

    @Test(testName = "Get all users and check the response result")
    public void getAllUsersSuccessTest() throws WebApiException {
//        XlsUtils.updateSheet(FILE_PATH, provider.getListOfUsers());
        LOG.info("Test 'Get all users and check the response result' started");
        List<User> expectedUsers = XlsUtils.read(FILE_PATH);
        LOG.info("Get list of all users");
        List<User> actualUsers = provider.getListOfUsers();

        LOG.info(String.format("Response size is %s: %s", expectedUsers.size(), expectedUsers.size() == actualUsers.size()));
        assertEquals(expectedUsers.size(), actualUsers.size());
    }

    @Test(testName = "Get user by id and check the response result")
    public void getPlaceByIdSuccessTest() throws WebApiException {
        LOG.info("Test 'Get user by id and check the response result' started");
        User expectedUser = XlsUtils.read(FILE_PATH).get(9);
        LOG.info(String.format("Expected user is '%s'", expectedUser.toString()));
        LOG.info("Getting response from service...");
        User actualUser = provider.getUserById(expectedUser.getId());
        LOG.info("Expected and actual results are equal: " + (actualUser.equals(expectedUser)));
        assertEquals(actualUser, expectedUser);
    }

    @Test(testName = "Get user by not valid id and check the response result")
    public void getUserByNotValidIdFailureTest() {
        LOG.info("Test 'Get user by not valid id and check the response result' started");
        WebApiException expectedException = new WebApiException(404, "The requested resource does not exist.");
        LOG.info(String.format("Expected exception is [%s]", expectedException.getMessage()));
        try {
            LOG.info("Getting response from service...");
            provider.getUserById(5000);
        } catch (WebApiException actualException) {
            LOG.info(String.format("Actual exception is [%s]", actualException.getMessage()));
            LOG.info("Expected and actual response codes are equal: " + (actualException.getCode() == expectedException.getCode()));
            assertEquals(actualException.getCode(), expectedException.getCode());
            LOG.info("Expected and actual response messages are equal: " +
                    (actualException.getErrorMessage().equals(expectedException.getErrorMessage())));
            assertEquals(actualException.getErrorMessage(), expectedException.getErrorMessage());
        }
    }

    @Test(testName = "Add and delete new user and check the response results")
    public void addNewUserSuccessTest() throws WebApiException {
        LOG.info("Test 'Add, update and delete new user and check the response results'");
        User expectedUser = XlsUtils.readNewUser(FILE_PATH);
        LOG.info(String.format("Expected added user '%s'", expectedUser.toString()));
        LOG.info("Getting response from service...");
        User actualUser = provider.addNewUser(expectedUser);
        expectedUser.setId(actualUser.getId());
        expectedUser.setLink(actualUser.getLink());
        LOG.info(String.format("Actual added user is [%s]", actualUser.toString()));
        LOG.info("Expected and actual users are equal: " + (actualUser.equals(expectedUser)));
        assertEquals(actualUser, expectedUser);

        LOG.info("Updating the added user");
        expectedUser.setName("Kate Tree");
        LOG.info(String.format("Expected added user '%s'", expectedUser.toString()));
        LOG.info("Getting response from service...");
        actualUser = provider.updateUser(expectedUser);
        LOG.info(String.format("Actual added user is [%s]", actualUser.toString()));
        LOG.info("Expected and actual users are equal: " + (actualUser.equals(expectedUser)));
        assertEquals(actualUser, expectedUser);

        LOG.info("Deleting the added user");
        WebApiResponse expectedResponse = new WebApiResponse(NO_CONTENT.getStatusCode(), MESSAGE_CODE_204);
        LOG.info(String.format("Expected response is [%s]", expectedResponse.getResponse()));
        WebApiResponse actualResponse = provider.removeUserById(actualUser.getId());
        LOG.info(String.format("Actual response is [%s]", actualResponse.getResponse()));
        LOG.info("Expected and actual response codes are equal: " + (actualResponse.getCode() == expectedResponse.getCode()));
        assertEquals(actualResponse.getCode(), expectedResponse.getCode());
        LOG.info("Expected and actual response messages are equal: " +
                (actualResponse.getMessage().equals(expectedResponse.getMessage())));
        assertEquals(actualResponse.getMessage(), expectedResponse.getMessage());
    }
}

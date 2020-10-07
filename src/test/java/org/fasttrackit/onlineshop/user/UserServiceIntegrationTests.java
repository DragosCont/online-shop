package org.fasttrackit.onlineshop.user;


import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.service.UserService;
import org.fasttrackit.onlineshop.steps.UserTestSteps;
import org.fasttrackit.onlineshop.transfer.user.SaveUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class UserServiceIntegrationTests {

    //field-injection
    @Autowired
    private UserService userService;

    @Autowired
    private UserTestSteps userTestSteps;

    @Test
    public void createUser_whenValidRequest_thenReturnSavedUser() {
        userTestSteps.createUser();
    }



    @Test
    public void createUser_whenMissingFirstName_thenThrowException() {
        SaveUserRequest request = new SaveUserRequest();
        request.setFirstName(null);
        request.setLastName("Test Last Name");

        Exception exception = null;
        try {
            userService.createUser(request);
        } catch (Exception e) {
            exception = e;
        }

        assertThat(exception, notNullValue());
        assertThat("Unexpected exception type.", exception instanceof TransactionSystemException);

    }

    @Test
    public void getUser_whenExistingUser_thenReturnUser() {
        User createdUser = userTestSteps.createUser();

        User userResponse = userService.getUser(createdUser.getId());

        assertThat(userResponse, notNullValue());
        assertThat(userResponse.getId(), is(createdUser.getId()));
        assertThat(userResponse.getFirstName(), is(createdUser.getFirstName()));
        assertThat(userResponse.getLastName(), is(createdUser.getLastName()));
    }

    @Test
    public void getUser_whenNonExistingUser_thenThrowResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.getUser(999999));

    }

    @Test
    public void updateUser_whenExistingUser_thenReturnUpdatedUser() {
        User createdUser = userTestSteps.createUser();

        SaveUserRequest request = new SaveUserRequest();
        request.setFirstName(createdUser.getFirstName() + "Updated");
        request.setLastName(createdUser.getLastName() + "Updated");

        User updatedUser = userService.updateUser(createdUser.getId(), request);

        assertThat(updatedUser, notNullValue());
        assertThat(updatedUser.getId(), is(createdUser.getId()));
        assertThat(updatedUser.getFirstName(), is(request.getFirstName()));
        assertThat(updatedUser.getLastName(), is(request.getLastName()));
    }

    @Test
    public void deleteUser_whenExistingUser_thenTheUserIsDeleted() {
        User createdUser = userTestSteps.createUser();

        userService.deleteUser(createdUser.getId());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.getUser(createdUser.getId()));

    }


}

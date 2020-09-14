package com.practice.concurrency.highconcurrency;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description
 * Date 2020/6/21 17:35
 * Created by kwz
 */
@Data
public class User {
    private String userId;
    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    public static void main(String[] args) {

        User user1 = new User();
        user1.setUserId("2");
        user1.setUserName("hah");

        User user2 = new User();
        user2.setUserId("1");
        user2.setUserName("hahhh");

        System.out.println(user1.equals(user2));
    }
}

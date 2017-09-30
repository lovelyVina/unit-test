package com.vina.test.demo.domain;

/**
 * @author fWang
 * @Description:
 * @date 2017/9/26 13:26
 */
public class UserDO {
    private Long userID;
    private String userName;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserDO() {
    }

    public UserDO(Long userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "userId:" + userID + ", userName" + userName;
    }
}

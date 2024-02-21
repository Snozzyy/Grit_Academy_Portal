package model;

import java.io.Serializable;

public class UserBean implements Serializable {
    private StateType stateType;
    private UserType userType;
    private PrivilegeType privilegeType;
    private String fname;
    private int id;

    public UserBean(StateType stateType, UserType userType, PrivilegeType privilegeType, String fname, int id){
        this.stateType = stateType;
        this.userType = userType;
        this.privilegeType = privilegeType;
        this.fname = fname;
        this.id = id;
    }

    public StateType getStateType() {
        return stateType;
    }

    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public PrivilegeType getPrivilegeType() {
        return privilegeType;
    }

    public void setPrivilegeType(PrivilegeType privilegeType) {
        this.privilegeType = privilegeType;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getId() {
        return id;
    }
}

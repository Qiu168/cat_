package com.HuangTaiQi.www.po;

/**
 * @author 14629
 */
public class UserEntity {
    private Integer id;
    private String studentNumber;
    private String name;
    private String username;
    private String password;
    private String electromobileModel;
    private String electromobileNumber;
    private Integer state;
    private Integer authorityId;
    public static int FORBID=0;
    public static int FREE=1;
    public static int CHARGING=2;
    public static int PARKING=3;



    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getElectromobileModel() {
        return electromobileModel;
    }

    public void setElectromobileModel(String electromobileModel) {
        this.electromobileModel = electromobileModel;
    }

    public String getElectromobileNumber() {
        return electromobileNumber;
    }

    public void setElectromobileNumber(String electromobileNumber) {
        this.electromobileNumber = electromobileNumber;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", electromobileModel='" + electromobileModel + '\'' +
                ", electromobileNumber='" + electromobileNumber + '\'' +
                ", state=" + state +
                ", authorityId=" + authorityId +
                '}';
    }
}

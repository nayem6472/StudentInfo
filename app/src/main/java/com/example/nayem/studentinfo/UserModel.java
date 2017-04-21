package com.example.nayem.studentinfo;

/**
 * Created by NAYEM on 4/21/2017.
 */

public class UserModel {
    private String uId,name,Id,department,result,address;

    public UserModel() {
    }

    public UserModel(String uId, String name, String id, String department, String result, String address) {
        this.uId = uId;
        this.name = name;
        Id = id;
        this.department = department;
        this.result = result;
        this.address = address;
    }

    public String getuId() {
        return uId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public String getDepartment() {
        return department;
    }

    public String getResult() {
        return result;
    }

    public String getAddress() {
        return address;
    }
}

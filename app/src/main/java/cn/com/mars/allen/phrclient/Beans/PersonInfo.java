package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/27.
 */
public class PersonInfo {
    private String person_id = null;
    private String password = null;
    private String name = null;
    private Integer gender = null;
    private Integer age = null;
    private String phone = null;
    private Integer vip = null;
    private String bloodType = null;
    private Integer group_id = null;

    public PersonInfo() {
    }

    public PersonInfo(String person_id, String password, String name, Integer gender, Integer age, String phone, Integer vip, String bloodType, Integer group_id) {
        this.person_id = person_id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.vip = vip;
        this.bloodType = bloodType;
        this.group_id = group_id;
    }

    public PersonInfo(PersonInfo personInfo) {
        this.person_id = personInfo.person_id;
        this.password = personInfo.password;
        this.name = personInfo.name;
        this.gender = personInfo.gender;
        this.age = personInfo.age;
        this.phone = personInfo.phone;
        this.vip = personInfo.vip;
        this.bloodType = personInfo.bloodType;
        this.group_id = personInfo.group_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getVip() {
        return vip;
    }

    public String getBloodType() {
        return bloodType;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
}

package cn.com.mars.allen.phrclient.Beans;

/**
 * Created by Allen on 2015/7/29.
 */
public class Doctor {
    private Integer doctor_id = null;
    private String doc_name = null;
    private Integer dep_id = null;
    private String doc_profile = null;
    private String doc_phone = null;

    public Doctor() {
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    public void setDoc_profile(String doc_profile) {
        this.doc_profile = doc_profile;
    }

    public void setDoc_phone(String doc_phone) {
        this.doc_phone = doc_phone;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public String getDoc_profile() {
        return doc_profile;
    }

    public String getDoc_phone() {
        return doc_phone;
    }

    @Override
    public String toString() {
        return doc_name + '\n' + "电话:" + doc_phone;
    }
}

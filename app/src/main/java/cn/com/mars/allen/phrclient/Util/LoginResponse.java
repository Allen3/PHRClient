package cn.com.mars.allen.phrclient.Util;

/**
 * Created by Allen on 2015/7/27.
 */
public class LoginResponse extends PersonInfo {
    private String _LOGIN_RESPONSE_;

    private String person_id = null;
    private String password = null;
    private String name = null;
    private Integer gender = null;
    private Integer age = null;
    private String phone = null;
    private Integer vip = null;
    private String bloodType = null;
    private Integer group_id = null;

    public LoginResponse() {
        super();
    }

    public LoginResponse(PersonInfo personInfo) {
        super(personInfo);
    }

    public LoginResponse(String _LOGIN_RESPONSE_) {
        this._LOGIN_RESPONSE_ = _LOGIN_RESPONSE_;
    }

    public void set_LOGIN_RESPONSE(String _LOGIN_RESPONSE_) {
        this._LOGIN_RESPONSE_ = _LOGIN_RESPONSE_;
    }

    public String get_LOGIN_RESPONSE() {
        return _LOGIN_RESPONSE_;
    }
}

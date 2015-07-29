package cn.com.mars.allen.phrclient.Util;

/**
 * Created by Allen on 2015/7/27.
 */
public class Constants {
    //  Information on local machine.
    public static final String PATH = "http://192.168.1.110:8080/";
    public static final String LOC_DB_NAME = "phr.db";
    public static final String LOC_FILE_PERSONINFO = "personinfo.json";

    //  Login Module.
    public static final String _LOGIN_SUCCESS_ = "_LOGIN_SUCCESS_";
    public static final String _LOGIN_FAIL_ = "_LOGIN_DENY_";

    //  Register Module.
    public static final String _REGISTER_SUCCESS_ = "_REGISTER__SUCCESS_";
    public static final String _REGISTER_FAIL_ = "_REGISTER_DENY_";

    //  Database Table PersonInfo.
    public static final String PERSON_ID = "person_id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String AGE = "age";
    public static final String PHONE = "phone";
    public static final String VIP = "vip";
    public static final String BLOODTYPE = "bloodType";
    public static final String PASSWORD = "password";
    public static final String GROUP_ID = "group_id";

    //  News Module.
    public static final String _NEWS_ = "_NEWS_";
    public static final String _NEWSTYPE_ = "_NEWSTYPE_";

    //  News Type.
    public static final String HEALTH_NEWS = "health_news";
    public static final String DRUG_NEWS = "drug_news";
    public static final String DISEASE_NEWS = "disease_news";
    public static final String IMMUNE_NEWS = "immune_news";
    public static final String NEWS_NEWS = "news_news";

    //  Database Table NewsInfo.
    public static final String NEWS_TYPE = "news_Type";
    public static final String NEWS_TITLE = "news_Title";
    public static final String NEWS_CONTENT = "news_Content";


    //  Database Table Hospital.
    public static final String HID = "hid";
    public static final String HNAME = "hname";
    public static final String HADDRESS = "haddress";
    public static final String HPROFILE = "hprofile";

    //  Database Table Department.
    public static final String DEP_ID = "dep_id";
    public static final String DEP_NAME = "dep_name";
    //public static final String HID = "hid";               F.K.

    //  Database Table Doctor.
    public static final String DOCTOR_ID = "doctor_id";
    public static final String DOC_NAME = "doc_name";
    //public static final String DEP_ID = "dep_id";           F.K.
    public static final String DOC_PROFILE = "doc_profile";
    public static final String DOC_PHONE = "doc_phone";

    //  Database Table Equipment.
    public static final String EQUIP_ID = "equip_id";
    public static final String EQUIP_NAME = "equip_name";
    public static final String EQUIP_PRICE = "equip_price";
}

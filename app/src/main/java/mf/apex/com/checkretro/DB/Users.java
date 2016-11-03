package mf.apex.com.checkretro.DB;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by user on 05/07/2016.
 */
public class Users extends SugarRecord {

    String uname;
    String department;

    public Users() {
    }

    public Users(String uname, String department) {
        this.uname = uname;
        this.department = department;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

package instagram2.comments.services.configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("config-properties")
public class ConfigProperties {

    @ConfigValue(value = "dburl", watch = true)
    private String dburl;
    @ConfigValue(value = "dbuser", watch = true)
    private String dbuser;
    @ConfigValue(value = "dbpass", watch = true)
    private String dbpass;

    public String getDbuser() {
        return dbuser;
    }

    public String getDbpass() {
        return dbpass;
    }

    public String getDburl() {
        return dburl;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public void setDbpass(String dbpass) {
        this.dbpass = dbpass;
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }
}

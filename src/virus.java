import java.util.Date;

/**
 * Created by vajira on 5/13/17.
 */

//this is used for storing the data structure of virus for generating report
//It is possible to add more data about virus structure


public class virus {
    private String name;
    private String type;
    private String corporation;
    private String effectiveness;


    public String getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }



}

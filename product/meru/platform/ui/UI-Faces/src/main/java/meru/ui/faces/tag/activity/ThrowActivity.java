package meru.ui.faces.tag.activity;

public class ThrowActivity extends UIActivity {

    public static final String NAME = "throw";

    public ThrowActivity() {
        super(NAME);
    }

    public String getCondition() {
        return getMandatoryAttribute("condition");
    }

    public String getMessage() {
        return getAttribute("message");
    }

    public String getLink() {
        return getAttribute("link");
    }
    
}

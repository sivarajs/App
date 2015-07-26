package meru.ui.faces.tag.activity;

public class RedirectActivity extends UIActivity {

    public static final String NAME = "redirect";

    public RedirectActivity() {
        super(NAME);
    }

    public String getHref() {
        return getAttribute("href");
    }

}

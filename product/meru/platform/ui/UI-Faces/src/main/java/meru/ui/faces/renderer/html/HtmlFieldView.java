package meru.ui.faces.renderer.html;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import meru.sys.lang.ClassHelper;
import meru.ui.faces.EntityDataProvider;
import meru.ui.faces.renderer.UIFieldView;
import meru.ui.faces.renderer.ViewBuilder;

public class HtmlFieldView extends UIFieldView {

    public HtmlFieldView(String fieldExpression) {
        super(fieldExpression,
              false);
    }

    public HtmlFieldView(String fieldExpression,
                         boolean escape) {
        super(fieldExpression,
              escape);
    }

    @Override
    public void build(ViewBuilder viewBuilder,
                      EntityDataProvider dataProvider) throws IOException {

        Object value = null;
        if (mVariable == null) {
            value = getVariableValue(mFieldExpression);
        }
        else {
            value = getVariableValue(mVariable,
                                     mFieldExpression);
        }
        if (value == null) {
            return;
        }

        String val = ClassHelper.toString(value);
        if (mEscape) {
            try {
                val = URLEncoder.encode(val,
                                        "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        ((HtmlBuilder) viewBuilder).addText(val,
                                            false);
    }

}

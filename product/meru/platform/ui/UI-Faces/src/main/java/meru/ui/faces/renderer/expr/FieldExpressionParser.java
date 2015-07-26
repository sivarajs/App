package meru.ui.faces.renderer.expr;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import meru.sys.lang.ClassHelper;
import meru.ui.faces.renderer.UIFieldView;
import meru.ui.faces.renderer.html.HtmlBuilder;
import meru.ui.faces.renderer.html.HtmlFieldView;
import meru.ui.faces.renderer.html.HtmlRendererContext;

import meru.el.EL;;

public class FieldExpressionParser implements EL.ExpressionParser {

    private HtmlBuilder mHtmlBuilder;
    private HtmlRendererContext mViewContext;
    private boolean mEscape;

    public FieldExpressionParser(HtmlBuilder htmlBuilder,
                                 HtmlRendererContext viewContext) {
        this(htmlBuilder,
             viewContext,
             false);
    }

    public FieldExpressionParser(HtmlBuilder htmlBuilder,
                                 HtmlRendererContext viewContext,
                                 boolean escape) {
        mHtmlBuilder = htmlBuilder;
        mViewContext = viewContext;
        mEscape = escape;
    }
    
    public FieldExpressionParser(HtmlRendererContext viewContext,
                                 boolean escape) {
        mHtmlBuilder = new HtmlBuilder();
        mViewContext = viewContext;
        mEscape = escape;
    }

    @Override
    public void readVariable(String variable) {
        Object object = mViewContext.getCurrentView()
                                    .getVariableValue(variable);
        if (object != null) {
            String value = ClassHelper.toString(object);
            if (value != null) {
                if (mEscape) {
                    try {
                        value = URLEncoder.encode(value,
                                                  "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
                mHtmlBuilder.addText(value,
                                     false);
            }

        }
        else {
            UIFieldView field = new HtmlFieldView(variable,
                                                  mEscape);
            mViewContext.addUIView(field);
        }
    }

    @Override
    public void readText(String text) {
        mHtmlBuilder.addText(text,
                             false);
    }
}

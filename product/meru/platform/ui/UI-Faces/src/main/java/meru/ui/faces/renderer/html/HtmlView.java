package meru.ui.faces.renderer.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import meru.ui.faces.EntityDataProvider;
import meru.ui.faces.renderer.UIView;
import meru.ui.faces.renderer.ViewBuilder;

public class HtmlView extends UIView {

    private List<String> mStaticHtmls;
    private Map<Integer, UIView> mHtmlFragments;

    protected HtmlBuilder mHtmlBuilder;
    protected EntityDataProvider mEntityDataProvider;

    public HtmlView() {
        mStaticHtmls = new ArrayList<String>(1);
        mHtmlBuilder = new HtmlBuilder();
    }

    public void addHtml(String html) {
        mStaticHtmls.add(html);
    }

    public void addUIView(UIView htmlFragment) {

        if (mHtmlFragments == null) {
            mHtmlFragments = new LinkedHashMap<Integer, UIView>(1);
        }

        htmlFragment.setParent(this);

        Integer loc = mStaticHtmls.size();

        mStaticHtmls.add(null);

        mHtmlFragments.put(loc,
                           htmlFragment);
    }

    @Override
    public final void build(ViewBuilder viewBuilder,
                            EntityDataProvider dataProvider) throws IOException {
        mHtmlBuilder = (HtmlBuilder) viewBuilder;
        mEntityDataProvider = dataProvider;
        buildHtml();
    }

    protected void buildHtml() throws IOException {
        generateHtml();
    }
    
    public void generateHtml() throws IOException {

        int count = 0;
        for (String html : mStaticHtmls) {

            if (html == null) {
                UIView htmlFragment = mHtmlFragments.get(count);
                htmlFragment.build(mHtmlBuilder,
                                   mEntityDataProvider);
            }
            else {
                mHtmlBuilder.addText(html);
            }
            count++;
        }
    }
}

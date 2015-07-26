package meru.ui.faces.renderer.html.component.other;

import java.io.IOException;

import meru.ui.faces.renderer.expr.ViewExpressionParser;
import meru.ui.faces.renderer.html.HtmlView;
import meru.ui.faces.tag.component.other.BreadCrumb;

public class BreadCrumbHtmlView extends HtmlView {

  private static final String SEPARATOR = " &#187; ";

  private BreadCrumb mBreadCrumb;

  public BreadCrumbHtmlView(BreadCrumb breadCrumb) {
    mBreadCrumb = breadCrumb;
  }

  @Override
  protected void buildHtml() throws IOException {

    String value = ViewExpressionParser.parseText(mBreadCrumb.getPath(),
                                                  this);

    String appRoot = ViewExpressionParser.parseText("#{application.root}",
                                                    this);

    mHtmlBuilder.startElement("a")
                .addAttribute("href",
                              appRoot)
                .addText("Home")
                .endElement();

    StringBuilder strBuilder = new StringBuilder(20);

    // if (storeName != null) {
    //
    // buildAnchor(mHtmlBuilder,
    // strBuilder,
    // storeName);
    // }

    String[] categories = value.split("[/]");
    strBuilder.append(appRoot + "/" + mBreadCrumb.getType());
    for (int i = 1; i < categories.length; i++) {
      buildAnchor(strBuilder,
                  categories[i]);
    }

    if (mBreadCrumb.getSuffix() != null) {

      String suffix = ViewExpressionParser.parseText(mBreadCrumb.getSuffix(),
                                                     this);
      mHtmlBuilder.addText(SEPARATOR,
                           false)
                  .addText(suffix);

    }
  }

  private final void buildAnchor(StringBuilder strBuilder,
                                 String category) {

    strBuilder.append("/")
              .append(category);

    mHtmlBuilder.addText(SEPARATOR,
                         false)
                .startElement("a")
                .addAttribute("href",
                              strBuilder.toString())
                .addText(category)
                .endElement();
  }
}
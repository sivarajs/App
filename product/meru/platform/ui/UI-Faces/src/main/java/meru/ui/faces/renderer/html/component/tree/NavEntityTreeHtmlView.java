package meru.ui.faces.renderer.html.component.tree;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import meru.el.EL;
import meru.ui.faces.renderer.EntityHierarchy.Folder;
import meru.ui.faces.renderer.EntityHierarchy.Item;
import meru.ui.faces.renderer.html.HierarchicalEntityHtmlView;
import meru.ui.faces.renderer.html.HtmlBuilder;
import meru.ui.faces.tag.component.tree.NavEntityTree;

public class NavEntityTreeHtmlView extends HierarchicalEntityHtmlView {

    private String mHref;
    private HtmlBuilder mSubTreeBuilder;
    private byte mSubTreeColumns = 1;
    private byte mSubTreeItemCount;
    private int mNumItems;
    private int mMenuWidth;

    public NavEntityTreeHtmlView(NavEntityTree navEntityTree) {
        super("/",
              navEntityTree.getEntityName(),
              navEntityTree.getFilter());

        mHref = navEntityTree.getHref();
        mNumItems = navEntityTree.getNumItems();
        mMenuWidth = navEntityTree.getMenuWidth();

        mSubTreeBuilder = new HtmlBuilder();
    }

    @Override
    protected void postWriteEntities() throws IOException {

        Folder root = mEntityHierarchy.buildFolders();
        mHtmlBuilder.startElement("ul");

        for (Item item : root.getItems()) {
            if (item instanceof Folder) {
                addFolder((Folder) item);
            }
        }
        mHtmlBuilder.endElement();
    }

    private void addFolder(Folder folder) {

        mHtmlBuilder.startElement("li")
                    .startElement("span")
                    .addAttribute("itemId",
                                  folder.id)
                    .addText(folder.label)
                    .endElement()
                    .startElement("div")
                    .addClassAttribute("arrow")
                    .endElement();

        // if (mHref != null) {
        // mHtmlBuilder.append("</a>");
        // }
        if (!folder.isEmpty()) {

            try {
                addSubNavTree(folder);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        mHtmlBuilder.endElement();

    }

    private void addSubNavTree(Folder folder) throws UnsupportedEncodingException {

        if (!folder.getItems()
                   .isEmpty()) {

            mSubTreeBuilder.reset();
            mSubTreeBuilder.startElement("div")
                           .addStyleAttribute("float:left;width:" + mMenuWidth
                                   + "px");

            for (Item item : folder.getItems()) {

                if (mSubTreeItemCount >= mNumItems) {
                    mSubTreeItemCount = 0;
                    mSubTreeColumns++;

                    // </div>
                    mSubTreeBuilder.endElement();
                    mSubTreeBuilder.startElement("div")
                                   .addStyleAttribute("float:left;width:"
                                           + mMenuWidth + "px");
                }

                if (item instanceof Folder) {
                    mSubTreeBuilder.startElement("div")
                                   .addClassAttribute("subNavTreeMenu");
                    addItem(item,
                            "subNavTreeTitle");

                    addSubNavTree2((Folder) item);
                    // </div>
                    mSubTreeBuilder.endElement();
                }
                else {
                    mSubTreeBuilder.addText(item.label);
                }

            }

            if (!mSubTreeBuilder.isEmpty()) {
                mSubTreeItemCount = 0;
                // </div>
                mSubTreeBuilder.endElement();
            }

            mHtmlBuilder.startElement("div")
                        .addClassAttribute("subNavTree")
                        .addStyleAttribute("width:"
                                + (mSubTreeColumns * mMenuWidth) + "px")
                        .addHtmlText(mSubTreeBuilder.getHtml())
                        .endElement();

            mSubTreeColumns = 1;
        }

    }

    private void addSubNavTree2(Folder folder) throws UnsupportedEncodingException {

        List<Item> items = folder.getItems();
        if (!items.isEmpty()) {

            for (Item item : items) {
                addItem(item,
                        "subNavTreeItem");
            }
        }

    }

    private void addItem(Item item, String cssClass) throws UnsupportedEncodingException {

        mSubTreeItemCount++;
        mSubTreeBuilder.startElement("div")
                       .addClassAttribute(cssClass);

        if (mHref != null) {
            String label = URLEncoder.encode(item.label,
                                             "UTF-8");
            
            String qName = item.getQualifiedName();
            
            
            String hRef = getVariableStringValue("application.root") + "/"
                    + mHref;

            
            mSubTreeBuilder.startElement("a")
                           .addAttribute("href",
                                         hRef.replace("#{name}",
                                                       label)
                                              .replace("#{qName}",
                                                       qName))
                           .addText(item.label)
                           .endElement();
        }

        // </div>
        mSubTreeBuilder.endElement();
    }

}

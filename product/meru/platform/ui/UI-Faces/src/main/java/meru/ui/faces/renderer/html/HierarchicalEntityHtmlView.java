package meru.ui.faces.renderer.html;

import java.io.IOException;

import meru.ui.faces.renderer.EntityHierarchy;
import app.entity.Hierarchical;

public abstract class HierarchicalEntityHtmlView extends
        ForEachEntityHtmlView<Hierarchical> {

    protected EntityHierarchy mEntityHierarchy;

    public HierarchicalEntityHtmlView(String entityName,
                                      String filter) {
        super(entityName,
              filter);
        mEntityHierarchy = new EntityHierarchy();
    }

    public HierarchicalEntityHtmlView(String root,
                                      String entityName,
                                      String filter) {
        super(entityName,
              filter);
        Hierarchical hierarchicalEntity = new EntityHierarchy.Root(root);
        mEntityHierarchy = new EntityHierarchy(hierarchicalEntity);
    }

    @Override
    protected void writeNoEntity() throws IOException {

    }

    @Override
    protected void writeEntity(Hierarchical hierarchical) throws IOException {
        mEntityHierarchy.addHierarchicalEntity(hierarchical);
    }
}

package meru.ui.faces.tag.registry;

import meru.ui.faces.tag.component.UIComponent;
import meru.ui.faces.tag.component.UIEntity;
import meru.ui.faces.tag.component.UIInclude;
import meru.ui.faces.tag.component.UIVariable;
import meru.ui.faces.tag.component.dialog.Dialog;
import meru.ui.faces.tag.component.form.EntityForm;
import meru.ui.faces.tag.component.grid.EntityGrid;
import meru.ui.faces.tag.component.grid.StackedEntityGrid;
import meru.ui.faces.tag.component.image.GraphicImage;
import meru.ui.faces.tag.component.image.ImageGallery;
import meru.ui.faces.tag.component.image.ImageScroller;
import meru.ui.faces.tag.component.input.InputDate;
import meru.ui.faces.tag.component.input.InputDecimal;
import meru.ui.faces.tag.component.input.InputFile;
import meru.ui.faces.tag.component.input.InputHidden;
import meru.ui.faces.tag.component.input.InputInt;
import meru.ui.faces.tag.component.input.InputSecret;
import meru.ui.faces.tag.component.input.InputText;
import meru.ui.faces.tag.component.input.InputTextarea;
import meru.ui.faces.tag.component.input.button.CommandButton;
import meru.ui.faces.tag.component.input.select.SelectBooleanCheckBox;
import meru.ui.faces.tag.component.input.select.SelectOneEntityMenu;
import meru.ui.faces.tag.component.input.select.SelectOneEntityPopup;
import meru.ui.faces.tag.component.list.EntityList;
import meru.ui.faces.tag.component.menu.EntityMenu;
import meru.ui.faces.tag.component.message.Message;
import meru.ui.faces.tag.component.other.BreadCrumb;
import meru.ui.faces.tag.component.output.OutputLink;
import meru.ui.faces.tag.component.output.OutputText;
import meru.ui.faces.tag.component.pane.split.SplitPane;
import meru.ui.faces.tag.component.pane.split.Splitter;
import meru.ui.faces.tag.component.panel.PanelGrid;
import meru.ui.faces.tag.component.panel.PanelGridSection;
import meru.ui.faces.tag.component.panel.PanelGroup;
import meru.ui.faces.tag.component.popup.Popup;
import meru.ui.faces.tag.component.popup.PopupLink;
import meru.ui.faces.tag.component.social.fb.FBComments;
import meru.ui.faces.tag.component.social.fb.FBLogin;
import meru.ui.faces.tag.component.social.google.GoogleLogin;
import meru.ui.faces.tag.component.social.google.GoogleMap;
import meru.ui.faces.tag.component.tab.Tab;
import meru.ui.faces.tag.component.tab.Tabs;
import meru.ui.faces.tag.component.table.Column;
import meru.ui.faces.tag.component.table.Columns;
import meru.ui.faces.tag.component.table.DynamicColumns;
import meru.ui.faces.tag.component.table.EntityColumns;
import meru.ui.faces.tag.component.table.EntityFilter;
import meru.ui.faces.tag.component.table.EntityFilters;
import meru.ui.faces.tag.component.table.EntityTable;
import meru.ui.faces.tag.component.template.Parameter;
import meru.ui.faces.tag.component.template.Template;
import meru.ui.faces.tag.component.toolbar.Toolbar;
import meru.ui.faces.tag.component.toolbar.ToolbarCommand;
import meru.ui.faces.tag.component.toolbar.ToolbarSeparator;
import meru.ui.faces.tag.component.tree.EntityTree;
import meru.ui.faces.tag.component.tree.NavEntityTree;
import meru.ui.faces.tag.component.wizard.Wizard;
import meru.ui.faces.tag.component.wizard.WizardItem;

public class UIComponentRegistry extends UITagRegistry<UIComponent> {

    protected UIComponentRegistry(String namespace) {
        super(namespace);
    }

    public UIComponentRegistry() {

        super(UIComponent.NAMESPACE);

        addTagClass(InputHidden.NAME,
                    InputHidden.class);
        addTagClass(InputText.NAME,
                    InputText.class);
        addTagClass(InputSecret.NAME,
                    InputSecret.class);
        addTagClass(InputInt.NAME,
                    InputInt.class);
        addTagClass(InputDecimal.NAME,
                    InputDecimal.class);
        addTagClass(InputDate.NAME,
                    InputDate.class);
        addTagClass(InputFile.NAME,
                    InputFile.class);      
        addTagClass(InputTextarea.NAME,
                    InputTextarea.class);

        addTagClass(SelectBooleanCheckBox.NAME,
                    SelectBooleanCheckBox.class);

        addTagClass(SelectOneEntityMenu.NAME,
                    SelectOneEntityMenu.class);
        addTagClass(SelectOneEntityPopup.NAME,
                    SelectOneEntityPopup.class);

        addTagClass(OutputText.NAME,
                    OutputText.class);
        addTagClass(OutputLink.NAME,
                    OutputLink.class);

        addTagClass(CommandButton.NAME,
                    CommandButton.class);

        addTagClass(Message.NAME,
                    Message.class);

        addTagClass(Template.NAME,
                    Template.class);
        addTagClass(Parameter.NAME,
                    Parameter.class);

        addTagClass(UIVariable.NAME,
                    UIVariable.class);
        addTagClass(UIInclude.NAME,
                    UIInclude.class);

        addTagClass(UIEntity.NAME,
                    UIEntity.class);
        
        addTagClass(PanelGroup.NAME,
                    PanelGroup.class);
        addTagClass(PanelGrid.NAME,
                    PanelGrid.class);
        addTagClass(PanelGridSection.NAME,
                    PanelGridSection.class);

        addTagClass(EntityList.NAME,
                    EntityList.class);
        addTagClass(EntityMenu.NAME,
                    EntityMenu.class);

        addTagClass(EntityForm.NAME,
                    EntityForm.class);

        addTagClass(EntityGrid.NAME,
                    EntityGrid.class);
        addTagClass(StackedEntityGrid.NAME,
                    StackedEntityGrid.class);

        addTagClass(EntityTable.NAME,
                    EntityTable.class);
        addTagClass(Columns.NAME,
                    Columns.class);
        addTagClass(Column.NAME,
                    Column.class);
        addTagClass(EntityColumns.NAME,
                    EntityColumns.class);
        addTagClass(DynamicColumns.NAME,
                    DynamicColumns.class);
        addTagClass(EntityFilters.NAME,
                    EntityFilters.class);
        addTagClass(EntityFilter.NAME,
                    EntityFilter.class);

        addTagClass(Toolbar.NAME,
                    Toolbar.class);
        addTagClass(ToolbarCommand.NAME,
                    ToolbarCommand.class);
        addTagClass(ToolbarSeparator.NAME,
                    ToolbarSeparator.class);

        addTagClass(EntityTree.NAME,
                    EntityTree.class);
        addTagClass(NavEntityTree.NAME,
                    NavEntityTree.class);

        addTagClass(SplitPane.NAME,
                    SplitPane.class);
        addTagClass(Splitter.NAME,
                    Splitter.class);

        addTagClass(Tabs.NAME,
                    Tabs.class);
        addTagClass(Tab.NAME,
                    Tab.class);
        
        addTagClass(Wizard.NAME,
                    Wizard.class);
        addTagClass(WizardItem.NAME,
                    WizardItem.class);

        addTagClass(Dialog.NAME,
                    Dialog.class);
        addTagClass(Popup.NAME,
                    Popup.class);
        addTagClass(PopupLink.NAME,
                    PopupLink.class);

        addTagClass(GraphicImage.NAME,
                    GraphicImage.class);
        addTagClass(ImageGallery.NAME,
                    ImageGallery.class);
        addTagClass(ImageScroller.NAME,
                    ImageScroller.class);


        addTagClass(BreadCrumb.NAME,
                    BreadCrumb.class);

        addTagClass(FBComments.NAME,
                    FBComments.class);
        addTagClass(FBLogin.NAME,
                    FBLogin.class);
        addTagClass(GoogleLogin.NAME,
                    GoogleLogin.class);
        addTagClass(GoogleMap.NAME,
                    GoogleMap.class);

    }
}

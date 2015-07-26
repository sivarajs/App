package meru.ui.faces.renderer.html.registry;

import meru.ui.faces.renderer.html.component.HtmlEntityRenderer;
import meru.ui.faces.renderer.html.component.HtmlIncludeRenderer;
import meru.ui.faces.renderer.html.component.HtmlVariableRenderer;
import meru.ui.faces.renderer.html.component.dialog.HtmlDialogRenderer;
import meru.ui.faces.renderer.html.component.form.HtmlEntityFormRenderer;
import meru.ui.faces.renderer.html.component.grid.HtmlEntityGridRenderer;
import meru.ui.faces.renderer.html.component.grid.HtmlStackedEntityGridRenderer;
import meru.ui.faces.renderer.html.component.image.HtmlGraphicImageRenderer;
import meru.ui.faces.renderer.html.component.image.HtmlImageGalleryRenderer;
import meru.ui.faces.renderer.html.component.image.HtmlImageScrollerRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputDateRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputDecimalRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputFileRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputHiddenRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputIntRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputSecretRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputTextRenderer;
import meru.ui.faces.renderer.html.component.input.HtmlInputTextareaRenderer;
import meru.ui.faces.renderer.html.component.input.button.HtmlCommandButtonRenderer;
import meru.ui.faces.renderer.html.component.input.select.HtmlSelectBooleanCheckBoxRenderer;
import meru.ui.faces.renderer.html.component.input.select.HtmlSelectOneEntityMenuRenderer;
import meru.ui.faces.renderer.html.component.input.select.HtmlSelectOneEntityPopupRenderer;
import meru.ui.faces.renderer.html.component.list.HtmlEntityListRenderer;
import meru.ui.faces.renderer.html.component.menu.HtmlEntityMenuRenderer;
import meru.ui.faces.renderer.html.component.message.HtmlMessageRenderer;
import meru.ui.faces.renderer.html.component.other.HtmlBreadCrumbRenderer;
import meru.ui.faces.renderer.html.component.output.HtmlOutputLinkRenderer;
import meru.ui.faces.renderer.html.component.output.HtmlOutputTextRenderer;
import meru.ui.faces.renderer.html.component.pane.split.HtmlSplitPaneRenderer;
import meru.ui.faces.renderer.html.component.pane.split.HtmlSplitterRenderer;
import meru.ui.faces.renderer.html.component.panel.HtmlPanelGridRenderer;
import meru.ui.faces.renderer.html.component.panel.HtmlPanelGridSectionRenderer;
import meru.ui.faces.renderer.html.component.panel.HtmlPanelGroupRenderer;
import meru.ui.faces.renderer.html.component.popup.HtmlPopupLinkRenderer;
import meru.ui.faces.renderer.html.component.popup.HtmlPopupRenderer;
import meru.ui.faces.renderer.html.component.social.fb.HtmlFBCommentsRenderer;
import meru.ui.faces.renderer.html.component.social.fb.HtmlFBLoginRenderer;
import meru.ui.faces.renderer.html.component.social.google.HtmlGoogleLoginRenderer;
import meru.ui.faces.renderer.html.component.social.google.HtmlGoogleMapRenderer;
import meru.ui.faces.renderer.html.component.tab.HtmlTabRenderer;
import meru.ui.faces.renderer.html.component.tab.HtmlTabsRenderer;
import meru.ui.faces.renderer.html.component.table.HtmlColumnRenderer;
import meru.ui.faces.renderer.html.component.table.HtmlColumnsRenderer;
import meru.ui.faces.renderer.html.component.table.HtmlDynamicColumnsRenderer;
import meru.ui.faces.renderer.html.component.table.HtmlEntityColumnsRenderer;
import meru.ui.faces.renderer.html.component.table.HtmlEntityTableRenderer;
import meru.ui.faces.renderer.html.component.template.HtmlParameterRenderer;
import meru.ui.faces.renderer.html.component.template.HtmlTemplateRenderer;
import meru.ui.faces.renderer.html.component.toolbar.HtmlToolbarCommandRenderer;
import meru.ui.faces.renderer.html.component.toolbar.HtmlToolbarRenderer;
import meru.ui.faces.renderer.html.component.toolbar.HtmlToolbarSeparatorRenderer;
import meru.ui.faces.renderer.html.component.tree.HtmlEntityTreeRenderer;
import meru.ui.faces.renderer.html.component.tree.HtmlNavEntityTreeRenderer;
import meru.ui.faces.renderer.html.component.wizard.HtmlWizardItemRenderer;
import meru.ui.faces.renderer.html.component.wizard.HtmlWizardRenderer;
import meru.ui.faces.renderer.registry.UIComponentRendererRegistry;
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

public class HtmlComponentRendererRegistry extends UIComponentRendererRegistry {

    public HtmlComponentRendererRegistry() {
        super(UIComponent.NAMESPACE);

        mBuilders.put(InputHidden.NAME,
                      HtmlInputHiddenRenderer.class);
        mBuilders.put(InputText.NAME,
                      HtmlInputTextRenderer.class);
        mBuilders.put(InputSecret.NAME,
                      HtmlInputSecretRenderer.class);
        mBuilders.put(InputInt.NAME,
                      HtmlInputIntRenderer.class);
        mBuilders.put(InputDecimal.NAME,
                      HtmlInputDecimalRenderer.class);
        mBuilders.put(InputDate.NAME,
                      HtmlInputDateRenderer.class);
        mBuilders.put(InputFile.NAME,
                      HtmlInputFileRenderer.class);
        mBuilders.put(InputTextarea.NAME,
                      HtmlInputTextareaRenderer.class);

        mBuilders.put(SelectBooleanCheckBox.NAME,
                      HtmlSelectBooleanCheckBoxRenderer.class);

        mBuilders.put(SelectOneEntityMenu.NAME,
                      HtmlSelectOneEntityMenuRenderer.class);
        mBuilders.put(SelectOneEntityPopup.NAME,
                      HtmlSelectOneEntityPopupRenderer.class);

        mBuilders.put(OutputText.NAME,
                      HtmlOutputTextRenderer.class);
        mBuilders.put(OutputLink.NAME,
                      HtmlOutputLinkRenderer.class);

        mBuilders.put(CommandButton.NAME,
                      HtmlCommandButtonRenderer.class);

        mBuilders.put(Message.NAME,
                      HtmlMessageRenderer.class);

        mBuilders.put(Template.NAME,
                      HtmlTemplateRenderer.class);
        mBuilders.put(Parameter.NAME,
                      HtmlParameterRenderer.class);
        mBuilders.put(UIVariable.NAME,
                      HtmlVariableRenderer.class);
        mBuilders.put(UIInclude.NAME,
                      HtmlIncludeRenderer.class);

        mBuilders.put(PanelGroup.NAME,
                      HtmlPanelGroupRenderer.class);
        mBuilders.put(PanelGrid.NAME,
                      HtmlPanelGridRenderer.class);
        mBuilders.put(PanelGridSection.NAME,
                      HtmlPanelGridSectionRenderer.class);

        mBuilders.put(UIEntity.NAME,
                      HtmlEntityRenderer.class);
        
        mBuilders.put(EntityList.NAME,
                      HtmlEntityListRenderer.class);
        mBuilders.put(EntityMenu.NAME,
                      HtmlEntityMenuRenderer.class);

        mBuilders.put(EntityGrid.NAME,
                      HtmlEntityGridRenderer.class);
        mBuilders.put(StackedEntityGrid.NAME,
                      HtmlStackedEntityGridRenderer.class);

        mBuilders.put(EntityTable.NAME,
                      HtmlEntityTableRenderer.class);
        mBuilders.put(Columns.NAME,
                      HtmlColumnsRenderer.class);
        mBuilders.put(Column.NAME,
                      HtmlColumnRenderer.class);
        mBuilders.put(EntityColumns.NAME,
                      HtmlEntityColumnsRenderer.class);
        mBuilders.put(DynamicColumns.NAME,
                      HtmlDynamicColumnsRenderer.class);

        mBuilders.put(Toolbar.NAME,
                      HtmlToolbarRenderer.class);
        mBuilders.put(ToolbarCommand.NAME,
                      HtmlToolbarCommandRenderer.class);
        mBuilders.put(ToolbarSeparator.NAME,
                      HtmlToolbarSeparatorRenderer.class);

        mBuilders.put(EntityForm.NAME,
                      HtmlEntityFormRenderer.class);

        mBuilders.put(EntityTree.NAME,
                      HtmlEntityTreeRenderer.class);
        mBuilders.put(NavEntityTree.NAME,
                      HtmlNavEntityTreeRenderer.class);

        mBuilders.put(SplitPane.NAME,
                      HtmlSplitPaneRenderer.class);
        mBuilders.put(Splitter.NAME,
                      HtmlSplitterRenderer.class);

        mBuilders.put(Tabs.NAME,
                      HtmlTabsRenderer.class);
        mBuilders.put(Tab.NAME,
                      HtmlTabRenderer.class);
        
        mBuilders.put(Wizard.NAME,
                      HtmlWizardRenderer.class);
        mBuilders.put(WizardItem.NAME,
                      HtmlWizardItemRenderer.class);

        mBuilders.put(Dialog.NAME,
                      HtmlDialogRenderer.class);
        mBuilders.put(Popup.NAME,
                      HtmlPopupRenderer.class);
        mBuilders.put(PopupLink.NAME,
                      HtmlPopupLinkRenderer.class);

        mBuilders.put(GraphicImage.NAME,
                      HtmlGraphicImageRenderer.class);
        mBuilders.put(ImageGallery.NAME,
                      HtmlImageGalleryRenderer.class);
        
        mBuilders.put(ImageScroller.NAME,
                      HtmlImageScrollerRenderer.class);
        
        mBuilders.put(BreadCrumb.NAME,
                      HtmlBreadCrumbRenderer.class);

        mBuilders.put(FBComments.NAME,
                      HtmlFBCommentsRenderer.class);
        mBuilders.put(FBLogin.NAME,
                      HtmlFBLoginRenderer.class);
        mBuilders.put(GoogleLogin.NAME,
                      HtmlGoogleLoginRenderer.class);
        mBuilders.put(GoogleMap.NAME,
                      HtmlGoogleMapRenderer.class);

    }

}

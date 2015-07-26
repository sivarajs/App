package meru.ui.faces.renderer.html.page;

import meru.ui.faces.tag.page.Section;

public class HtmlSectionRenderer extends HtmlFragmentRenderer<Section> {

//    private SectionName sectionName;
//    private HtmlFragmentRenderer<Section> fragmentRenderer;

    @Override
    protected boolean preRender() {

//        sectionName = SectionName.getSectionName(uiComponent.getName());
//        switch (sectionName) {
//            case WORK_AREA:
//                uiComponent.setCSSClass(sectionName.getName());
//                fragmentRenderer = new HtmlSectionWorkAreaRenderer();
//                break;
//            case CONTENT_AREA:
//                break;
//            case NAV_AREA:
//                break;
//            default:
//                break;
//
//        }
//
//        fragmentRenderer.render(uiComponent,
//                                viewContext);
//        
        return true;
    }

    @Override
    protected boolean preRenderChildren() {

        

        return true;
    }

    @Override
    protected void postRenderChildren() {

    }
}

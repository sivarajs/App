package meru.ui.faces.tag.page;


public class Section extends UIFragment {

    public static final String NAME = "section";

    
    public static enum SectionName {
        WORK_AREA("workArea"),
        NAV_AREA("navArea"),
        CONTENT_AREA("contentArea");
        
        private String name;
        private SectionName(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public static SectionName getSectionName(String name) {
            for (SectionName sectionName : values()) {
                if (sectionName.name.equals(name)) {
                    return sectionName;
                }
            }
            
            throw new RuntimeException("Unknown section name : "+name);
        }
    }
    
    public Section() {
        super(NAME);
    }
    
    public String getName() {
        return getMandatoryAttribute("name");
    }

}

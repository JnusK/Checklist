package highlighter.checklistapp.entity;

/**
 * Created by Joseph on 10/24/2017.
 */

public class ChecklistItem {
    private int checklist_item_id;
    private int checklist_id;
    private String description;
    private int serviceability;

    public void setChecklistID(int checklist_id) {
        this.checklist_item_id = checklist_id;
    }

    public void setID(int id){
        this.checklist_id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setServiceability(int serviceability){
        this.serviceability = serviceability;
    }

    public int getChecklistItemID() {
        return checklist_item_id;
    }

    public int getChecklistID(){
        return checklist_id;
    }

    public String getDescription(){
        return description;
    }

    public int getServiceability(){
        return serviceability;
    }

    public ChecklistItem(int checklist_item_id, int id, String description, int serviceability){
        this.checklist_item_id = checklist_item_id;
        this.checklist_id = id;
        this.description = description;
        this.serviceability = serviceability;
    }
}

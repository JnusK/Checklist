package highlighter.checklistapp.entity;

/**
 * Created by Joseph on 10/24/2017.
 */

public class ChecklistItem {
    private int checklist_id;
    private int id;
    private String description;
    private int serviceability;

    public void setChecklistID(int checklist_id) {
        this.checklist_id = checklist_id;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setServiceability(int serviceability){
        this.serviceability = serviceability;
    }

    public int getChecklistID() {
        return checklist_id;
    }

    public int getId(){
        return id;
    }

    public String getDescription(){
        return description;
    }

    public int getServiceability(){
        return serviceability;
    }

    public ChecklistItem(int checklist_id, int id, String description, int serviceability){
        this.checklist_id = checklist_id;
        this.id = id;
        this.description = description;
        this.serviceability = serviceability;
    }
}

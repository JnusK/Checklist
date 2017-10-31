package highlighter.checklistapp.entity;

/**
 * Created by Joseph on 10/24/2017.
 */

public class ChecklistItem {
    private int id;
    private String description;
    private int serviceability;

    public void setID(int id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setServiceability(int serviceability){
        this.serviceability = serviceability;
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

    public ChecklistItem(int id, String description, int serviceability){
        this.id = id;
        this.description = description;
        this.serviceability = serviceability;
    }
}

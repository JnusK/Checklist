package highlighter.checklistapp.entity;

import java.util.ArrayList;

/**
 * Created by Joseph on 10/24/2017.
 */

public class Checklist {

    private String name;
    private int id;
    private int date_added;
    private String frequency;
    private ArrayList<ChecklistItem> checklistItems;
    private int last_edit_date;
    private int last_edit_user_id;
    private boolean is_archive = true;


    public void setLast_edit_date(int last_edit_date) {
        this.last_edit_date = last_edit_date;
    }

    public void setLast_edit_user_id(int last_edit_user_id) {
        this.last_edit_user_id = last_edit_user_id;
    }

    public void setIs_archive(boolean is_archive) {
        this.is_archive = is_archive;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setDateAdded(int date_added){
        this.date_added = date_added;
    }

    public void setFrequency(String frequency){
        this.frequency = frequency;
    }

    public void setchecklistItems(ArrayList<ChecklistItem> content){
        this.checklistItems = content;
    }

    public String getName(){
        return name;
    }

    public boolean getIs_archive(){ return this.is_archive; }

    public int getLast_edit_date() {
        return last_edit_date;
    }

    public int getLast_edit_user_id() {
        return last_edit_user_id;
    }

    public int getID(){
        return id;
    }

    public int getDateAdded(){
        return  date_added;
    }

    public String getFrequency(){
        return frequency;
    }

    public ArrayList<ChecklistItem> getchecklistItems(){
        return checklistItems;
    }

    public void addChecklistItem(ChecklistItem item){
        this.checklistItems.add(item);
    }

    public void setChecklistItemDescription(int id, String description){
        this.checklistItems.get(id).setDescription(description);
    }

    public void setChecklistItemServiceability(int id, String serviceability){
        this.checklistItems.get(id).setDescription(serviceability);
    }

    public int getServiceability(int id){
        return this.checklistItems.get(id).getServiceability();
    }


    public Checklist(String name, int id, int date_added, String frequency, ArrayList<ChecklistItem> checklistItems){
        this.name = name;
        this.id = id;
        this.date_added = date_added;
        this.frequency = frequency;
        this.checklistItems = checklistItems;
    }

}

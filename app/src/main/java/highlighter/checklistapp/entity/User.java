package highlighter.checklistapp.entity;

/**
 * Created by Joseph on 10/24/2017.
 */

public class User {

    private int id;
    private String password;
    private int type;
    private int date_added;

    public User(int id, String password, int type, int date_added){
        this.id = id;
        this.password = password;
        this.type = type;
        this.date_added = date_added;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setDateAdded(int date_added){
        this.date_added = date_added;
    }

    public int getID(){
        return id;
    }

    public String getPassword(){
        return password;
    }

    public int getType(){
        return type;
    }

    public int getDateAdded(){
        return  date_added;
    }

}


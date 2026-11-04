package models;

import java.util.UUID;

public class Persona {
    public String name;
    private String country ;
    private String id;
    public boolean is_alive = true;

    public String getCountry(){
        return country;
    }

    public void setCountry(String newCountry){
        this.country=newCountry;
    }

    @Override
    public String toString() {
        return "models.Persona{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", is_alive=" + is_alive +
                '}';
    }

    public String getName(){
        return name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setId(){
        this.id = UUID.randomUUID().toString().substring(0, 4);
    }

    public String getId(){
        return id;
    }

    public void setName(String newName){
        this.name=newName;
    }
}

package fvtc.edu.fileiodemo;

public class Actor {
    private int Id;
    private String FirstName;
    private String LastName;

    public  Actor(int id, String firstName, String lastName) {
        Id = id;
        this.FirstName = firstName;
        LastName = lastName;
    }
    public String toString(){ return  Id + "|" + FirstName + "|" + LastName;}
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}

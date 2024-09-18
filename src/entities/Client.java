package entities;

public class Client {
    private int id;
    private String name;
    private String telephone;
    private boolean estProfessionel;

    public Client(int id, String name, String telephone, boolean estProfessionel) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.estProfessionel = estProfessionel;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getTelephone(){
        return telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public boolean isEstProfessionel(){
        return estProfessionel;
    }
    public void setEstProfessionel(boolean estProfessionel){
        this.estProfessionel = estProfessionel;
    }

}

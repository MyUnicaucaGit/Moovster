package edu.unicauca.moovster.role;

public class Rol {
    private  String rolName;
    private  String rolTitle;
    private String rolDescription;

    public Rol(String roleName, String roleTitle, String roleDescription) {
        this.rolName = roleName;
        this.rolTitle = roleTitle;
        this.rolDescription = roleDescription;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getRolTitle() {
        return rolTitle;
    }

    public void setRolTitle(String rolTitle) {
        this.rolTitle = rolTitle;
    }

    public String getRolDescription() {
        return rolDescription;
    }

    public void setRolDescription(String rolDescription) {
        this.rolDescription = rolDescription;
    }
}

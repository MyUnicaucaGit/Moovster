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

    public String getRoleName() {
        return rolName;
    }

    public void setRoleName(String roleName) {
        this.rolName = roleName;
    }

    public String getRoleTitle() {
        return rolTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.rolTitle = roleTitle;
    }

    public String getRoleDescription() {
        return rolDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.rolDescription = roleDescription;
    }
}

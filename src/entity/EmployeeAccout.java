package entity;

public class EmployeeAccout {

    private final String username;
    private final Integer roleId;
    private final boolean hasAccount;

    public EmployeeAccout(String username, Integer roleId, boolean hasAccount) {
        this.username = username;
        this.roleId = roleId;
        this.hasAccount = hasAccount;
    }

    public String getUsername() {
        return username;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public boolean hasAccount() {
        return hasAccount;
    }
}

package com.product.model;

public class ptRole {
    private Integer roleKey;

    private String roleCode;

    private String roleName;

    private Integer roleType;

    private Integer disable;

    public Integer getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(Integer roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }
}
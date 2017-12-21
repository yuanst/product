package com.product.model;

public class ptPermission {
    private Integer permissionKey;

    private String resource;

    private String permissionCode;

    private String permissionName;

    private Integer parentId;

    private Integer disable;

    public Integer getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(Integer permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource == null ? null : resource.trim();
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }
}
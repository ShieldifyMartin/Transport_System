package org.example.dto;

public class StaffCompanyDTO {
    private Long staffId;
    private String staffName;
    private long companyId;
    private String companyName;

    public StaffCompanyDTO(long staffId, String staffName, long companyId, String companyName) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "StaffCompanyDto{" +
            "staffId=" + staffId +
            ", staffName='" + staffName + '\'' +
            ", companyId=" + companyId +
            ", companyName='" + companyName + '\'' +
            '}';
    }
}

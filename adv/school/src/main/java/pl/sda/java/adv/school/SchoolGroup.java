package pl.sda.java.adv.school;

public class SchoolGroup {
    private byte schoolYear;
    private char classCode;

    public SchoolGroup(byte schoolYear, char classCode) {
        this.schoolYear = schoolYear;
        this.classCode = classCode;
    }

    public byte getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(byte schoolYear) {
        this.schoolYear = schoolYear;
    }

    public char getClassCode() {
        return classCode;
    }

    public void setClassCode(char classCode) {
        this.classCode = classCode;
    }
}

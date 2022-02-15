package pl.sda.java.adv.school;

public final class SchoolGroup {
    private final byte schoolYear;
    private final char classCode;

    public SchoolGroup(byte schoolYear, char classCode) {
        this.schoolYear = schoolYear;
        this.classCode = classCode;
    }

    public byte getSchoolYear() {
        return schoolYear;
    }

    public char getClassCode() {
        return classCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolGroup that = (SchoolGroup) o;

        if (schoolYear != that.schoolYear) return false;
        return classCode == that.classCode;
    }

    @Override
    public int hashCode() {
        int result = schoolYear;
        result = 31 * result + (int) classCode;
        return result;
    }
}

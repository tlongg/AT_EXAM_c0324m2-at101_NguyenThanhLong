public class Student {
    private int studentId;
    private String fullName;
    private int age;
    private String address;
    private double gpa;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = Integer.parseInt(studentId);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Student() {
    }

    public Student(int studentId, String fullName, int age, String address, double gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Sinh viên " + getStudentId() + " {" +
                "Mã Sinh viên = '" + studentId + '\'' +
                ", Họ tên = '" + fullName + '\'' +
                ", Tuổi  = " + age +
                ", Địa chỉ  = '" + address + '\'' +
                ", GPA = " + gpa +
                '}';
    }
}

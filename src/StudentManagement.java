import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement extends Student {
    private ArrayList<Student> studentList;
    private Scanner scanner = new Scanner(System.in);
    public static final String FILE_NAME = "students.csv";
    public static final String COMMA = ", ";
    FileWriter fileWriter;

    public StudentManagement() {
        studentList = new ArrayList<>();
    }

    private List<Student> readStudentFromFile() {
        List<Student> studentList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> list = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            String[] array = list.toArray(new String[0]);
            for (String lines : array) {
                String[] lineSplit = lines.split(COMMA);
                Student student = new Student(Integer.parseInt(lineSplit[0]), lineSplit[1], Integer.parseInt(lineSplit[2]), lineSplit[3], Double.parseDouble(lineSplit[4]));
                studentList.add(student);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    public void showAllStudents(List<Student> studentList) {
        studentList = readStudentFromFile();
        System.out.println(studentList);
    }

    public boolean isStudentIdExists(int studentId) {
        List<Student> studentList = readStudentFromFile();
        Student student = new Student();
        int exsitingId = student.getStudentId();
        if (exsitingId == studentId) {
            return true;
        } else {

            return false;
        }
    }


    public void addStudent(int studentId, String fullName, int age, String address, double gpa) {
        Student student = new Student(studentId, fullName, age, address, gpa);
        List<Student> studentList = readStudentFromFile();
        String line = null;
        if (isStudentIdExists(studentId)) {
            System.out.println("Mã sinh viên bị trùng lặp");
        } else {
            line = student.getStudentId() + COMMA + student.getFullName() + COMMA + student.getAge() + COMMA + student.getAddress() + COMMA + student.getGpa();
            try {
                fileWriter = new FileWriter(FILE_NAME, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateStudent(int studentId, String newFullName, int newAge, String newAddress, double newGpa) {
        List<Student> studentList = readStudentFromFile();
        for (Student student : studentList) {
            if (!isStudentIdExists(studentId)) {
                System.out.println("Không tìm được sinh viên với mã sinh viên trên.");
            } else {
                if (student.getStudentId() == studentId) {
                    student.setFullName(newFullName);
                    student.setAge(newAge);
                    student.setAddress(newAddress);
                    student.setGpa(newGpa);
                    break;
                }
            }
        }
    }

    public void deleteStudent(int studentId) {
        List<Student> studentList = readStudentFromFile();
        if (!isStudentIdExists(studentId)) {
            System.out.println("Không tìm được sinh viên với mã sinh viên trên.");
        } else {
            studentList.removeIf(student -> student.getStudentId() == studentId);
        }
    }

    public void menu() {
        StudentManagement studentManagement = new StudentManagement();
        int option;
        do {
            System.out.println("\n--CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN--");
            System.out.println("Chọn chức năng theo số");
            System.out.println("1. Xem danh sách sinh viên");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Thoát");
            System.out.println("Chọn chức năng:");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    studentManagement.showAllStudents(studentList);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Nhập mã sinh viên: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhập họ tên sinh viên:");
                    String fullName = scanner.nextLine();
                    System.out.println("Nhập tuổi sinh viên");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhập địa chỉ sinh viên đang ở:");
                    String address = scanner.nextLine();
                    System.out.println("Nhập GPA của sinh viên:");
                    double gpa = scanner.nextDouble();
                    studentManagement.addStudent(studentId, fullName, age, address, gpa);
                    break;
                case 3:
                    System.out.println("Nhập mã sinh viên tìm sinh viên để sửa thông tin:");
                    studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhập họ tên mới của sinh viên:");
                    String newFullName = scanner.nextLine();
                    System.out.println("Nhập tuổi mới của sinh viên");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nhập địa chỉ mới của sinh viên đang ở:");
                    String newAddress = scanner.nextLine();
                    System.out.println("Nhập GPA mới của sinh viên:");
                    double newGpa = scanner.nextDouble();
                    studentManagement.updateStudent(studentId, newFullName, newAge, newAddress, newGpa);
                    break;
                case 4:
                    System.out.println("Nhập mã sinh viên để xóa");
                    studentId = scanner.nextInt();
                    studentManagement.deleteStudent(studentId);
            }
        } while (option != 5);
    }
}

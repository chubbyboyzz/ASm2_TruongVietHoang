public class Student {
    private String code;
    private String name;
    private int age;
    private String gender;
    private String address;
    private float grade;

    public Student(String code, String name, int age, String gender, String address, float grade) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.grade = grade;
    }

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public float getGrade() { return grade; }
    public void setGrade(float grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", grade=" + grade +
                '}';
    }

    // Chuyển đổi đối tượng Student thành chuỗi
    public String toFileString() {
        return code + "," + name + "," + age + "," + gender + "," + address + "," + grade;
    }

    // Chuyển đổi chuỗi thành đối tượng Student
    public static Student fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        String code = parts[0];
        String name = parts[1];
        int age = Integer.parseInt(parts[2]);
        String gender = parts[3];
        String address = parts[4];
        float grade = Float.parseFloat(parts[5]);
        return new Student(code, name, age, gender, address, grade);
    }
}

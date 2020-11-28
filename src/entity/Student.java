package entity;

/**
 * @auther：albert.fang
 * @date：2020/11/28 11:58
 * @description：学生实体类，用于测试使用
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private String collegeName;
    private int collegeId;

    public Student(int id, String name, int collegeId) {
        this.id = id;
        this.name = name;
        this.collegeId = collegeId;
    }

    public Student(int id, String name, int age, String collegeName, int collegeId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.collegeName = collegeName;
        this.collegeId = collegeId;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}

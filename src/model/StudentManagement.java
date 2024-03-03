package model;


import data.FileManagement;
import view.Validation;
import java.util.*;


public class StudentManagement {
    private final ArrayList<BizStudents> listBizStudent = new ArrayList<>();
    private final ArrayList<ITStudents> listItStudent = new ArrayList<>();
    private final ArrayList<Student> listStudent = new ArrayList<>();
    private final HashMap<String, Double> gpaListStudent = new HashMap<>();
    private final Validation val;
    private FileManagement f = new FileManagement();
    Scanner sc = new Scanner(System.in);
    public StudentManagement(){
        val = new Validation();
        f.loadStudentData(listItStudent, listBizStudent, listStudent);

    }
    public boolean isEmpty(){
        return listStudent.isEmpty();
    }

    //get address
    public Address enterAddress(){
        String country = val.getAndCheckValue("Enter your country", "Name of country just contain letters");
        String city = val.getAndCheckValue("Enter your city", "Name of city just contain letters");
        String district = val.getAndCheckDistrictValue("Enter your district", "Name of district just contain letters and numbers");
        String street = val.getAndCheckDistrictValue("Enter your street", "Name of street just contain letters and numbers");

        return new Address(country, city, district, street);
    }

    //add student
    public Boolean addStudent() {
        String id = val.getAndCheckIDValue("Enter ID:");
        String fullName = val.getAndCheckValue("Enter student's full name: ", "Name just contains letters!");
        Address address = enterAddress();
        String major = val.getAndCheckMajorValue("Enter Student's major(IT/Biz):");
        if (major.equalsIgnoreCase("Biz")) {
            BizStudents biz;
            double accountingScore = val.getAndValidScore("Enter Student's accounting score:");
            double marketingScore = val.getAndValidScore("Enter Student's marketing score");
            biz = new BizStudents(id, fullName, address, accountingScore, marketingScore);
            listBizStudent.add(biz);
            listStudent.add(biz);
            return true;
        }
        if (major.equalsIgnoreCase("IT")) {
            ITStudents it;
            double javaScore = val.getAndValidScore("Enter Student's Java Score");
            double cssScore = val.getAndValidScore("Enter Student's CSS Score");
            it = new ITStudents(id, fullName, address, javaScore, cssScore);
            listItStudent.add(it);
            listStudent.add(it);
            return true;
        }
        return false;
    }

    //print all students:
    public void showEachFacultyStudent(){
        System.out.println("List of Biz Students: ");
        displayFormatBizStudent(listBizStudent);
        System.out.println("List of IT Students: ");
        displayFormatITStudent(listItStudent);
    }

    public void showAllStudent(){
        displayFormatStudent(listStudent);
    }

    //export student including name and gpa
    public void exportList(){
        for(BizStudents bizStd: listBizStudent){
            double gpaBiz = bizStd.calculateAvg();
            gpaListStudent.put(bizStd.getFullName(), gpaBiz);
        }

        for(ITStudents itStd: listItStudent){
            double gpaIT = itStd.calculateAvg();
            gpaListStudent.put(itStd.getFullName(), gpaIT);
        }
        for(Map.Entry<String, Double> entry: gpaListStudent.entrySet()){
            System.out.println("Student's name: " + entry.getKey() + ", GPA: " + entry.getValue());
        }
    }

    //sort list students
    public void sortByName(){
        Collections.sort(listStudent, (s1, s2) -> s1.getFullName().compareTo(s2.getFullName()));
        showAllStudent();
    }

    //find student by id
        public Student findById(String id){
        for(Student student: listStudent){
            if(student.getId().equals(id)){
                System.out.println("Finding successfully!");
                displayFormatEachStudent(student);
                return student;
            }
        }
        System.out.println("Can not find that student");
        return null;
    }

    //count and print out the number of students in the same city of 2 faculties
    public void statisticStdByAddress(){
        HashMap<String, Integer> addressCount = new HashMap<>();
        for(Student student: listStudent){
            String stdAddress = student.getAddress().getCity();
            if(stdAddress != null){
                addressCount.put(stdAddress, addressCount.getOrDefault(stdAddress, 0) + 1);
            }
        }

        for(Map.Entry<String, Integer> entry: addressCount.entrySet()){
            if(entry.getValue() > 1){
                for(Student student: listStudent){
                    if(entry.getKey().equalsIgnoreCase(student.getAddress().getCity())){
                        System.out.println("Student:  " + student.getFullName() + ", Address: " + entry.getKey());
                    }
                }
                System.out.println("Count: " + entry.getValue());
            }
        }
    }

    //update students
    public void updateStudent(Student std){
        String newName = val.getAndCheckValue("Enter new student's name: ", "Name just contain letters");
        Address newAddress = enterAddress();
        std.setFullName(newName);
        std.setAddress(newAddress);
    }

    //delete students
    public void deleteStudent(Student std){
        boolean remove = listStudent.remove(std);
        try{
            if(remove){
                System.out.println("Delete Successfully!");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //total passed subjects of students
    public void report(){
        for(BizStudents bizStd: listBizStudent){
            if(bizStd.calculateAvg() >= 5){
                System.out.println("Student's name: " + bizStd.getFullName() + ", Marketing score: " + bizStd.getMarketingScore() + ", Accounting Score: " + bizStd.getAccountingScore() + ", GPA: " + bizStd.calculateAvg());
            }
        }
        for(ITStudents itStd: listItStudent){
            if(itStd.calculateAvg() >= 5){
                System.out.println("Student's name: " + itStd.getFullName() + ", Java Score: " + itStd.getJavaScore() + ", CSS Score: " + itStd.getCssScore() + ", GPA: " + itStd.calculateAvg());
            }
        }
    }

    public void displayFormatStudent(ArrayList<Student> listStudent){
        String format = "|%-3s | %-10s | %-12s |%n";
        System.out.format("+----+------------+--------------+%n");
        System.out.format("| ID |  Full Name |    Address   |%n");
        System.out.format("+----+------------+--------------+%n");
        for (ITStudents std: listItStudent){
            String id = std.getId();
            String fullName = std.getFullName();
            String adr = std.getAddress().getCity();
            System.out.format(format, id, fullName, adr);
        }
        System.out.format("+----+------------+--------------+%n");
    }
    public void displayFormatEachStudent(Student std){
        String format = "|%-3s | %-10s | %-12s |%n";
        System.out.format("+----+------------+--------------+%n");
        System.out.format("| ID |  Full Name |    Address   |%n");
        System.out.format("+----+------------+--------------+%n");
        String id = std.getId();
        String fullName = std.getFullName();
        String adr = std.getAddress().getCity();
        System.out.format(format, id, fullName, adr);
        System.out.format("+----+------------+--------------+%n");
    }

    public void displayFormatITStudent(ArrayList<ITStudents> listItStudent){
        String format = "|%-3s | %-10s | %-12s | %-10s | %-10s| %n";
        System.out.format("+----+------------+--------------+------------+-----------+%n");
        System.out.format("| ID |  Full Name |    Address   | Java Score | CSS Score | %n");
        System.out.format("+----+------------+--------------+------------+-----------+%n");
        for (ITStudents std: listItStudent){
            String id = std.getId();
            String fullName = std.getFullName();
            String adr = std.getAddress().getCity();
            double javaScore = std.getJavaScore();
            double cssScore = std.getCssScore();
            System.out.format(format, id, fullName, adr, javaScore, cssScore);
        }
        System.out.format("+----+------------+--------------+------------+-----------+%n");
    }

    public void displayFormatBizStudent(ArrayList<BizStudents> listBizStudent){
        String format = "|%-3s | %-10s | %-12s | %-16s | %-16s| %n";
        System.out.format("+----+------------+--------------+------------------+-----------------+%n");
        System.out.format("| ID |  Full Name |    Address   | Accounting Score | Marketing Score | %n");
        System.out.format("+----+------------+--------------+------------------+-----------------+%n");
        for (BizStudents std: listBizStudent){
            String id = std.getId();
            String fullName = std.getFullName();
            String adr = std.getAddress().getCity();
            double accountingScore = std.getAccountingScore();
            double marketingScore = std.getMarketingScore();
            System.out.format(format, id, fullName, adr, accountingScore, marketingScore);
        }
        System.out.format("+----+------------+--------------+------------------+-----------------+%n");
    }

    public static void main(String[] args) {
        StudentManagement std = new StudentManagement();
        std.addStudent();
//        std.showEachFacultyStudent();
//        std.findById("1");
//        std.showAllStudent();
//        std.sortByName();
//        std.exportList();
//        std.statisticStdByAddress();
//        std.report();
    }
}
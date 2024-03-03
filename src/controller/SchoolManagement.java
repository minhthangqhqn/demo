package controller;

import model.Student;
import model.StudentManagement;
import view.Menu;
import view.Validation;

public class SchoolManagement extends Menu<String> {
    private static final String[] menu = {"Add new Student: ", "Export List Students: ", "Sort and Print Students By Name", "Count and print Out Students in the same faculties", "Update Students or Delete Students: ", "Report", "Exit"};
    private static final String menuTitle = "Student Management";
    private final StudentManagement manager;
    private final Validation val;

    public SchoolManagement() {
        super(menuTitle, menu);
        val = new Validation();
        manager = new StudentManagement();
    }

    public static void main(String[] args) {
        SchoolManagement school = new SchoolManagement();
        school.run();
    }
    @Override
    public void execute(int ch) {
        switch(ch){
            case 1:
                if(manager.addStudent()){
                    System.out.println("Adding successfully");
                    manager.showAllStudent();
                }
                else
                    System.out.println("Adding unsuccessfully");
                break;
            case 2:
                manager.exportList();
                break;
            case 3:
                manager.sortByName();
                break;
            case 4:
                manager.statisticStdByAddress();
                break;
            case 5:
                String findId = val.getAndCheckIDValue("Enter Student's ID want to find");
                Student foundStd = manager.findById(findId);
                System.out.println("Do you want to update (U) or delete (D) student");
                String choice = val.getAndValidChoice("Enter your choice: ");
                if(choice.equalsIgnoreCase("U")){
                    manager.updateStudent(foundStd);
                }
                else{
                    manager.deleteStudent(foundStd);
                }
                break;
            case 6:
                manager.report();
                break;
            case 7:
                System.out.println("Thank You!");
                System.exit(0);
        }
    }
}

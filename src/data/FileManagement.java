package data;

import model.Address;
import model.BizStudents;
import model.ITStudents;
import model.Student;

import java.io.*;
import java.util.ArrayList;

public class FileManagement {
    private static final File FILE = new File("src");
    private static final String PATH = FILE.getAbsolutePath();
    private static final String FILE_NAME_STUDENTS = "\\data\\Students.txt";

    public void loadStudentData(ArrayList<ITStudents> listItStudent, ArrayList<BizStudents> listBizStudent, ArrayList<Student> listStudent){
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(PATH + FILE_NAME_STUDENTS));
            while((line = br.readLine()) != null){
                String std[] = line.split(",");
                double subject1 = Double.parseDouble(std[4]);
                double subject2 = Double.parseDouble(std[5]);
                String listAdr[] = std[2].split("-");
                Address adr = new Address(listAdr[0], listAdr[1], listAdr[2], listAdr[3]);
                if(std[3].equalsIgnoreCase("IT")){
                    ITStudents it = new ITStudents(std[0], std[1], adr, subject1, subject2);
                    listItStudent.add(it);
                    listStudent.add(it);
                }
                else{
                    BizStudents biz = new BizStudents(std[0], std[1], adr, subject1, subject2);
                    listBizStudent.add(biz);
                    listStudent.add(biz);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


//    public void writeFruitsIntoFile(ArrayList<ITStudents> listItStudent, ArrayList<BizStudents> listBizStudent) {
//        try (PrintWriter pr = new PrintWriter(PATH + FILE_NAME_STUDENTS);) {
//            for (Student fruit: list) {
//                pr.println(fruit.toString());
//            }
//            pr.close();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
}

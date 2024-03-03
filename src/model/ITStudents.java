package model;

public class ITStudents extends Student {
    private double javaScore;
    private double cssScore;
//    private double avgScore;

    public ITStudents(String id, String fullName, Address address, double javaScore, double cssScore) {
        super(id, fullName, address);
        this.javaScore = javaScore;
        this.cssScore = cssScore;
    }

    public ITStudents(){

    }

    public double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }

    public double getCssScore() {
        return cssScore;
    }

    public void setCssScore(double cssScore) {
        this.cssScore = cssScore;
    }

//    public double getAvgScore(){
//        return avgScore;
//    }


    @Override
    public String toString() {
        return "ITStudents{" +
                super.toString() +
                ", javaScore=" + javaScore +
                ", cssScore=" + cssScore +
                '}';
    }

    @Override
    public double calculateAvg() {
        return (3 * getJavaScore() + getCssScore()) / 4;
    }
}

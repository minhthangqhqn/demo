package model;

public class BizStudents extends Student {
    private double accountingScore;
    private double marketingScore;
//    private double avgScore;

    public BizStudents(String id, String fullName, Address address, double accountingScore, double marketingScore) {
        super(id, fullName, address);
        this.marketingScore = marketingScore;
        this.accountingScore = accountingScore;
    }

    public BizStudents(){

    }

    public double getAccountingScore() {
        return accountingScore;
    }

    public void setAccountingScore(double accountingScore) {
        this.accountingScore = accountingScore;
    }

    public double getMarketingScore() {
        return marketingScore;
    }

    public void setMarketingScore(double marketingScore) {
        this.marketingScore = marketingScore;
    }
//    public double getAvgScore(){
//        return avgScore;
//    }

    @Override
    public String toString() {
        return "BizStudents: " +
                super.toString() +
                ", accountingScore=" + accountingScore +
                ", marketingScore=" + marketingScore +
                '}';
    }

    @Override
    public double calculateAvg() {
        return (getAccountingScore() * 2 + getMarketingScore()) / 3;
    }
}

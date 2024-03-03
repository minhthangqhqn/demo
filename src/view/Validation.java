package view;

import java.util.Scanner;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

    public class Validation {
        private final String stringRegex = "[a-zA-Z]+";
        private final String doubleRegex = ".*\\d+.*";
        private final String intRegex = ".*\\d+.*";
        public String getValue(String msg){
            Scanner sc = new Scanner(System.in);
            System.out.println(msg);
            return sc.nextLine().trim();
        }

        public String getAndCheckDistrictValue(String msg, String cause){
            String value;
            while(true){
                try{
                    value = getValue(msg);
                    if(value.isEmpty()){
                        throw new Exception("Input can not empty");
                    }
                    if(!value.matches(stringRegex) && !value.matches(intRegex)){
                        throw new Exception(cause);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return value;
        }

        public String getAndCheckIDValue(String msg){
            String value;
            while(true){
                try{
                    value = getValue(msg);
                    if(value.isEmpty()){
                        throw new Exception("Input can not empty");
                    }
                    if(!value.matches(stringRegex) && !value.matches(intRegex)){
                        throw new Exception("ID just contains letters and numbers!");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return value;
        }

        public String getAndCheckValue(String msg, String cause){
            String value;
            while(true){
                try{
                    value = getValue(msg);
                    if(value.isEmpty()){
                        throw new Exception("Input can not empty");
                    }
                    if(!value.matches(stringRegex)){
                        throw new Exception(cause);
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return value;
        }
        public String getAndCheckMajorValue(String msg){
            String value;
            while(true){
                try{
                    value = getValue(msg);
                    if(value.isEmpty()){
                        throw new Exception("Input can not empty");
                    }
                    if(!value.equalsIgnoreCase("IT") && !value.equalsIgnoreCase("Biz")){
                        throw new Exception("Please input the right major");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return value;
        }



        public int getAndValidInt(String msg) {
            String input;

            while (true) {
                input = getValue(msg);

                if (!input.matches(intRegex) || Integer.parseInt(input) <= 0) {
                    System.out.println("Invalid input enter again!");
                    continue;
                }
                return Integer.parseInt(input);
            }
        }

        public double getAndValidScore(String msg) {
            String input;

            while (true) {
                input = getValue(msg);

                if (!input.matches(doubleRegex) || (Double.parseDouble(input) < 0 || Double.parseDouble(input) > 10)){
                    System.out.println("Invalid input, please enter again!");
                    continue;
                }
                return  Double.parseDouble(input);
            }
        }

        public String getAndValidChoice(String msg){
            String input;
            while(true){
                input = getValue(msg);
                if(!input.equalsIgnoreCase("U") && !input.equalsIgnoreCase("D")){
                    System.out.println("Invalid input, please enter again!");
                    continue;
                }
                break;
            }
            return input;
        }
    }


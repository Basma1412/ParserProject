//package parserproject;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//class Parts {
//
//    String part1;
//    String part2;
//
//    public Parts(String part1, String part2) {
//        this.part1 = part1;
//        this.part2 = part2;
//    }
//}
//
//class Parser {
//
//    ArrayList<String> tokens;
//    int current_index;
//    String token;
//
//    public Parser(ArrayList<String> tokens) {
//        this.tokens = tokens;
//        this.current_index = 0;
//    }
//
//    public void updateToken() {
////        current_index++;
//    }
//
//    public String getToken() {
//        if (current_index >= tokens.size()) {
//            return "";
//        }
////|| tokens.get(current_index).equals(";")
//        return tokens.get(current_index);
//    }
//
//    public boolean factor() {
//
//        if (getToken().equals("(")) {
//            current_index++;
//            if (exp()) {
////                current_index++;
//                return getToken().equals(")");
//            } else {
//                return false;
//            }
//        } else {
//            return getToken().equals("number") || getToken().equals("identifier");
//        }
//    }
//
//    public boolean mulop() {
//        return getToken().equals("*") || getToken().equals("/");
//    }
////term->termmulopfactor|factor
//    //term->factor term2
//    //term2->mulopfactor|E
//
//    public boolean term() {
//        boolean flag = false;
//        if (factor()) {
//            flag = true;
//        }
//        if (flag) {
//            current_index++;
//            if (term2()) {
//                return true;
//            } else {
//                current_index--;
//                return true;
//            }
//        } else {
//            return flag;
//        }
//    }
//
//    public boolean ret = false;
//
//    public boolean term2() {
//
//        if (mulop()) {
//            current_index++;
//            if (factor()) {
//                current_index++;
//                ret = true;
//                return term2();
//            } else {
//
//                current_index--;
//
//                return false;
//            }
//        } else {
////            return getToken().equals("");
////            if (ret)
////            {
//            current_index--;
////            }
//
//            return true;
//        }
//    }
//
//    public boolean addop() {
//
//        return getToken().equals("+") || getToken().equals("-");
//    }
//
//    public boolean simpleExp() {
//        boolean flag = false;
//        if (term()) {
//            flag = true;
//        }
//
//        if (flag) {
//            current_index++;
//            if (simpleExp2()) {
//                return true;
//            } else {
//                current_index--;
//                return true;
//            }
//
//        } else {
//            return false;
//        }
//    }
//
//    //simpleexp->term simpleexp2
//    //simpleexp2->addop simpleexp2|E
//    boolean flagsE = false;
//
//    public boolean simpleExp2() {
//
//        if (addop()) {
//            current_index++;
//            if (term()) {
//                current_index++;
//                flagsE = true;
//                return simpleExp2();
//            } else {
////                current_index--;
//                return false;
//            }
//        } else {
////            return getToken().equals("");
////            if (flagsE)
////            {
//            current_index--;
////            }
//            return true;
//        }
//
//    }
//
//    public boolean comparisonOp() {
//        String xy = getToken();
//        return xy.equals("<") || xy.equals("=") || xy.equals(">");
//    }
//
//    //exp-> simplexp x
//    //x-> comp simple|siple
//    public boolean exp() {
//        if (simpleExp()) {
//            current_index++;
//            return exp2();
//        } else {
//            return false;
//        }
//    }
//
//    public boolean exp2() {
//        if (comparisonOp()) {
//            current_index++;
//            if (simpleExp()) {
//                current_index++;
//                return exp2();
//            } else {
//                return false;
//            }
//        } else {
//            current_index--;
//            return true;
//        }
//    }
//
//    public boolean writeStmt() {
//
//        if (getToken().equals("write")) {
//            current_index++;
//            return exp();
//        } else {
//            return false;
//        }
//
//    }
//
//    public boolean readStmt() {
//        if (getToken().equals("read")) {
//            current_index++;
//            return (getToken().equals("identifier"));
//        } else {
//            return false;
//        }
//    }
//
//    public boolean assignStmt() {
//
//        if (getToken().equals("identifier")) {
//            current_index++;
//            if (getToken().equals(":")) {
//                current_index++;
//                if (getToken().equals("=")) {
//                    current_index++;
//                    return exp();
//                } else {
//                    current_index--;
//                }
//            } else {
//                current_index--;
//            }
//        }
//        return false;
//    }
//
//    public boolean repeatStmt() {
//
//        if (getToken().equals("repeat")) {
//            current_index++;
//            if (statementSequence()) {
////                current_index++;
//                if (getToken().equals("until")) {
//                    current_index++;
//                    return exp();
//                }
//            }
//
//        }
//        return false;
//
//    }
//
//    public boolean ifStmt() {
//        if (getToken().equals("if")) {
//            current_index++;
//            if (exp()) {
//                current_index++;
//                if (getToken().equals("then")) {
//                    current_index++;
//                    if (statementSequence()) {
////                        current_index++;
//                        return ifStmt2();
//                    } else {
//                        current_index--;
//                    }
//                }
//            } else {
//                current_index--;
//            }
//
//        }
//        return false;
//
//    }
//
//    public boolean ifStmt2() {
//        switch (getToken()) {
//            case "end":
//                return true;
//            case ";":
//                return true;
//            case "else":
//                current_index++;
//                if (statementSequence()) {
//                    current_index++;
//                    return (getToken().equals("end"));
//                }
//                break;
//        }
//        return false;
//    }
//
//    public boolean statement() {
//        if (ifStmt()) {
//            System.out.println("If Statement found");
//            return true;
//
//        } else if (readStmt()) {
//
//            System.out.println("Read Statement found");
//            return true;
//        } else if (repeatStmt()) {
//
//            System.out.println("Repeat Statement found");
//            return true;
//        } else if (writeStmt()) {
//
//            System.out.println("Write Statement found");
//            return true;
//        } else if (assignStmt()) {
//
//            System.out.println("Assign Statement found");
////            current_index--;
//            return true;
//        } else {
//            return false;
//        }
//
////        
////        return ifStmt() || readStmt() || repeatStmt() || writeStmt() || assignStmt();
//    }
//
//    //statement seq2
//    //seq2 ;statement||E
//    public boolean statementSequence() {
//        if (statement()) {
//            current_index++;
//            return statementSequence2();
//        } else {
//            return false;
//        }
//    }
//
//    public boolean statementSequence2() {
//        
//         if ((getToken() == null)||("until".equals(getToken()))||("end".equals(getToken())))
//         {
//             return true;
//         }
//       else if (getToken().equals(";")) {
//            
//           
//            current_index++;
//             if (getToken() == null)
//             {
//                 
//            current_index++;
//             }
//             else 
//             {
//                 return false;
//             }
//            
//             if (statement()) {
//                current_index++;
//                return statementSequence2();
//            } else {
//                return true;
//            }
//        } else {
//            
//            return false;
//        }
//            
////            
////            return (statement());
////        } else {
////            return statement();
////        }
//    }
//
//    
//    
//    
//   
//    public boolean program() {
//        if (statementSequence()) {
//            System.out.println("Program Found");
//            return true;
//        }
//        return false;
//    }
//
//}
//
//
//public class ParserProject {
//
//    public static void main(String[] args) throws FileNotFoundException {
//
//        Scanner s = null;
//        ArrayList<String> data = new ArrayList<>();
//
//        try {
//            s = new Scanner(new BufferedReader(new FileReader("../scanner_output.txt")));
//            while (s.hasNext()) {
//                String inputString = s.next();
//                data.add(inputString);
//            }
//        } catch (Exception e) {
//
//        } finally {
//            if (s != null) {
//                s.close();
//            }
//
//        }
//        ArrayList<Parts> input = new ArrayList<>();
//        int j = 0;
//        for (String data1 : data) {
//
//            String[] parts = data1.split("~", 2);
//            String part1 = parts[0];
//            String part2 = parts[1];
//            input.add(j++, new Parts(part1, part2));
//        }
//
//        ArrayList<String> parsed_data = new ArrayList<>();
//        j = 0;
//        for (Parts input1 : input) {
//            String part1 = input1.part1;
//            String part2 = input1.part2;
//
//            if ("Identifier".equals(part2)) {
//                parsed_data.add(j++, "identifier");
//            } else if ("number".equals(part2)) {
//                parsed_data.add(j++, "number");
//            } else {
//                parsed_data.add(j++, part1);
//            }
//        }
//
//        ArrayList<ArrayList<String>> stats = new ArrayList<>();
//        ArrayList<String> temp = new ArrayList<>();
//        int xx = 0;
//        int xxx = 0;
//
//        for (String input1 : parsed_data) {
//
//            temp.add(xx++, input1);
//            if (input1.equals(";")) {
//                ArrayList<String> temp2 = new ArrayList<>();
//                for (String temp1 : temp) {
//                    temp2.add(temp1);
//                }
//                stats.add(xxx++, temp2);
//                temp.clear();
//                xx = 0;
//            }
//
//        }
//        if (!temp.isEmpty()) {
//            ArrayList<String> temp2 = new ArrayList<>();
//            for (String temp1 : temp) {
//                temp2.add(temp1);
//            }
//            stats.add(xxx++, temp2);
//        }
//
//        ArrayList<String> program = new ArrayList<>();
//        for (ArrayList<String> input1 : stats) {
//            program.addAll(input1);
//        }
////
////        for (String x:program)
////        {
////            System.out.println(x+ " ");
////        }
//        
//        
//        
//        ArrayList<String> fProgram;
//        fProgram = new ArrayList<>();
//        for (String program1 : program) {
//            if (";".equals(program1)||"end".equals(program1))
//            {
//                
//                fProgram.add(program1);
//                fProgram.add(null);
//            }
//            else
//            {
//                
//                fProgram.add(program1);
//            }
//        }
//        
//        Parser current = new Parser(fProgram);
//        current.program();
//
//    }
//}
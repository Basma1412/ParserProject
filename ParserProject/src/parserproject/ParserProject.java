package parserproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

class Parts {

    String part1;
    String part2;

    public Parts(String part1, String part2) {
        this.part1 = part1;
        this.part2 = part2;
    }
}

class Value {

    String id;
    String value;

    public Value(String id, String value) {
        this.id = id;
        this.value = value;
    }
}

class Parser {

    ArrayList<String> tokens;

    ArrayList<String> values;

    ArrayList<Value> results;

    int current_index;
    String token;

    public Parser(ArrayList<String> tokens, ArrayList<String> values) {
        this.tokens = tokens;
        this.current_index = 0;
        this.values = values;
        this.results = new ArrayList<>();
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String getToken() {
        if (current_index >= tokens.size()) {
            return "";
        }
        return tokens.get(current_index);
    }

    public String getValue() {
        if (current_index >= tokens.size()) {
            return "";
        }

        return values.get(current_index);
    }

    public void saveNewIdentifier(String assign) {

        String[] parts = assign.split(":=", 2);
        String part1 = parts[0];
        String part2 = parts[1];
        int res = evaluate(part2);
    }

    public int evaluate(String str) {
        if (str.contains("+")) {
            String[] parts = str.split("+", 2);
            String part1 = parts[0];
            String part2 = parts[1];
        } else if (str.contains("-")) {

            String[] parts = str.split("-", 2);
            String part1 = parts[0];
            String part2 = parts[1];
        } else if (str.contains("*")) {

            String[] parts = str.split("*", 2);
            String part1 = parts[0];
            String part2 = parts[1];

        } else if (str.contains("/")) {

            String[] parts = str.split("/", 2);
            String part1 = parts[0];
            String part2 = parts[1];
        } else {

            String[] parts = str.split("+", 2);
            String part1 = parts[0];
            String part2 = parts[1];
        }
        return 0;
    }

    public String factor() {

        if (getToken().equals("(")) {
            current_index++;
            String expr = exp();
            if (!"".equals(expr)) {
                if (getToken().equals(")")) {
                    return expr;
                } else {

                    return "Error";
                }
            } else {
                return "Error";
            }
        } else {

            String currentToken = getToken();
            String currentValue = getValue();

            if (currentToken.equals("number") || currentToken.equals("identifier")) {
                return currentValue;
            } else {
                return "Error";
            }
        }
    }

    public String mulop() {
        if (getToken().equals("*") || getToken().equals("/")) {
            return getToken();
        } else {
            return "Error";
        }
    }

    public String term() {
        String flag = "Error";
        String fact2 = factor();
        if (!"Error".equals(fact2)) {
            flag = "True";
        }
        if (flag.equals("True")) {
            current_index++;
            String t2 = term2();
            if (!"Error".equals(t2)) {
           
                
                String termret = checkterm(fact2 + "" + t2);
                return termret;
            } else {
                current_index--;
                return "Error";
            }
        } else {
            return flag;
        }
    }

    public boolean ret = false;

    
    
     public String checkterm(String str) {
        if (str.contains("*")) {

            String[] parts = str.split("\\*", 2);
            String part1 = parts[0];
            String part2 = parts[1];

            if (!isNumeric(part1) && isNumeric(part2)) {

                Integer oInt2 = Integer.parseInt(part2);
                Integer oInt1 = 1;
                for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 * oInt2;
                return rr + "";

            } else if (!isNumeric(part2) && isNumeric(part1)) {
                Integer oInt1 = Integer.parseInt(part1);

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 * oInt2;
                return rr + "";
            }
            
            else if (!isNumeric(part2) && !isNumeric(part1)) {
                Integer oInt1 =1;
                 for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 * oInt2;
                return rr + "";
            }
            else {

                Integer oInt1 = Integer.parseInt(part1);
                Integer oInt2 = Integer.parseInt(part2);

                int rr = oInt1 * oInt2;
                return rr + "";
            }

        } 
        
        else if (str.contains("/")) {

            String[] parts = str.split("\\/", 2);
            String part1 = parts[0];
            String part2 = parts[1];

            if (!isNumeric(part1) && isNumeric(part2)) {

                Integer oInt2 = Integer.parseInt(part2);
                Integer oInt1 = 1;
                for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 / oInt2;
                return rr + "";

            } else if (!isNumeric(part2) && isNumeric(part1)) {
                Integer oInt1 = Integer.parseInt(part1);

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 / oInt2;
                return rr + "";
            } 
             else if (!isNumeric(part2) && !isNumeric(part1)) {
                Integer oInt1 =1;
                 for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 / oInt2;
                return rr + "";
            }
            else {

                Integer oInt1 = Integer.parseInt(part1);
                Integer oInt2 = Integer.parseInt(part2);

                int rr = oInt1 / oInt2;
                return rr + "";
            }
           
        } 
        else {
            return str;
        }
    }
    
    public String term2() {

        String mOP = mulop();
        if (!"Error".equals(mOP)) {
            current_index++;
            String f = factor();
            if (!f.equals("Error")) {
                current_index++;
                ret = true;
                String current = mOP + "" + f;
                return current + term2();
                
                
            } else {

                current_index--;

                return "Error";
            }
        } else {

            current_index--;

            return "";
        }
    }

    public String addop() {
        if (getToken().equals("+") || getToken().equals("-")) {
            return getToken();
        } else {
            return "Error";
        }
    }

    public String checksimpleExp(String str) {
        if (str.contains("+")) {

            String[] parts = str.split("\\+", 2);
            String part1 = parts[0];
            String part2 = parts[1];

            if (!isNumeric(part1) && isNumeric(part2)) {

                Integer oInt2 = Integer.parseInt(part2);
                Integer oInt1 = 1;
                for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 + oInt2;
                return rr + "";

            } else if (!isNumeric(part2) && isNumeric(part1)) {
                Integer oInt1 = Integer.parseInt(part1);

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 + oInt2;
                return rr + "";
            } 
             else if (!isNumeric(part2) && !isNumeric(part1)) {
                Integer oInt1 =1;
                 for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 + oInt2;
                return rr + "";
            }
            else {

                Integer oInt1 = Integer.parseInt(part1);
                Integer oInt2 = Integer.parseInt(part2);

                int rr = oInt1 + oInt2;
                return rr + "";
            }

        } 
        
        else if (str.contains("-")) {

            String[] parts = str.split("\\-", 2);
            String part1 = parts[0];
            String part2 = parts[1];

            if (!isNumeric(part1) && isNumeric(part2)) {

                Integer oInt2 = Integer.parseInt(part2);
                Integer oInt1 = 1;
                for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 - oInt2;
                return rr + "";

            } else if (!isNumeric(part2) && isNumeric(part1)) {
                Integer oInt1 = Integer.parseInt(part1);

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 - oInt2;
                return rr + "";
            } 
             else if (!isNumeric(part2) && !isNumeric(part1)) {
                Integer oInt1 =1;
                 for (Value v : results) {
                    if (v.id.equals(part1)) {
                        oInt1 = Integer.parseInt(v.value);
                    }
                }

                Integer oInt2 = 1;
                for (Value v : results) {
                    if (v.id.equals(part2)) {
                        oInt2 = Integer.parseInt(v.value);
                    }
                }
                int rr = oInt1 - oInt2;
                return rr + "";
            }
            else {

                Integer oInt1 = Integer.parseInt(part1);
                Integer oInt2 = Integer.parseInt(part2);

                int rr = oInt1 - oInt2;
                return rr + "";
            }
           
        } 
        else {
            return str;
        }
    }

    public String simpleExp() {
        boolean flag = false;
        String termT = term();
        if (!"Error".equals(termT)) {
            flag = true;
        }

        if (flag) {
            current_index++;
            String sE2 = simpleExp2();
            if (!"Error".equals(sE2)) {

                String ret = checksimpleExp(termT + "" + sE2);
                return ret;
            } else {
                current_index--;
                return "Error";
            }

        } else {
            return "Error";
        }
    }

    boolean flagsE = false;

    public String simpleExp2() {

        String aOP = addop();
        if (!"Error".equals(aOP)) {
            current_index++;
            String termT = term();
            if (!"Error".equals(termT)) {
                current_index++;
                flagsE = true;
                return aOP + "" + termT + simpleExp2();
            } else {
                return "Error";
            }
        } else {

            current_index--;

            return "";
        }

    }

    public boolean comparisonOp() {
        String xy = getToken();
        return xy.equals("<") || xy.equals("=") || xy.equals(">");
    }

    public String exp() {
        String sE = simpleExp();
        if (!"Error".equals(sE)) {
            current_index++;
            return sE + exp2();
        } else {
            return "Error";
        }
    }

    public String exp2() {
        if (comparisonOp()) {
            current_index++;
            String sE = simpleExp();
            if (!"Error".equals(sE)) {
                current_index++;
                return sE + "" + exp2();
            } else {
                return "Error";
            }
        } else {
            current_index--;
            return "";
        }
    }

    public String writeStmt() {

        if (getToken().equals("write")) {
            current_index++;
            return exp();
        } else {
            return "Error";
        }

    }

    public boolean readStmt() {
        if (getToken().equals("read")) {
            current_index++;
            return (getToken().equals("identifier"));
        } else {
            return false;
        }
    }

    
    public void save(String str, String val)
    {
        Value val2 = new Value(str,val);
        results.add(val2);
    }
    public String assignStmt() {

        String id = getValue();
        if (getToken().equals("identifier")) {
            current_index++;
            if (getToken().equals(":")) {
                current_index++;
                if (getToken().equals("=")) {
                    current_index++;

                    String returnedExp = exp();
                    if (returnedExp != "Error") {
                        save(id,returnedExp);
                    }

                    return id + ":=" + returnedExp;
                } else {
                    current_index--;
                }
            } else {
                current_index--;
            }
        }
        return "Error";
    }

    public String repeatStmt() {

        if (getToken().equals("repeat")) {
            current_index++;
            if (statementSequence()) {
                if (getToken().equals("until")) {
                    current_index++;
                    return exp();
                }
            }

        }
        return "Error";

    }

    public boolean ifStmt() {
        if (getToken().equals("if")) {
            current_index++;
            if (exp() != "Error") {
                current_index++;
                if (getToken().equals("then")) {
                    current_index++;
                    if (statementSequence()) {
                        return ifStmt2();
                    } else {
                        current_index--;
                    }
                }
            } else {
                current_index--;
            }

        }
        return false;

    }

    public boolean ifStmt2() {
        switch (getToken()) {
            case "end":
                return true;
            case ";":
                return true;
            case "else":
                current_index++;
                if (statementSequence()) {
                    current_index++;
                    return (getToken().equals("end"));
                }
                break;
        }
        return false;
    }

    public boolean statement() {
        String assign;
        if (ifStmt()) {
            System.out.println("If Statement found");
            return true;

        } else if (readStmt()) {

            System.out.println("Read Statement found");
            return true;
        } else if (repeatStmt() != "Error") {

            System.out.println("Repeat Statement found");
            return true;
        } else if (writeStmt() != "Error") {

            System.out.println("Write Statement found");
            return true;
        } else if ((assign = assignStmt()) != "Error") {

            System.out.println("Assign Statement found " + assign);
            return true;
        } else {
            return false;
        }

    }

    //statement seq2
    //seq2 ;statement||E
    public boolean statementSequence() {
        if (statement()) {
            current_index++;
            return statementSequence2();
        } else {
            return false;
        }
    }

    public boolean statementSequence2() {

        if ((getToken() == null) || ("until".equals(getToken())) || ("end".equals(getToken()))) {
            return true;
        } else if (getToken().equals(";")) {

            current_index++;
            if (getToken() == null) {

                current_index++;
            } else {
                return false;
            }

            if (statement()) {
                current_index++;
                return statementSequence2();
            } else {
                return true;
            }
        } else {

            return false;
        }

    }

    public boolean program() {
        if (statementSequence()) {
            System.out.println("Program Found");
            return true;
        }
        return false;
    }

}

public class ParserProject {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner s = null;
        ArrayList<String> data = new ArrayList<>();

        try {
            s = new Scanner(new BufferedReader(new FileReader("../scanner_output.txt")));
            while (s.hasNext()) {
                String inputString = s.next();
                data.add(inputString);
            }
        } catch (Exception e) {

        } finally {
            if (s != null) {
                s.close();
            }

        }
        ArrayList<Parts> input = new ArrayList<>();
        int j = 0;
        for (String data1 : data) {

            String[] parts = data1.split("~", 2);
            String part1 = parts[0];
            String part2 = parts[1];
            input.add(j++, new Parts(part1, part2));
        }

        ArrayList<String> parsed_data = new ArrayList<>();

        ArrayList<String> values = new ArrayList<>();
        j = 0;
        for (Parts input1 : input) {
            String part1 = input1.part1;
            String part2 = input1.part2;
            values.add(j, part1);
            if ("Identifier".equals(part2)) {
                parsed_data.add(j++, "identifier");
            } else if ("number".equals(part2)) {
                parsed_data.add(j++, "number");
            } else {
                parsed_data.add(j++, part1);
            }
        }

        ArrayList<ArrayList<String>> stats = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        int xx = 0;
        int xxx = 0;

        for (String input1 : parsed_data) {

            temp.add(xx++, input1);
            if (input1.equals(";")) {
                ArrayList<String> temp2 = new ArrayList<>();
                for (String temp1 : temp) {
                    temp2.add(temp1);
                }
                stats.add(xxx++, temp2);
                temp.clear();
                xx = 0;
            }

        }
        if (!temp.isEmpty()) {
            ArrayList<String> temp2 = new ArrayList<>();
            for (String temp1 : temp) {
                temp2.add(temp1);
            }
            stats.add(xxx++, temp2);
        }

        ArrayList<String> program = new ArrayList<>();
        for (ArrayList<String> input1 : stats) {
            program.addAll(input1);
        }

        ArrayList<String> fProgram;

        ArrayList<String> valuesTemp = new ArrayList<>();
        fProgram = new ArrayList<>();
        for (int i = 0; i < program.size(); i++) {
            String program1 = program.get(i);
            String tempValue = values.get(i);
            if (";".equals(program1) || "end".equals(program1)) {

                fProgram.add(program1);
                fProgram.add(null);

                valuesTemp.add(tempValue);
                valuesTemp.add(null);

            } else {

                valuesTemp.add(tempValue);
                fProgram.add(program1);
            }
        }

        values = valuesTemp;
        Parser current = new Parser(fProgram, values);
        current.program();

    }
}

package com.calculatorApp.calculator;
import org.springframework.web.bind.annotation.*;
import java.text.DecimalFormat;
@RestController
public class CalculatorController {
    @CrossOrigin
    @PostMapping("/{displayValue}")
    public String calculate(@RequestBody String displayValue) {
        System.out.println(displayValue);
        String res1 = displayValue.replaceAll("\"", "");
        String res2 = res1.replaceAll("\\u00B2", "S");
        String res3 = res2.replaceAll("\\u221A", "R");
        String res4 = res3.replaceAll("\\u207B\u00B9", "N");
        String res5 = res4.replace("+-", "-");
        String res6 = res5.replace("-+", "-");
        String res = res6.replaceAll(" ", "");
        System.out.println(res);
        String op1 = "";
        String op2 = "";
        String operation = "";
        int flag = 0;
        double answer = 0;
        DecimalFormat df = new DecimalFormat("0.000000000");
        if (res.charAt(0) == '-') {
            op1 += res.charAt(0);
        for (int i = 1; i < res.length(); i++) {
            if ((Character.isDigit(res.charAt(i)) || res.charAt(i) == '.') && flag == 0) {
                op1 += res.charAt(i);
            } else if ((Character.isDigit(res.charAt(i)) || res.charAt(i) == '.' || res.charAt(i) == '-') && flag == 1) {
                op2 += res.charAt(i);
            } else {
                operation += res.charAt(i);
                flag = 1;
            }
        }
    } else {
            for (int i = 0; i < res.length(); i++) {
                if ((Character.isDigit(res.charAt(i)) || res.charAt(i) == '.') && flag == 0) {
                    op1 += res.charAt(i);
                } else if ((Character.isDigit(res.charAt(i)) || res.charAt(i) == '.' || res.charAt(i) == '-') && flag == 1) {
                    op2 += res.charAt(i);
                } else {
                    operation += res.charAt(i);
                    flag = 1;
                }
            }
        }
        if(res == "")
            return "";
        if(res.charAt(res.length()-1) == '+' || res.charAt(res.length()-1) == '-' || res.charAt(res.length()-1) == '*' || res.charAt(res.length()-1) == '÷' || res.charAt(res.length()-1) == '%')
            return "ERROR";
        switch (operation) {
            case "+":
                answer = Double.parseDouble(op1) + Double.parseDouble(op2);
                break;
            case "-":
                answer = Double.parseDouble(op1) - Double.parseDouble(op2);
                break;
            case "*":
                answer = Double.parseDouble(op1) * Double.parseDouble(op2);
                break;
            case "÷":
                if (Double.parseDouble(op2) == 0)
                    return "E";
                else
                    answer = Double.parseDouble(op1) / Double.parseDouble(op2);
                break;
            case "%":
                answer = Double.parseDouble(op1) * Double.parseDouble(op2) / 100;
                break;
            case "N":
                if(op2 =="") {
                    if (Double.parseDouble(op1) == 0)
                        return "E";
                    else {
                        answer = (1 / Double.parseDouble(op1));
                    }
                }else if (op1 == "") {
                    return "ERROR";
                }
                break;
            case "S":
                if(op2 =="")
                    answer = Double.parseDouble(op1) * Double.parseDouble(op1);
                else if (op1 == "")
                    return "ERROR";
                break;
            case "R":
                if(op2 =="") {
                    if (Double.parseDouble(op1) < 0)
                        return "E";
                    else {
                        answer = Math.sqrt(Double.parseDouble(op1));
                    }
                } else if (op1 == "") {
                    if (Double.parseDouble(op2) < 0)
                        return "E";
                    else {
                        answer = Math.sqrt(Double.parseDouble(op2));
                    }
                }
                break;
            default:
                return op1;
        }
        if (Double.toString(answer).length() > 19) {
            return "Too large";
        } else {

            if ((int) answer == answer)
                return Integer.toString((int) answer);
            else
                return Double.toString(Double.parseDouble(df.format(answer)));
        }
    }
}
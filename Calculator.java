import java.util.Scanner;

public class Calculator {
    private static String input;
    private static int index;
    private static char lookahead;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        input = scanner.nextLine().replaceAll("\\s", ""); // Ignore whitespace
        int result = ll1(input);
        System.out.println("Result: " + result);
    }

    private static int ll1(String input) {
        if (input.isEmpty()) {
            System.out.println("Error: Empty input");
            System.exit(1);
        }
        Calculator.input = input;
        index = 0;
        lookahead = input.charAt(index);
        return expr();
    }

    private static int expr() {
        int result = term();
        while (lookahead == '+' || lookahead == '-') {
            char op = lookahead;
            match(op);
            int termResult = term();
            if (op == '+') {
                result += termResult;
            } else {
                result -= termResult;
            }
        }
        return result;
    }

    private static int term() {
        int result = factor();
        while (lookahead == '*' || lookahead == '/') {
            char op = lookahead;
            match(op);
            int factorResult = factor();
            if (op == '*') {
                result *= factorResult;
            } else {
                result /= factorResult;
            }
        }
        return result;
    }

    private static int factor() {
        int result = 0;
        if (lookahead == '(') {
            match('(');
            result = expr();
            match(')');
        } else if (Character.isDigit(lookahead)) {
            result = Integer.parseInt(Character.toString(lookahead));
            match(lookahead);
        } else {
            System.out.println("Syntax error");
            System.exit(1);
        }
        return result;
    }

    private static void match(char expected) {
        if (lookahead == expected) {
            index++;
            if (index < input.length()) {
                lookahead = input.charAt(index);
            } else {
                lookahead = '\0';
            }
        } else {
            System.out.println("Syntax error");
            System.exit(1);
        }
    }
}

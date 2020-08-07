import java.util.Stack;
import java.util.Scanner;
//Been working with group 3 in order to get the implementations to work.
class RPN {
  private Stack<String> evaluationStack = new Stack<String>();

  // Main input loop
  public void loop() {
    Scanner reader = new Scanner(System.in);

    final String prompt = "> ";
    System.out.print(prompt);

    while (reader.hasNext()) {
      String input = reader.next();
      if (input.equals("quit") || input.equals("q")) {
        System.out.println("Quitting");
        break;
      }

      System.out.println("Got a string: " + input);
      evaluationStack.push(input);
      //if the input is an operator we evaluate.
      if (isOperator(input)) {
        evaluate();
      }
      System.out.println(evaluationStack.toString());
      System.out.print(prompt);
    }

    reader.close();
  }

  // Evaluate the stack and push the result back in the same stack
  public void evaluate() {
    if (isOperator(evaluationStack.peek())) {   //We check if the top is an operator. If it is we do the necessary calculations.
      System.out.println("Tokens: ");
      System.out.println(evaluationStack.toString());
      char op = evaluationStack.pop().charAt(0);
      int op2 = Integer.parseInt(evaluationStack.pop());
      int op1 = Integer.parseInt(evaluationStack.pop());
      int result = 0;
      switch (op) {
        case '+':
          result = op1 + op2;
          break;
        case '-':
          result = op1 - op2;
          break;
        case '*':
          result = op1 * op2;
          break;
        case '/':
          result = op1 / op2;
          break;
        default:
          break;
      }
      evaluationStack.push(Integer.toString(result));
    }
  }

  //Method to see if a String is an operator
  public boolean isOperator(String input) {
    return ( input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/") );
  }

  // To visualise the token stack
  public String toString() {
    StringBuilder result = new StringBuilder();
    Stack<String> tmpStack = new Stack<>();
    tmpStack.addAll(evaluationStack);
    while (!tmpStack.isEmpty()){
      result.append(tmpStack.pop());
    }
    return result.toString();
  }

  // For unit test purposes
  public void addToken(String token) {
    evaluationStack.add(token);
    evaluate();
  }

  // For unit test purposes
  public Stack<String> getEvaluationStack() {
    Stack<String> copy = new Stack<String>();
    copy.addAll(evaluationStack);
    return copy;
  }


  public static void main(String[] args) {
    RPN calc = new RPN();
    calc.addToken("5");
    calc.addToken("5");
    calc.addToken("+");
    System.out.println(calc.toString());



    calc.loop();
  }
}

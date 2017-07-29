
import java.util.StringTokenizer;

import data_structures.Queue;
import data_structures.Stack;

public class ExpressionEvaluator  {

	Stack<String> stack = new Stack<String>();
	Queue<String> que = new Queue<String>();

	public String processInput(String s){
		infixToPostfix(s);
		return Solver();	
	}

	public String Solver(){
		while(!que.isEmpty()){
			if(isNumeric(que.peek())){
				stack.push(que.dequeue());
			}
			else if(que.peek().equals("^") || que.peek().equals("*") || que.peek().equals("/")|| que.peek().equals("+") || que.peek().equals("-") ){
				Double A = Double.parseDouble(stack.pop());
				Double B = Double.parseDouble(stack.pop());
				String op = que.dequeue();
				stack.push(Operation(A, B, op));			
			}
		}
		return stack.pop();
	}

	public void infixToPostfix(String s){
		StringTokenizer infix = new StringTokenizer(s);
		while(infix.hasMoreTokens()){
			String Token = infix.nextToken();
			if(Token.equals("(")){
				stack.push("(");
			}
			else if(Token.equals(")")){  
				do { 
					if(stack.peek().equals("^") || stack.peek().equals("/") || stack.peek().equals("*") || stack.peek().equals("+") || stack.peek().equals("-")){
						que.enqueue(stack.pop());}
				} while(!stack.pop().equals("(")); 
			}
			else if(Token.equals("^") || Token.equals("+") || Token.equals("-") || Token.equals("/") || Token.equals("*")){
				while(!stack.isEmpty() && !stack.peek().equals("(") && pemdasPrecedence(Token, stack.peek()) <= 0){
					que.enqueue(stack.pop());
				}
				stack.push(Token);
			}
			if(isNumeric(Token)){
				que.enqueue(Token);
			}
		}
		while(!stack.isEmpty()){
			que.enqueue(stack.pop());
		}
	} 	

	public String Operation(Double a, Double b, String c){
		Double ans = 0.0;
		if(c.equals("+")){
			ans = b + a;
		}
		else if(c.equals("-")){
			ans = b - a;
		}
		else if(c.equals("*")){
			ans = b*a;
		}
		else if(c.equals("/")){
			ans = b/a;
		}
		else if(c.equals("^")){
			ans = (Double) Math.pow(b, a);
		}
		return String.valueOf(ans);
	}

	public int pemdasPrecedence(String CurrentToken, String StackToken){
		int current = 0;
		int onstack = 0;
		if(CurrentToken.equals("^")){
			current = 3;
		}
		else if(CurrentToken.equals("*")){
			current = 2;
		}
		else if(CurrentToken.equals("/")){
			current = 2;
		}
		else if(CurrentToken.equals("+")){
			current = 1;
		}
		else if(CurrentToken.equals("-")){
			current = 1;
		}
		if(StackToken.equals("^")){
			onstack = 3;
		}
		else if(StackToken.equals("*")){
			onstack = 2;
		}
		else if(StackToken.equals("/")){
			onstack = 2;
		}
		else if(StackToken.equals("+")){
			onstack = 1;
		}
		else if(StackToken.equals("-")){
			onstack = 1;
		}
		return (current - onstack);
	}

	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
}

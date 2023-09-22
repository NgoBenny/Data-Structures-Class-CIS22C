import java.util.Scanner;

public class palindrome {

    public static void main(String[] args) {
        
        Scanner read = new Scanner(System.in);

        System.out.print("Enter a string: ");
        
        String input = read.nextLine();

        read.close();

        LinkedStack<Character> stack = new LinkedStack<Character>();
        LinkedQueue<Character> queue = new LinkedQueue<Character>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
            queue.enqueue(input.charAt(i));
        }

        boolean isPalindrome = true;
        while (!queue.isEmpty() && !stack.isEmpty()) {
            if (queue.dequeue().equals(stack.pop())) {
                continue;
            } else {
                isPalindrome = false;
                break;
            }
        }

        if (!isPalindrome) {
            System.out.println(input + " is not a palindrome");
        } else {
            System.out.println(input + " is a palindrome");
        }
    }
}
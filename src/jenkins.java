import java.util.Random;

public class jenkins {

    public String print(String input) {
        return input;
    }
    public static void main(String[] args){

        String name = "Chanthat";
        String jenkins = new jenkins().print(name);
        System.out.print("hello "+jenkins);
        int a = 10;
        int b = 20;
        double sum = a * b;
        System.out.println(sum);
        int ages = 0;

        System.out.println("Hello sonarthat"+ages);
        int unusedNumber = 100;


        // Issue 2: Duplicated string literals
        System.out.println("SonarQube Testing");
        System.out.println("SonarQube Testing");
        System.out.println("SonarQube Testing");
        System.out.println("SonarQube Testing");

        // Issue 3: Always true condition
        int age = 20;
        if (age == age) {
            System.out.println("Age condition");
        }

        // Issue 4: Weak random generator for security use
        Random random = new Random();
        int verificationCode = random.nextInt(1000000);
        System.out.println("Verification code: " + verificationCode);

        // Issue 5: Empty catch block
        try {
            int result = Integer.parseInt("abc");
            System.out.println(result);
        } catch (NumberFormatException e) {
        }

    }
}

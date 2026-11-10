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

    }
}

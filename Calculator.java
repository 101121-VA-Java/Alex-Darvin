public class Calculator{
    public static void main(String[] args){
        int a = 20;
        int b = 13;
        int sum = add(a, b);
        int sub = subtract(a, b);
        int mult = multiply(a, b);
        int rem = remainder(a, b);
        

        System.out.println(sum);
        System.out.println(sub);
        System.out.println(mult);
        System.out.println(rem);
    }

        public static int add(int a, int b){
            return a + b;
        }

        public static int subtract(int a, int b){
            return a - b;
        }

        public static int multiply(int a, int b){
            return a * b;
        }

        public static int remainder(int a, int b){
            return a % b;
        }

        // public static String weekday(String weekday){
    
        // }
}
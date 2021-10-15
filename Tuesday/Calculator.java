public class Calculator{
    public static void main(String[] args){
        int a = 20;
        int b = 13;
        int sum = add(a, b);
        int sub = subtract(a, b);
        double mult = multiply(a, b);
        int rem = remainder(a, b);
        
        System.out.println(sum);
        System.out.println(sub);
        System.out.println(mult);
        System.out.println(rem);

        String day = weekday("Monday");
        String day2 = weekday("Tuesday");
        String day3 = weekday("Wednesday");
        String day4 = weekday("Thursday");
        String day5 = weekday("Friday");
        String day6 = weekday("Saturday");
        String day7 = weekday("Sunday");
        String day8 = weekday("sakjdfbil");
        
        String gre = greeting("Michael");
        System.out.println(gre);

        for (int i = 0; i < 7; i++)
        { 
            if(i == 0){
                System.out.println(day8);
            }
            if(i == 1){
                System.out.println(day);
            }
            if(i == 2){
                System.out.println(day2);
            }
            if(i == 3){
                System.out.println(day3);
            }
            if(i == 4){
                System.out.println(day4);
            }
            if(i == 5){
                System.out.println(day5);
            }
            if(i == 6){
                System.out.println(day6);
            }
            if(i == 7){
                System.out.println(day7);
            }
            if(i == 8){
                System.out.println(day8);
            }
        }
    
    }

    public static int add(int a, int b){
        return a + b;
    }

    public static int subtract(int a, int b){
        return a - b;
    }

    public static double multiply(double a, double b){
        return a * b;
    }

    public static int remainder(int a, int b){
        return a % b;
    }
    
    public static String weekday(String weekday){
        String day = "";

        switch(weekday){
            case "Monday":
                day = "Is the week ever gonna end?";
                break;
            case "Tuesday":
                day = "Is it Friday yet?";
                break;
            case "Wednesday":
                day = "It is Wednesday my dudes";
                break;
            case "Thursday":
                day = "So close to the weekend.";
                break;
            case "Friday":
                day = "Thank god it's Friday";
                break;
            case "Saturday":
                day = "Relax, it's the weekend!";
                break;
            case "Sunday":
                day = "The Weekend's Monday";
                break;
            default:
                day = "Look at a calendar!";
        }
        return day;
    }
    public static void countdown(int startValue) { 
        while (startValue >= 0){
            System.out.println(startValue);
            startValue--;
        }
    }
    public static String greeting(String name){
        String names = "I hope you are having a good day " + name;
        return names;
    }
}
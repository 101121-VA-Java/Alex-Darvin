public class Arrays {
    public static void main(String[] args){
        String[] groupMems = {"Alex", "Brayden", "Gerardo", "Kim", "Albert"};
        String[] reversed = reversedGroupMems(groupMems);
        for(int n = 0; n < groupMems.length; n++){
            System.out.println("The group members reversed are: " + reversed[n]);
        }
    }

    public static String[] reversedGroupMems(String[] a){
        int i = a.length;
        int x = 0;
        String[] reversed = new String[i];

        while(i > 0){
            reversed[x] = a[i-1];
            System.out.println("This is: " + reversed[x]);

            i--;
            x++;
        }
        return reversed;
    }
}
/**
 * Created by Admin on 2017-03-14.
 */
public class test {

    public static int suite(int n) {
        int x;
            if (n == 0) {x= 1;}
            else{

                x=((suite(n-1) + (2*n)) % 7);
            }
        System.out.print(x);
        return x;

    }

    public static void main(String[] args){

        suite(4);

    }

}

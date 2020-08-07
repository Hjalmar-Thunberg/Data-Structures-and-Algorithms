public class Question4 {

    public static int fibonacci(int n) {
        int f[] = new int[n + 2];
        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        int input = 25;
        System.out.println(fibonacci(input));
    }
}
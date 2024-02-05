import java.util.*;
import java.io.*;

public class Main{
    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }

    public static long ominsik(int n) {
        boolean[] isPrime = sieve(n);
        long result = 1L;

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                int exponent = 1;
                while (Math.pow(i, exponent) <= n) {
                    exponent++;
                }
                result *= Math.pow(i, exponent - 1);
            }
            result = result % 987654321;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long result = ominsik(n)%987654321;
        System.out.println(result);
    }
}
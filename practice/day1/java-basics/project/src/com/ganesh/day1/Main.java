package com.ganesh.day1;

import java.util.Scanner;

public class Main {

    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a and b: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Sum = " + (a + b));

        System.out.print("Enter a number for even/odd: ");
        int n = sc.nextInt();
        System.out.println(n + " is " + (isEven(n) ? "Even" : "Odd"));

        System.out.print("Enter three numbers x y z: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        System.out.println("Largest = " + largestOfThree(x, y, z));

        System.out.print("Enter number for factorial: ");
        int f = sc.nextInt();
        System.out.println("Factorial = " + factorial(f));

        System.out.print("Enter number to reverse: ");
        int r = sc.nextInt();
        System.out.println("Reversed = " + reverseNumber(r));

        System.out.print("Enter number to check palindrome: ");
        int p = sc.nextInt();
        System.out.println(p + " is " + (isPalindrome(p) ? "Palindrome" : "Not Palindrome"));

        System.out.print("Enter number to count digits: ");
        int cd = sc.nextInt();
        System.out.println("Digits = " + countDigits(cd));

        System.out.print("Enter number to sum digits: ");
        int sd = sc.nextInt();
        System.out.println("Sum of digits = " + sumDigits(sd));

        System.out.print("Enter number to check prime: ");
        int pr = sc.nextInt();
        System.out.println(pr + " is " + (isPrime(pr) ? "Prime" : "Not Prime"));

        System.out.print("Enter N for fibonacci: ");
        int fibN = sc.nextInt();
        System.out.print("Fibonacci: ");
        printFibonacci(fibN);

        sc.close();
    }

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    static int largestOfThree(int x, int y, int z) {
        int max = x;
        if (y > max) max = y;
        if (z > max) max = z;
        return max;
    }

    static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial not defined for negative numbers");
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    static int reverseNumber(int n) {
        int num = Math.abs(n);
        int rev = 0;
        while (num > 0) {
            int d = num % 10;
            rev = rev * 10 + d;
            num /= 10;
        }
        return n < 0 ? -rev : rev;
    }

    static boolean isPalindrome(int n) {
        return n == reverseNumber(n);
    }

    static int countDigits(int n) {
        int num = Math.abs(n);
        if (num == 0) return 1;
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    static int sumDigits(int n) {
        int num = Math.abs(n);
        int sum = 0;
        while (num > 0) {
            sum += (num % 10);
            num /= 10;
        }
        return sum;
    }

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static void printFibonacci(int n) {
        if (n <= 0) {
            System.out.println("(none)");
            return;
        }
        long a = 0, b = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(a);
            if (i < n) System.out.print(" ");
            long c = a + b;
            a = b;
            b = c;
        }
        System.out.println();
    }
}

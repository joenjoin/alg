package me.tidbits.maths;

public class PrimeNumber {

	public static void main(String[] args) {
		System.out.println(countPrimes(10));
	}

	public static boolean isPrime(int n) {
		return false;
	}

	/**
	 * Count primes using "Sieve of Eratosthenes"
	 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
	 * 
	 * Count prime numbers that are LESS than number n
	 * 
	 * @Input 2
	 * @Output 0
	 * 
	 * @Input 3
	 * @Output 1
	 * 
	 * @Input 10
	 * @Output 4
	 */
	public static int countPrimes(int n) {
		boolean[] sieve = new boolean[n + 1];
		// We are counting LESS than n, so n is not included
		for (int i = 2; i < n; i++) {
			sieve[i] = true;
		}

		int i = 2;
		while (i <= n / 2) {
			if (sieve[i]) {
				int j = i + i;
				while (j < n) {
					sieve[j] = false;
					j += i;
				}
			}
			i++;
		}

		int count = 0;
		for (i = 2; i < n; i++) {
			count += sieve[i] ? 1 : 0;
		}
		return count;
	}
}

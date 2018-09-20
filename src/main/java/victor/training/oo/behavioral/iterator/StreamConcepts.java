package victor.training.oo.behavioral.iterator;

import static java.util.stream.Collectors.toList;

import java.util.stream.Stream;

public class StreamConcepts {

	public static void main(String[] args) {
		//lazy evaluation
		Stream<Integer> stream = Stream.of(1,2,3)
				.filter(StreamConcepts::isOdd);
		System.out.println("Now... printing"); // SOLUTION
		System.out.println(stream.collect(toList()));
	}

	private static boolean isOdd(Integer n) {
		System.out.println("isOdd " + n);
		return n % 2 == 1;
	}
}

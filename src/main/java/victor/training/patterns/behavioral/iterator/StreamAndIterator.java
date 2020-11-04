package victor.training.patterns.behavioral.iterator;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Arrays.asList;

public class StreamAndIterator {
	public static void main(String[] args) {
		List<Integer> list = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		// Make an Iterable
		Iterable<Integer> iterable = () -> list.iterator(); // SOLUTION
		
		// Make Stream using StreamSupport
		Stream<Integer> stream = StreamSupport.stream(iterable.spliterator(), false); // SOLUTION
		
		stream.forEach(System.out::println); // SOLUTION
		
		// Now, vice-versa:
		Stream<Integer> st = list.stream();
		
		// Convert to Iterator and get first element
		System.out.println(st.iterator().next()); // SOLUTION
	}
}

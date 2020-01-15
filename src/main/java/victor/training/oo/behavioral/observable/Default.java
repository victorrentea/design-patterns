package victor.training.oo.behavioral.observable;


import rx.Observable;

public class Default {

    public static void main(String[] args) {

        tryCache(13)
//            .defaultIfEmpty("hartie igienica")
            .switchIfEmpty(StartWith.httpVrajeala(12))
                .toBlocking()// doar pt testare
            .subscribe(System.out::println);
    }

    private static Observable<String> tryCache(int id) {
        // n-am
        return Observable.empty();
    }
}

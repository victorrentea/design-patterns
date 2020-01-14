package victor.training.oo.behavioral;

import lombok.Value;
import rx.Observable;

import java.util.Arrays;

public class Promises {

    public static void main(String[] args) {

        Observable.from(Arrays.asList(2, 30, 22, 5, 60, 1))
                .filter(n -> n >= 10)
                .subscribe(System.out::println);


        Observable.just(13)
                .subscribe(System.out::println);

        Observable.range(1, 10)
            .subscribe(System.out::println);

        Observable.never().subscribe(n -> System.out.println("niciodata")); // Tace ca un nesimtit

        Observable.error(new IllegalStateException("Ntz"))
            .onErrorReturn(t -> "vin")
            .map(x -> x  + " Diesel")
            .subscribe(s -> System.out.println(s));

        System.out.println("Dupa");


        Observable.just(new HttpResponse(300,""))
                // ascunde eroarea cu totul
//            .filter(httpResponse -> httpResponse.getCode() < 300)
                // forma 1
//            .doOnNext(res -> {
//                if (res.getCode() >= 300) {
//                    throw new IllegalStateException();
//                }
//            })
                // forma 2 merge
            .map(res -> {
                if (res.getCode() >= 300) {
                    throw new IllegalStateException();
                } else {
                    return res;
                }
            })
            .subscribe(res -> System.out.println(res.getBody()));
    }

    static Observable<String> makeRequest(int i) {
        if (/*am_in_cache*/ i > 0) {
            return Observable.just("ce_am_in_cache");
        }
        if (/*cacheul_e_corupt*/ i == 0) {
            return Observable.error(new IllegalStateException("Cacheul e varza"));
        }
//        nu il am in cache

        return null; //return Observable.from(futureDinHttpRequest);// httpRequest
    }
}

@Value
class HttpResponse {
    int code;
    String body;
}

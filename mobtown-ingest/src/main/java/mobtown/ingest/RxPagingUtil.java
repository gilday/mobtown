package mobtown.ingest;

import io.reactivex.Observable;

import java.util.Collection;

/**
 * Creates Observable stream of T from a paginated query
 */
public final class RxPagingUtil {

    public static <T> Observable<T> allPages(PageRetriever<T> retriever) {
        return queryPage(0, retriever);
    }

    private static <T> Observable<T> queryPage(int offset, PageRetriever<T> retriever) {
        return Observable.defer(() -> {
            final Collection<T> results = retriever.get(offset);
            if (results.isEmpty()) {
                return Observable.empty();
            }
            return Observable.fromIterable(results)
                    .concatWith(Observable.defer(() -> queryPage(offset + results.size(), retriever)));
        });
    }

    interface PageRetriever<T> {
        /**
         * Given an offset, returns a page of results
         * @return page of results
         */
        Collection<T> get(int offset) throws Exception;
    }
}

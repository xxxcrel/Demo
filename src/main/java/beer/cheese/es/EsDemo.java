package beer.cheese.es;

import java.io.IOException;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;


public class t add EsDemo {
    @Autowired
    RestHighLevelClient client;
    public static void main(String[] args) {

    }

    public  void test() throws IOException {
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(bool);
        request.source(builder);
        client.search(request, RequestOptions.DEFAULT);
    }
}

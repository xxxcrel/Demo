package beer.cheese.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class EsDemo {

    @Autowired
    RestHighLevelClient client;

    public void test(){
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
    }
}

package com.baole.search.server.context;

import com.baole.search.dto.es.request.EsRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.server.es.EsQueryIndex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.Map;

@Getter
@Setter
@ToString
public class EsContextDTO<C, T> {

    private EsRequestDTO esRequestDTO;

    private EsQueryIndex<C, T> esQueryIndex;

    private NativeSearchQuery nativeSearchQuery;

    private SearchHits<C> searchHits;

    private EsResponseDTO<T> esResponseDTO;
}

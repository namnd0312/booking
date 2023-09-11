package com.namnd.bookingbe.repository.elasticsearch;

import com.namnd.bookingbe.model.elasticsearch.RoomDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ERoomRepository extends ElasticsearchRepository<RoomDocument, String> {
}

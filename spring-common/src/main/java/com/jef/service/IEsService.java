package com.jef.service;

import com.jef.entity.User;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;

public interface IEsService {

    TransportClient getClient();
}

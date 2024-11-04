package br.com.ciceroednilson.infrastructure.broker.message;

import java.util.ArrayList;
import java.util.List;

public class ProductMessage {

    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void add(Long id) {
        if (ids == null) {
            ids = new ArrayList<>();
        }
        this.ids.add(id);
    }
}

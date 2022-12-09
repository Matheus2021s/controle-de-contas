package br.com.mariah.controledecontas.genericcrud.collection;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public abstract class Collection<T extends CollectionTarget> {
    protected List<T> list;

    public Collection(final List<T> list) {
        this.list = new CopyOnWriteArrayList<>(list);
    }

    public T resolveByClass(Class<? extends T> value) {
        return list.stream().filter(value::isInstance)
                .findFirst()
                .orElse(null);
    }

}

package com.denysenko.mps.persistence.dao.mongo;

import com.denysenko.mps.model.point.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class Repository implements MongoRepository<Point, String> {


    @Override
    public <S extends Point> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Point> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Point> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Point> findAll() {
        return null;
    }

    @Override
    public Iterable<Point> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Point entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Point> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Point> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Point> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Point> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Point> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Point> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Point> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Point> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Point> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Point> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Point> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Point, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}

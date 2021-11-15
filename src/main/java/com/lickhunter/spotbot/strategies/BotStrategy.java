package com.lickhunter.spotbot.strategies;


@FunctionalInterface
public interface BotStrategy<T> {
    void execute(T t);
}

package pl.javastart.service;

import org.junit.jupiter.api.Test;


class BoosterServiceTest {

    @Test
    void find(){
        BoosterService boosterService = new BoosterService();
        System.out.println(boosterService.findFreeOrderBoost());
    }
}
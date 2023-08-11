package com.example.demo2;

import java.io.IOException;

public interface DealerManagerHandler {

    void readDealers();

    void generateRandomDealers() throws IOException;

    boolean checkDealerSelected(String dealer, int count);

    void TakeSelectedDealersDetails() throws IOException;

    void sortSelectedDealers();
}

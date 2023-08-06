package com.senla.hotelio.service;

import java.util.ArrayList;

public interface IImportService {
    <T> ArrayList<T> getEntitiesFromCsv(String fileName);
}

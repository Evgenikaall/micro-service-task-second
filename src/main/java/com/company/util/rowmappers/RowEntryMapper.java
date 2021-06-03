package com.company.util.rowmappers;

import java.util.Map;

public interface RowEntryMapper<T> {
    T mapEntryRow(Map<String, Object> entry);
}

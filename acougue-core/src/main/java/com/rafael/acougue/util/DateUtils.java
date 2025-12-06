package com.rafael.acougue.util;

import java.time.OffsetDateTime;

/**
 * Utilitário básico para conversões de datas.
 */
public final class DateUtils {

    private DateUtils() {
    }

    public static OffsetDateTime now() {
        return OffsetDateTime.now();
    }
}

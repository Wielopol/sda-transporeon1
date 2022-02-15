package pl.sda.java.adv.school.model;

import java.math.BigDecimal;

public enum GradeWeight {
    AKT (new BigDecimal("1.0")),
    PYT (new BigDecimal("1.5")),
    EGZ (new BigDecimal("2.0"));

    private BigDecimal weight;

    GradeWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }
}

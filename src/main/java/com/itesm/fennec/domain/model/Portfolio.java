package com.itesm.fennec.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {
    private User owner;
    private List<Investment> investments = new ArrayList<>();

    public Portfolio(User owner) {
        this.owner = owner;
    }

    public void addInvestment(Investment investment) {
        this.investments.add(investment);
    }

    public void removeInvestmentById(Long investmentId) {
        this.investments.removeIf(inv -> inv.getId().equals(investmentId));
    }

    public BigDecimal getTotalInvested() {
        return investments.stream()
                .map(Investment::getAmountInvested)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getPropertyCount() {
        return (int) investments.stream()
                .map(inv -> inv.getProperty().getId())
                .distinct()
                .count();
    }
}


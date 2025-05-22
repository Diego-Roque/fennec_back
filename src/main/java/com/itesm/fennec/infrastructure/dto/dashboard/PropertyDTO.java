package com.itesm.fennec.infrastructure.dto.dashboard;

import java.util.List;

public class PropertyDTO {
    public String name;
    public String location;
    public String description;
    public String type;
    public double price;
    public int size;
    public int bathrooms;
    public int bedrooms;
    public int parking;
    public List<Integer> previousPrices;
    public double valuation3Years;
    public double valuation5Years;
    public double growthRate;
    public double roiMonthly;
    public int breakevenYears;
    public int occupancyRate;
    public List<String> riskFactors;
    public String levelRisk;
    public List<String> amenities;
    public String investmentGrade;
    public String phone;



    public PropertyDTO(String name, String location, String description, String type,
                       double price, int size, int bathrooms, int bedrooms, int parking,
                       List<Integer> previousPrices, double valuation3Years, double valuation5Years,
                       double growthRate, double roiMonthly, int breakevenYears, int occupancyRate,
                       List<String> riskFactors, String levelRisk,
                       List<String> amenities, String investmentGrade, String phone) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.type = type;
        this.price = price;
        this.size = size;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.parking = parking;
        this.previousPrices = previousPrices;
        this.valuation3Years = valuation3Years;
        this.valuation5Years = valuation5Years;
        this.growthRate = growthRate;
        this.roiMonthly = roiMonthly;
        this.breakevenYears = breakevenYears;
        this.occupancyRate = occupancyRate;
        this.riskFactors = riskFactors;
        this.levelRisk = levelRisk;
        this.amenities = amenities;
        this.investmentGrade = investmentGrade;
        this.phone = phone;
    }
}

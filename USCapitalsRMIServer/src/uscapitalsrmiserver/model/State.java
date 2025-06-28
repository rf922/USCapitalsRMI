/*
 * State.java
 * 
 * 
 */
package uscapitalsrmiserver.model;

import java.util.Objects;

/**
 *
 * @author rf922
 */
public class State {
    
    private String state;
    private String abbreviation;
    private String capital;
    private Double lattitude;    
    private Double longitude;
    private Long population;

    public State(String state, String abbreviation, String capital, Double lattitude, Double longitude, Long population) {
        this.state = state;
        this.abbreviation = abbreviation;
        this.capital = capital;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.population = population;
    }
    
    
    

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.state);
        hash = 71 * hash + Objects.hashCode(this.abbreviation);
        hash = 71 * hash + Objects.hashCode(this.capital);
        hash = 71 * hash + Objects.hashCode(this.lattitude);
        hash = 71 * hash + Objects.hashCode(this.longitude);
        hash = 71 * hash + Objects.hashCode(this.population);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.abbreviation, other.abbreviation)) {
            return false;
        }
        if (!Objects.equals(this.capital, other.capital)) {
            return false;
        }
        if (!Objects.equals(this.lattitude, other.lattitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return Objects.equals(this.population, other.population);
    }
    @Override
    public String toString(){
        
        String val = String.format("%nState : %s%nCapital : %s%nAbbreviation : %s%nLongitude : %d%nLattitude : %d%nPopulation : %d", this.state, this.capital, this.abbreviation, this.lattitude, this.longitude, this.population);
        return val;
    }
    
    
    
}

/*
 * Copyright (C) 2018 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
 * Karlsruhe, Germany.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.swe.common.constraint;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
public class AllowedTimes extends AbstractConstraint<AllowedTimes> {

    /**
     * Value
     *
     * The values that the user can choose from.
     */
    private List<String> value;

    /**
     * Intervals
     *
     * The intervals that the value must fall in.
     */
    private List<List<String>> interval;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.value);
        hash = 11 * hash + Objects.hashCode(this.interval);
        hash = 11 * hash + Objects.hashCode(this.significantFigures);
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
        final AllowedTimes other = (AllowedTimes) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.interval, other.interval)) {
            return false;
        }
        if (!Objects.equals(this.significantFigures, other.significantFigures)) {
            return false;
        }
        return true;
    }

    /**
     * Significant Figures
     *
     * The number of significant figures.
     */
    private Integer significantFigures;

    public List<String> getValue() {
        return value;
    }

    public List<List<String>> getInterval() {
        return interval;
    }

    public Integer getSignificantFigures() {
        return significantFigures;
    }

    public boolean isValid(String input, String uom) {
        return true;
    }

    public AllowedTimes setValue(List<String> value) {
        this.value = value;
        return this;
    }

    public AllowedTimes setInterval(List<List<String>> interval) {
        this.interval = interval;
        return this;
    }

    public AllowedTimes setSignificantFigures(Integer significantFigures) {
        this.significantFigures = significantFigures;
        return this;
    }

    @Override
    protected AllowedTimes self() {
        return this;
    }

}

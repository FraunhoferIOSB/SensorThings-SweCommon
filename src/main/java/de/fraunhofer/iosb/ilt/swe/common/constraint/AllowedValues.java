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

import de.fraunhofer.iosb.ilt.swe.common.Utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
public class AllowedValues extends AbstractConstraint<AllowedValues> {

    /**
     * Values
     *
     * The values that the user can choose from.
     */
    private List<BigDecimal> value;

    /**
     * Intervals
     *
     * The intervals that the value must fall in.
     */
    private List<List<BigDecimal>> interval;

    /**
     * Significant Figures
     *
     * The number of significant figures.
     */
    private Integer significantFigures;

    public List<BigDecimal> getValue() {
        return value;
    }

    public List<List<BigDecimal>> getInterval() {
        return interval;
    }

    public Integer getSignificantFigures() {
        return significantFigures;
    }

    public boolean isValid(BigDecimal input) {
        if (Utils.isNullOrEmpty(value) && Utils.isNullOrEmpty(interval) && significantFigures == 0) {
            // This constraint is empty
            return true;
        }
        if (value != null) {
            for (BigDecimal item : value) {
                if (item.compareTo(input) == 0) {
                    return true;
                }
            }
        }
        if (interval != null) {
            for (List<BigDecimal> range : interval) {
                if (range.get(0).compareTo(input) < 0 && range.get(1).compareTo(input) > 0) {
                    return true;
                }
            }
        }
        // TODO: validate significantFigues
        return false;
    }

    public AllowedValues setValue(List<BigDecimal> value) {
        this.value = value;
        return this;
    }

    public AllowedValues setInterval(List<List<BigDecimal>> interval) {
        this.interval = interval;
        return this;
    }

    public AllowedValues setSignificantFigures(Integer significantFigures) {
        this.significantFigures = significantFigures;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.value);
        hash = 17 * hash + Objects.hashCode(this.interval);
        hash = 17 * hash + Objects.hashCode(this.significantFigures);
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
        final AllowedValues other = (AllowedValues) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.interval, other.interval)) {
            return false;
        }
        return Objects.equals(this.significantFigures, other.significantFigures);
    }

    @Override
    protected AllowedValues self() {
        return this;
    }

}

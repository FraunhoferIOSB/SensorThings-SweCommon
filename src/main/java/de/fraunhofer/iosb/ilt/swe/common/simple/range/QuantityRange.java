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
package de.fraunhofer.iosb.ilt.swe.common.simple.range;

import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedValues;
import de.fraunhofer.iosb.ilt.swe.common.simple.AbstractSimpleComponent;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
public class QuantityRange extends AbstractSimpleComponent {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuantityRange.class);

    /**
     * Unit of Measure
     *
     * The units of the value of this Quantity.
     */
    private String uom;

    /**
     * Value
     *
     * The starting and ending values of this CategoryRange.
     */
    private List<BigDecimal> value;

    /**
     * Constraint
     *
     * A limited list of possible values.
     */
    private AllowedValues constraint;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.uom);
        hash = 67 * hash + Objects.hashCode(this.value);
        hash = 67 * hash + Objects.hashCode(this.constraint);
        hash = 67 * hash + super.hashCode();
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
        final QuantityRange other = (QuantityRange) obj;
        if (!Objects.equals(this.uom, other.uom)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.constraint, other.constraint)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    @Override
    public List<BigDecimal> getValue() {
        return value;
    }

    public void setValue(List<BigDecimal> value) {
        this.value = value;
    }

    public AllowedValues getConstraint() {
        return constraint;
    }

    public void setConstraint(AllowedValues constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean valueIsValid() {
        if (getValue() == null) {
            return false;
        }
        if (getConstraint() == null) {
            return true;
        }
        for (BigDecimal item : getValue()) {
            if (!constraint.isValid(item)) {
                LOGGER.error("Item '{}' does not fit the constraint", item);
                return false;
            }
        }
        return true;
    }

}

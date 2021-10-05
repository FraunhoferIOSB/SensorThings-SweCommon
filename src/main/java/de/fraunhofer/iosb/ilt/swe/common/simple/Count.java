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
package de.fraunhofer.iosb.ilt.swe.common.simple;

import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedValues;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
public class Count extends AbstractSimpleComponent {

    /**
     * Value
     *
     * an integer that must be within one of the constraint intervals or exactly
     * one of the enumerated values.
     */
    private Long value;

    /**
     * Constraint
     *
     * A list of inclusive intervals and/or single values.
     */
    private AllowedValues constraint;

    public Long getValue() {
        return value;
    }

    public AllowedValues getConstraint() {
        return constraint;
    }

    @Override
    public boolean valueIsValid() {
        if (value == null) {
            return false;
        }
        if (constraint == null) {
            return true;
        }
        return constraint.isValid(new BigDecimal(value));
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void setConstraint(AllowedValues constraint) {
        this.constraint = constraint;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.value);
        hash = 79 * hash + Objects.hashCode(this.constraint);
        hash = 79 * hash + super.hashCode();
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
        final Count other = (Count) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.constraint, other.constraint)) {
            return false;
        }
        return super.equals(obj);
    }

}

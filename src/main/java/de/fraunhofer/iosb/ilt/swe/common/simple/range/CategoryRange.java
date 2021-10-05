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

import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedTokens;
import de.fraunhofer.iosb.ilt.swe.common.simple.AbstractSimpleComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
public class CategoryRange extends AbstractSimpleComponent {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRange.class);

    /**
     * Value
     *
     * The starting end ending values of this CategoryRange.
     */
    private List<String> value = new ArrayList<>();

    /**
     * Allowed Tokens
     *
     * A limited list of possible values.
     */
    private AllowedTokens constraint;

    //TODO
    private Map<String, String> codeSpace;

    public void setValue(List<String> value) {
        this.value = value;
    }

    public void setConstraint(AllowedTokens constraint) {
        this.constraint = constraint;
    }

    public AllowedTokens getConstraint() {
        return constraint;
    }

    public List<String> getValue() {
        return value;
    }

    @Override
    public boolean valueIsValid() {
        if (value == null) {
            return false;
        }
        if (constraint == null) {
            return true;
        }
        for (String item : value) {
            if (!constraint.isValid(item)) {
                LOGGER.error("Item '{}' does not fit the constraint", item);
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.value);
        hash = 59 * hash + Objects.hashCode(this.constraint);
        hash = 59 * hash + Objects.hashCode(this.codeSpace);
        hash = 59 * hash + super.hashCode();
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
        final CategoryRange other = (CategoryRange) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.constraint, other.constraint)) {
            return false;
        }
        if (!Objects.equals(this.codeSpace, other.codeSpace)) {
            return false;
        }
        return super.equals(obj);
    }

}

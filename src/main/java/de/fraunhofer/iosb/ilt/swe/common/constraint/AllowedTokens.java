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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
public class AllowedTokens extends AbstractConstraint<AllowedTokens> {

    /**
     * Value
     *
     * The values that the user can choose from.
     */
    private List<String> value;

    /**
     * Pattern
     *
     * The regex(?) pattern that the value must match.
     */
    private String pattern;

    public AllowedTokens() {
    }

    public AllowedTokens(String... tokens) {
        this.value = Arrays.asList(tokens);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.value);
        hash = 37 * hash + Objects.hashCode(this.pattern);
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
        final AllowedTokens other = (AllowedTokens) obj;
        if (!Objects.equals(this.pattern, other.pattern)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    public AllowedTokens(String pattern) {
        this.pattern = pattern;
    }

    public List<String> getValue() {
        return value;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean isValid(String input) {
        if (value != null) {
            for (String item : value) {
                if (item.equals(input)) {
                    return true;
                }
            }
        }
        if (pattern != null && !pattern.isEmpty()) {
            Pattern compiled = Pattern.compile(pattern);
            if (compiled.matcher(input).matches()) {
                return true;
            }
        }

        return false;
    }

    public AllowedTokens setValue(List<String> value) {
        this.value = value;
        return this;
    }

    public AllowedTokens setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    @Override
    protected AllowedTokens self() {
        return this;
    }

}

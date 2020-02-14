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

import de.fraunhofer.iosb.ilt.configurable.AbstractConfigurable;
import de.fraunhofer.iosb.ilt.configurable.Utils;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
@ConfigurableClass(
        jsonName = "AllowedTokens",
        profilesEdit = MODE_SIMPLE_EXPERT)
public class AllowedTokens extends AbstractConfigurable<Void, Void> {

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Value", description = "The values that the user can choose from.")
    @EditorList.EdOptsList(editor = EditorString.class,
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private List<String> value;

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Pattern", description = "The regex(?) pattern that the value must match.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String pattern;

    public AllowedTokens() {
        this.value = new ArrayList<>();
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
        if (!Utils.isNullOrEmpty(pattern)) {
            Pattern compiled = Pattern.compile(pattern);
            if (compiled.matcher(input).matches()) {
                return true;
            }
        }

        return false;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

}

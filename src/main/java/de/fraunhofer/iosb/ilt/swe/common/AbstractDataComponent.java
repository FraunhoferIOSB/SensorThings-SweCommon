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
package de.fraunhofer.iosb.ilt.swe.common;

import com.google.gson.JsonElement;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorBoolean;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import java.util.Objects;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
public abstract class AbstractDataComponent extends AbstractSWEIdentifiable {

    @ConfigurableField(editor = EditorString.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Definition", description = "A scoped name that maps to a controlled term defined in a (web accessible) dictionary, registry or ontology.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String definition;

    @ConfigurableField(editor = EditorBoolean.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Optional", description = "A flag indicating if the component value can be omitted.")
    @EditorBoolean.EdOptsBool(dflt = false, profilesEdit = MODE_SIMPLE_EXPERT)
    private boolean optional;

    @ConfigurableField(editor = EditorBoolean.class, optional = true,
            profilesGui = MODE_EXPERT,
            label = "Updatable", description = "A flag indicating if the component value is fixed or can be updated.")
    @EditorBoolean.EdOptsBool(dflt = false, profilesEdit = MODE_SIMPLE_EXPERT)
    private boolean updatable;

    public String getDefinition() {
        return definition;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.definition);
        hash = 29 * hash + (this.optional ? 1 : 0);
        hash = 29 * hash + (this.updatable ? 1 : 0);
        hash = 29 * hash + super.hashCode();
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
        final AbstractDataComponent other = (AbstractDataComponent) obj;
        if (this.optional != other.optional) {
            return false;
        }
        if (this.updatable != other.updatable) {
            return false;
        }
        if (!Objects.equals(this.definition, other.definition)) {
            return false;
        }
        return super.equals(obj);
    }

    public Boolean isOptional() {
        return optional;
    }

    public Boolean isUpdatable() {
        return updatable;
    }

    public abstract JsonElement getValueJson();

    public abstract void setValueJson(JsonElement value);

    /**
     * Checks if any set values are valid for any set constraints.
     *
     * @return true if the values are valid.
     */
    public abstract boolean valueIsValid();

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }
}

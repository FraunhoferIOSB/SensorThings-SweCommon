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

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.AbstractEditorMap;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorBigDecimal;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorMap;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedValues;
import java.math.BigDecimal;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hylke van der Schaaf
 * @author Michael Jacoby
 */
@ConfigurableClass(jsonName = "Quantity")
public class Quantity extends AbstractSimpleComponent {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Quantity.class);


    @ConfigurableField(editor = EditorBigDecimal.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "a real value that is within one of "
            + "the constraint intervals or exactly one of the enumerated values,"
            + " and most importantly is expressed in the unit specified.")
    @EditorBigDecimal.EdOptsBigDecimal(dflt = 0, profilesEdit = MODE_VALUE)
    private BigDecimal value;

    @ConfigurableField(editor = EditorClass.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Constraint", description = "A limited list of possible values.")
    @EditorClass.EdOptsClass(clazz = AllowedValues.class)
    private AllowedValues constraint;

    @ConfigurableField(editor = EditorString.class, optional = false,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "UoM", description = "The units of the value of this Quantity.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String uom;

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setConstraint(AllowedValues constraint) {
        this.constraint = constraint;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getUom() {
        return uom;
    }

    public AllowedValues getConstraint() {
        return constraint;
    }

    @Override
    public JsonElement getValueJson() {
        return new JsonPrimitive(value);
    }

    @Override
    public void setValueJson(JsonElement jsonValue) {
        if (!jsonValue.isJsonPrimitive()) {
            LOGGER.warn("Given value is not a JsonPrimitive: {}", jsonValue);
            return;
        }
        try {
            setValue(jsonValue.getAsJsonPrimitive().getAsBigDecimal());
            EditorMap<?> editor = getConfigEditor(null, null);
            AbstractEditorMap.Item valueEditorItem = editor.getOptions().get("value");
            valueEditorItem.editor.setValue(value);
        } catch (NumberFormatException exc) {
            LOGGER.warn("Given value is not a BigDecimal: {}", jsonValue);
            LOGGER.trace("", exc);
        }
    }

    @Override
    public boolean valueIsValid() {
        if (value == null) {
            return false;
        }
        if (constraint == null) {
            return true;
        }
        return constraint.isValid(value);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.value);
        hash = 53 * hash + Objects.hashCode(this.constraint);
        hash = 53 * hash + Objects.hashCode(this.uom);
        hash = 53 * hash + super.hashCode();
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
        final Quantity other = (Quantity) obj;
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

}

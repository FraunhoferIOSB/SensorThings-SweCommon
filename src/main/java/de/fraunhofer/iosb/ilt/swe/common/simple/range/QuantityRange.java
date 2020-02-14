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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.AbstractEditorMap;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorBigDecimal;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorMap;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;
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
@ConfigurableClass(jsonName = "QuantityRange")
public class QuantityRange extends AbstractSimpleComponent {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuantityRange.class);

    @ConfigurableField(editor = EditorString.class, optional = false,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Unit of Measure", description = "The units of the value of this Quantity.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String uom;

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "The starting end ending values of this CategoryRange.")
    @EditorList.EdOptsList(editor = EditorBigDecimal.class,
            profilesEdit = MODE_SIMPLE_EXPERT,
            minCount = 2, maxCount = 2, horizontal = true, labelText = "Range:")
    @EditorBigDecimal.EdOptsBigDecimal(dflt = 0)
    private List<BigDecimal> value;

    @ConfigurableField(editor = EditorClass.class, optional = true,
            profilesGui = MODE_SIMPLE + "," + MODE_EXPERT,
            label = "Constraint", description = "A limited list of possible values.")
    @EditorClass.EdOptsClass(clazz = AllowedValues.class)
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
    public JsonElement getValueJson() {
        JsonArray array = new JsonArray(getValue().size());
        for (BigDecimal item : getValue()) {
            array.add(new JsonPrimitive(item));
        }
        return array;
    }

    @Override
    public void setValueJson(JsonElement jsonValue) {
        if (!jsonValue.isJsonArray()) {
            LOGGER.warn("Given value is not a JsonArray: {}", jsonValue);
            return;
        }
        getValue().clear();
        JsonArray valueArray = jsonValue.getAsJsonArray();
        for (JsonElement item : valueArray) {
            try {
                BigDecimal itemValue = item.getAsBigDecimal();
                getValue().add(itemValue);
            } catch (NumberFormatException exc) {
                LOGGER.warn("Given value is not a BigDecimal: {}", item);
                LOGGER.trace("", exc);
            }
        }
        EditorMap<?> editor = getConfigEditor(null, null);
        AbstractEditorMap.Item valueEditorItem = editor.getOptions().get("value");
        valueEditorItem.editor.setValue(getValue());
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

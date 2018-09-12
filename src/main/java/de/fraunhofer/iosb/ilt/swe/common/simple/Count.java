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
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorLong;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorMap;
import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(jsonName = "Count")
public class Count extends AbstractSimpleComponent {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Count.class);

    @ConfigurableField(editor = EditorLong.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "an integer that must be within one of the constraint intervals or exactly one of the enumerated values.")
    @EditorLong.EdOptsLong(dflt = 0, profilesEdit = MODE_VALUE)
    private Long value;

    @ConfigurableField(editor = EditorClass.class, optional = true,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Constraint", description = "A list of inclusive intervals and/or single values")
    @EditorClass.EdOptsClass(clazz = AllowedValues.class)
    private AllowedValues constraint;

    public Long getValue() {
        return value;
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
            value = jsonValue.getAsJsonPrimitive().getAsLong();
            EditorMap<?> editor = getConfigEditor(null, null);
            AbstractEditorMap.Item valueEditorItem = editor.getOptions().get("value");
            valueEditorItem.editor.setValue(value);
        } catch (NumberFormatException exc) {
            LOGGER.warn("Given value is not a Long: {}", jsonValue);
            LOGGER.trace("", exc);
        }
    }

}

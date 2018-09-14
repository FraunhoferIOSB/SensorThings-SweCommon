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
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorMap;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedTokens;
import de.fraunhofer.iosb.ilt.swe.common.simple.AbstractSimpleComponent;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(jsonName = "CategoryRange")
public class CategoryRange extends AbstractSimpleComponent {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRange.class);

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "The starting end ending values of this CategoryRange.")
    @EditorList.EdOptsList(editor = EditorString.class,
            profilesEdit = MODE_SIMPLE_EXPERT,
            minCount = 2, maxCount = 2, horizontal = true, labelText = "Range:")
    @EditorString.EdOptsString
    private List<String> value;

    @ConfigurableField(editor = EditorClass.class, optional = true,
            profilesGui = MODE_SIMPLE + "," + MODE_EXPERT,
            label = "Allowed Tokens", description = "A limited list of possible values.")
    private AllowedTokens constraint;

    //TODO
    private Map<String, String> codeSpace;

    public AllowedTokens getConstraint() {
        return constraint;
    }

    public List<String> getValue() {
        return value;
    }

    @Override
    public JsonElement getValueJson() {
        JsonArray array = new JsonArray(value.size());
        for (String item : value) {
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
        value.clear();
        JsonArray valueArray = jsonValue.getAsJsonArray();
        for (JsonElement item : valueArray) {
            try {
                String itemValue = item.getAsString();
                value.add(itemValue);
            } catch (NumberFormatException exc) {
                LOGGER.warn("Given value is not a BigDecimal: {}", item);
                LOGGER.trace("", exc);
            }
        }
        EditorMap<?> editor = getConfigEditor(null, null);
        AbstractEditorMap.Item valueEditorItem = editor.getOptions().get("value");
        valueEditorItem.editor.setValue(value);
    }

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

}

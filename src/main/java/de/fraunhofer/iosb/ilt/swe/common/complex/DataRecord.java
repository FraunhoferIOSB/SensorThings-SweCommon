/*
 * Copyright (C) 2018 Fraunhofer IOSB.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.swe.common.complex;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.swe.common.AbstractDataComponent;
import java.util.List;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(jsonName = "DataRecord")
public class DataRecord extends AbstractDataComponent {

    @ConfigurableField(editor = EditorList.class,
            profilesGui = MODE_SIMPLE_EXPERT_VALUE,
            jsonField = "field",
            label = "Fields",
            description = "The fields in this DataRecord.")
    @EditorList.EdOptsList(editor = EditorClass.class,
            profilesEdit = MODE_SIMPLE_EXPERT)
    @EditorClass.EdOptsClass(clazz = Field.class)
    private List<Field> fields;

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public JsonElement getValueJson() {
        JsonObject object = new JsonObject();
        for (Field item : fields) {
            String itemName = item.getName();
            JsonElement itemValue = item.getField().getValueJson();
            object.add(itemName, itemValue);
        }
        return object;
    }

    @Override
    public void setValueJson(JsonElement value) {
        if (!value.isJsonObject()) {
            return;
        }
        JsonObject asJsonObject = value.getAsJsonObject();
        for (Field item : fields) {
            String itemName = item.getName();
            JsonElement itemValue = asJsonObject.get(itemName);
            item.getField().setValueJson(itemValue);
        }
    }

    @Override
    public boolean valueIsValid() {
        if (fields == null) {
            return true;
        }
        for (Field field : fields) {
            if (!field.valueIsValid()) {
                return false;
            }
        }
        return true;
    }

}

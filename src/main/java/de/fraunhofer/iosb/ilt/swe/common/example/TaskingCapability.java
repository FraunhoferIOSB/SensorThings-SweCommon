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
package de.fraunhofer.iosb.ilt.swe.common.example;

import com.google.gson.JsonElement;
import de.fraunhofer.iosb.ilt.configurable.AnnotatedConfigurable;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorSubclass;
import de.fraunhofer.iosb.ilt.swe.common.AbstractDataComponent;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT_VALUE;

/**
 * @author scf
 */
@ConfigurableClass()
public class TaskingCapability implements AnnotatedConfigurable<Void, Void> {

    @ConfigurableField(
            editor = EditorSubclass.class,
            label = "taskingParameters",
            description = "The parameters for this taskingCapability",
            profilesGui = MODE_SIMPLE_EXPERT_VALUE)
    @EditorSubclass.EdOptsSubclass(
            iface = AbstractDataComponent.class,
            merge = true,
            nameField = "type",
            profilesEdit = MODE_SIMPLE_EXPERT)
    private AbstractDataComponent component;

    public JsonElement getValueJson() {
        return component.getValueJson();
    }

    public void setValueJson(JsonElement valueJson) {
        component.setValueJson(valueJson);
    }

    public boolean valueIsValid() {
        return component.valueIsValid();
    }

}

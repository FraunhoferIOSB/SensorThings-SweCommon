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
package de.fraunhofer.iosb.ilt.swe.common.complex;

import de.fraunhofer.iosb.ilt.configurable.AbstractConfigurable;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorSubclass;
import de.fraunhofer.iosb.ilt.swe.common.AbstractDataComponent;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT_VALUE;

/**
 *
 * @author scf
 */
@ConfigurableClass(profilesEdit = MODE_SIMPLE_EXPERT)
public class Field extends AbstractConfigurable<Void, Void> {

    @ConfigurableField(editor = EditorString.class,
            profilesGui = MODE_SIMPLE_EXPERT_VALUE,
            label = "Name", description = "The unique name for this field.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String name;

    @ConfigurableField(editor = EditorSubclass.class,
            merge = true,
            profilesGui = MODE_SIMPLE_EXPERT_VALUE,
            label = "Field",
            description = "The actual field.")
    @EditorSubclass.EdOptsSubclass(iface = AbstractDataComponent.class,
            profilesEdit = MODE_SIMPLE_EXPERT,
            merge = true,
            nameField = "type")
    private AbstractDataComponent field;

    public String getName() {
        return name;
    }

    public AbstractDataComponent getField() {
        return field;
    }

    public boolean valueIsValid() {
        return field.valueIsValid();
    }

}

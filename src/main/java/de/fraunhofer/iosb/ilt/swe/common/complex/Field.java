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
import java.util.Objects;

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

    public Field() {
        // Empty constructor
    }

    public Field(String name, AbstractDataComponent field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractDataComponent getField() {
        return field;
    }

    public void setField(AbstractDataComponent field) {
        this.field = field;
    }

    public boolean valueIsValid() {
        return field.valueIsValid();
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
        final Field other = (Field) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.field, other.field);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.field);
        return hash;
    }

}

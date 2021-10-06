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

import de.fraunhofer.iosb.ilt.swe.common.AbstractDataComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Hylke van der Schaaf
 */
public class DataRecord extends AbstractDataComponent {

    private List<AbstractDataComponent> field;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.field);
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
        final DataRecord other = (DataRecord) obj;
        if (!Objects.equals(this.field, other.field)) {
            return false;
        }
        return super.equals(obj);
    }

    @Deprecated
    public List<AbstractDataComponent> getFields() {
        return getField();
    }

    public List<AbstractDataComponent> getField() {
        if (field == null) {
            field = new ArrayList<>();
        }
        return field;
    }

    public void setField(List<AbstractDataComponent> field) {
        this.field = field;
    }

    public java.util.Optional<AbstractDataComponent> getFieldByName(String name) {
        return getField().stream().filter(f -> f.getName().equals(name)).findFirst();
    }

    public void addDataComponent(AbstractDataComponent field) {
        String name = field.getName();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Field must have a non-empty name");
        }
        if (getFieldByName(name).isPresent()) {
            throw new IllegalArgumentException("Field with name " + name + " is already present");
        }
        getField().add(field);
    }

    @Override
    public boolean valueIsValid() {
        if (field == null) {
            return true;
        }
        for (AbstractDataComponent f : field) {
            if (!f.valueIsValid()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<String, Object> getValue() {
        Map<String, Object> value = new LinkedHashMap<>();
        for (AbstractDataComponent f : field) {
            value.put(f.getName(), f.getValue());
        }
        return value;
    }
}

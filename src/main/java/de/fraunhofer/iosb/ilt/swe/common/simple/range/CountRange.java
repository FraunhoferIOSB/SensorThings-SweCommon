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

import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorClass;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorLong;
import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedValues;
import de.fraunhofer.iosb.ilt.swe.common.simple.AbstractSimpleComponent;
import java.util.List;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(jsonName = "CountRange")
public class CountRange extends AbstractSimpleComponent {

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "The starting end ending values of this CategoryRange.")
    @EditorList.EdOptsList(editor = EditorLong.class,
            profilesEdit = MODE_SIMPLE_EXPERT,
            minCount = 2, maxCount = 2, horizontal = true, labelText = "Range:")
    @EditorLong.EdOptsLong(dflt = 0)
    private List<Long> value;

    @ConfigurableField(editor = EditorClass.class, optional = true,
            profilesGui = MODE_SIMPLE + "," + MODE_EXPERT,
            label = "Constraint", description = "A limited list of possible values.")
    @EditorClass.EdOptsClass(clazz = AllowedValues.class)
    private AllowedValues constraint;

    public List<Long> getValue() {
        return value;
    }

    public void setValue(List<Long> value) {
        if (value.size() != 2) {
            throw new IllegalArgumentException("CountRange must have a value with exactly 2 values.");
        }
        this.value = value;
    }

    public AllowedValues getConstraint() {
        return constraint;
    }

    public void setConstraint(AllowedValues constraint) {
        this.constraint = constraint;
    }

}

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
import de.fraunhofer.iosb.ilt.configurable.editor.EditorDouble;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorList;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;
import de.fraunhofer.iosb.ilt.swe.common.constraint.AllowedValues;
import de.fraunhofer.iosb.ilt.swe.common.simple.AbstractSimpleComponent;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(jsonName = "QuantityRange")
public class QuantityRange extends AbstractSimpleComponent {

    @ConfigurableField(editor = EditorString.class, optional = false,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Unit of Measure", description = "The units of the value of this Quantity.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String uom;

    @ConfigurableField(editor = EditorList.class, optional = true,
            profilesGui = MODE_VALUE,
            label = "Value", description = "The starting end ending values of this CategoryRange.")
    @EditorList.EdOptsList(editor = EditorDouble.class,
            profilesEdit = MODE_SIMPLE_EXPERT,
            minCount = 2, maxCount = 2, horizontal = true, labelText = "Range:")
    @EditorDouble.EdOptsDouble(dflt = 0)
    private List<BigDecimal> value;

    @ConfigurableField(editor = EditorClass.class, optional = true,
            profilesGui = MODE_SIMPLE + "," + MODE_EXPERT,
            label = "Constraint", description = "A limited list of possible values.")
    @EditorClass.EdOptsClass(clazz = AllowedValues.class)
    private AllowedValues constraint;

}

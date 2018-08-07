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
package de.fraunhofer.iosb.ilt.swe.common.util;

import de.fraunhofer.iosb.ilt.configurable.AbstractConfigurable;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableClass;
import de.fraunhofer.iosb.ilt.configurable.annotations.ConfigurableField;
import de.fraunhofer.iosb.ilt.configurable.editor.EditorString;
import static de.fraunhofer.iosb.ilt.swe.common.AbstractSWE.MODE_SIMPLE_EXPERT;

/**
 *
 * @author Hylke van der Schaaf
 */
@ConfigurableClass(profilesEdit = MODE_SIMPLE_EXPERT)
public class NillValue extends AbstractConfigurable<Void, Void> {

    @ConfigurableField(editor = EditorString.class, optional = false,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Reason", description = "The reason why a measurement value is not available. "
            + "It is a resolvable reference to a controlled term that provides the formal"
            + " textual definition of this reason (usually agreed upon by one or more communities).")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String reason;

    @ConfigurableField(editor = EditorString.class, optional = false,
            profilesGui = MODE_SIMPLE_EXPERT,
            label = "Value", description = "the data value that would be found in the\n"
            + "stream to indicate that a measurement value is missing for the corresponding reason. The\n"
            + "range of values allowed here is the range of values allowed by the datatype of the parent\n"
            + "data component.")
    @EditorString.EdOptsString(profilesEdit = MODE_SIMPLE_EXPERT)
    private String value;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

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

/**
 * Definition of the placeholder for a "no value" value.
 */
public class NillValue {

    private String reason;

    private String value;

    public String getReason() {
        return reason;
    }

    public NillValue setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getValue() {
        return value;
    }

    public NillValue setValue(String value) {
        this.value = value;
        return this;
    }

}

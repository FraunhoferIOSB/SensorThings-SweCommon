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
 * Unit of Measurement.
 */
public class UnitOfMeasurement {

    private String code;
    private String href;
    private String label;
    private String symbol;

    public String getCode() {
        return code;
    }

    public UnitOfMeasurement setCode(String code) {
        this.code = code;
        return this;
    }

    public String getHref() {
        return href;
    }

    public UnitOfMeasurement setHref(String href) {
        this.href = href;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public UnitOfMeasurement setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public UnitOfMeasurement setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

}

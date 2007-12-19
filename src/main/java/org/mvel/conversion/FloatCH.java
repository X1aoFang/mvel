/**
 * MVEL (The MVFLEX Expression Language)
 *
 * Copyright (C) 2007 Christopher Brock, MVFLEX/Valhalla Project and the Codehaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.mvel.conversion;

import org.mvel.ConversionException;
import org.mvel.ConversionHandler;

import static java.lang.String.valueOf;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FloatCH implements ConversionHandler {
    private static final Map<Class, Converter> CNV =
            new HashMap<Class, Converter>();


    public Object convertFrom(Object in) {
        if (!CNV.containsKey(in.getClass())) throw new ConversionException("cannot convert type: "
                + in.getClass().getName() + " to: " + Integer.class.getName());
        return CNV.get(in.getClass()).convert(in);
    }


    public boolean canConvertFrom(Class cls) {
        return CNV.containsKey(cls);
    }

    static {
        CNV.put(String.class,
                new Converter() {
                    public Object convert(Object o) {
                        if (((String) o).length() == 0) return (float) 0;

                        return Float.parseFloat(((String) o));
                    }
                }
        );


        CNV.put(Object.class,
                new Converter() {
                    public Object convert(Object o) {
                        return CNV.get(String.class).convert(valueOf(o));
                    }
                }
        );

        CNV.put(BigDecimal.class,
                new Converter() {
                    public Float convert(Object o) {
                        return ((BigDecimal) o).floatValue();
                    }
                }
        );


        CNV.put(BigInteger.class,
                new Converter() {
                    public Float convert(Object o) {
                        return ((BigInteger) o).floatValue();
                    }
                }
        );


        CNV.put(Float.class,
                new Converter() {
                    public Object convert(Object o) {
                        return o;
                    }
                }
        );

        CNV.put(Integer.class,
                new Converter() {
                    public Float convert(Object o) {
                        //noinspection UnnecessaryBoxing
                        return ((Integer) o).floatValue();
                    }
                }
        );


        CNV.put(Double.class,
                new Converter() {
                    public Float convert(Object o) {
                        return ((Double) o).floatValue();
                    }
                }
        );

        CNV.put(Long.class,
                new Converter() {
                    public Float convert(Object o) {
                        return ((Long) o).floatValue();
                    }
                }
        );

        CNV.put(Short.class,
                new Converter() {
                    public Float convert(Object o) {
                        return ((Short) o).floatValue();
                    }
                }
        );

        CNV.put(Boolean.class,
                new Converter() {
                    public Float convert(Object o) {
                        if ((Boolean) o) return 1f;
                        else return 0f;
                    }
                }
        );

    }
}
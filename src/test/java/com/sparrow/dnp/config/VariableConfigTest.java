/* 
 * Copyright 2020 KR ENDÜSTRİYEL BİLİŞİM LTD. ŞTİ..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.dnp.config;

import com.automatak.dnp3.enums.EventAnalogOutputStatusVariation;
import com.automatak.dnp3.enums.EventAnalogVariation;
import com.automatak.dnp3.enums.EventBinaryOutputStatusVariation;
import com.automatak.dnp3.enums.EventBinaryVariation;
import com.automatak.dnp3.enums.EventCounterVariation;
import com.automatak.dnp3.enums.EventDoubleBinaryVariation;
import com.automatak.dnp3.enums.EventFrozenCounterVariation;
import com.automatak.dnp3.enums.PointClass;
import com.automatak.dnp3.enums.StaticAnalogOutputStatusVariation;
import com.automatak.dnp3.enums.StaticAnalogVariation;
import com.automatak.dnp3.enums.StaticBinaryOutputStatusVariation;
import com.automatak.dnp3.enums.StaticBinaryVariation;
import com.automatak.dnp3.enums.StaticCounterVariation;
import com.automatak.dnp3.enums.StaticDoubleBinaryVariation;
import com.automatak.dnp3.enums.StaticFrozenCounterVariation;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author KR INDUSTRIAL IT
 */
public class VariableConfigTest {

    /**
     * Test of AnalogInputConfig
     */
    @Test
    public void testAnalogInputConfig() {

        VariableConfig.AnalogInputConfig config = new VariableConfig.AnalogInputConfig();
        config.setDeadband(50.0);
        config.setEventVariation(EventAnalogVariation.Group32Var4);
        config.setStaticVariation(StaticAnalogVariation.Group30Var3);
        config.setIndex(99);
        config.setPointClass(PointClass.Class1);

        assertEquals(50.0, config.getDeadband());
        assertEquals(EventAnalogVariation.Group32Var4, config.getEventVariation());
        assertEquals(StaticAnalogVariation.Group30Var3, config.getStaticVariation());
        assertEquals(99, config.getIndex());
        assertEquals(PointClass.Class1, config.getPointClass());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "deadband":
                        assertEquals(50.0, evt.getOldValue());
                        assertEquals(5.0, evt.getNewValue());
                        break;
                    case "eventVariation":
                        assertEquals(EventAnalogVariation.Group32Var4, evt.getOldValue());
                        assertEquals(EventAnalogVariation.Group32Var3, evt.getNewValue());
                        break;
                    case "staticVariation":
                        assertEquals(StaticAnalogVariation.Group30Var3, evt.getOldValue());
                        assertEquals(StaticAnalogVariation.Group30Var2, evt.getNewValue());
                        break;
                    case "index":
                        assertEquals(99, evt.getOldValue());
                        assertEquals(9, evt.getNewValue());
                        break;
                    case "pointClass":
                        assertEquals(PointClass.Class1, evt.getOldValue());
                        assertEquals(PointClass.Class2, evt.getNewValue());
                        break;
                    default:
                        fail("Unknown property name=" + evt.getPropertyName());
                }
            }
        });

        config.setDeadband(5.0);
        config.setEventVariation(EventAnalogVariation.Group32Var3);
        config.setStaticVariation(StaticAnalogVariation.Group30Var2);
        config.setIndex(9);
        config.setPointClass(PointClass.Class2);

    }

    @Test
    public void testAnalogOutputConfig() {

        VariableConfig.AnalogOutputConfig config = new VariableConfig.AnalogOutputConfig();
        config.setDeadband(150.0);
        config.setEventVariation(EventAnalogOutputStatusVariation.Group42Var3);
        config.setStaticVariation(StaticAnalogOutputStatusVariation.Group40Var3);
        config.setIndex(199);
        config.setPointClass(PointClass.Class2);

        assertEquals(150.0, config.getDeadband());
        assertEquals(EventAnalogOutputStatusVariation.Group42Var3, config.getEventVariation());
        assertEquals(StaticAnalogOutputStatusVariation.Group40Var3, config.getStaticVariation());
        assertEquals(199, config.getIndex());
        assertEquals(PointClass.Class2, config.getPointClass());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "deadband":
                        assertEquals(150.0, evt.getOldValue());
                        assertEquals(15.0, evt.getNewValue());
                        break;
                    case "eventVariation":
                        assertEquals(EventAnalogOutputStatusVariation.Group42Var3, evt.getOldValue());
                        assertEquals(EventAnalogOutputStatusVariation.Group42Var6, evt.getNewValue());
                        break;
                    case "staticVariation":
                        assertEquals(StaticAnalogOutputStatusVariation.Group40Var3, evt.getOldValue());
                        assertEquals(StaticAnalogOutputStatusVariation.Group40Var4, evt.getNewValue());
                        break;
                    case "index":
                        assertEquals(199, evt.getOldValue());
                        assertEquals(19, evt.getNewValue());
                        break;
                    case "pointClass":
                        assertEquals(PointClass.Class2, evt.getOldValue());
                        assertEquals(PointClass.Class3, evt.getNewValue());
                        break;
                    default:
                        fail("Unknown property name=" + evt.getPropertyName());
                }
            }
        });

        config.setDeadband(15.0);
        config.setEventVariation(EventAnalogOutputStatusVariation.Group42Var6);
        config.setStaticVariation(StaticAnalogOutputStatusVariation.Group40Var4);
        config.setIndex(19);
        config.setPointClass(PointClass.Class3);

    }

    @Test
    public void testDigitalInputConfig() {

        VariableConfig.DigitalInputConfig config = new VariableConfig.DigitalInputConfig();

        config.setEventVariation(EventBinaryVariation.Group2Var2);
        config.setStaticVariation(StaticBinaryVariation.Group1Var2);
        config.setIndex(231);
        config.setPointClass(PointClass.Class1);

        assertEquals(EventBinaryVariation.Group2Var2, config.getEventVariation());
        assertEquals(StaticBinaryVariation.Group1Var2, config.getStaticVariation());
        assertEquals(231, config.getIndex());
        assertEquals(PointClass.Class1, config.getPointClass());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "eventVariation":
                        assertEquals(EventBinaryVariation.Group2Var2, evt.getOldValue());
                        assertEquals(EventBinaryVariation.Group2Var3, evt.getNewValue());
                        break;
                    case "staticVariation":
                        assertEquals(StaticBinaryVariation.Group1Var2, evt.getOldValue());
                        assertEquals(StaticBinaryVariation.Group1Var1, evt.getNewValue());
                        break;
                    case "index":
                        assertEquals(231, evt.getOldValue());
                        assertEquals(29, evt.getNewValue());
                        break;
                    case "pointClass":
                        assertEquals(PointClass.Class1, evt.getOldValue());
                        assertEquals(PointClass.Class3, evt.getNewValue());
                        break;
                    default:
                        fail("Unknown property name=" + evt.getPropertyName());
                }
            }
        });

        config.setEventVariation(EventBinaryVariation.Group2Var2);
        config.setStaticVariation(StaticBinaryVariation.Group1Var1);
        config.setIndex(29);
        config.setPointClass(PointClass.Class3);

    }

    @Test
    public void testDigitalOutputConfig() {

        VariableConfig.DigitalOutputConfig config = new VariableConfig.DigitalOutputConfig();

        config.setEventVariation(EventBinaryOutputStatusVariation.Group11Var2);
        config.setStaticVariation(StaticBinaryOutputStatusVariation.Group10Var2);
        config.setIndex(232);
        config.setPointClass(PointClass.Class1);

        assertEquals(EventBinaryOutputStatusVariation.Group11Var2, config.getEventVariation());
        assertEquals(StaticBinaryOutputStatusVariation.Group10Var2, config.getStaticVariation());
        assertEquals(232, config.getIndex());
        assertEquals(PointClass.Class1, config.getPointClass());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "eventVariation":
                        assertEquals(EventBinaryOutputStatusVariation.Group11Var2, evt.getOldValue());
                        assertEquals(EventBinaryOutputStatusVariation.Group11Var1, evt.getNewValue());
                        break;
                    case "staticVariation":
                        assertEquals(StaticBinaryOutputStatusVariation.Group10Var2, evt.getOldValue());
                        assertEquals(StaticBinaryOutputStatusVariation.Group10Var2, evt.getNewValue());
                        break;
                    case "index":
                        assertEquals(232, evt.getOldValue());
                        assertEquals(11, evt.getNewValue());
                        break;
                    case "pointClass":
                        assertEquals(PointClass.Class1, evt.getOldValue());
                        assertEquals(PointClass.Class1, evt.getNewValue());
                        break;
                    default:
                        fail("Unknown property name=" + evt.getPropertyName());
                }
            }
        });

        config.setEventVariation(EventBinaryOutputStatusVariation.Group11Var1);
        config.setStaticVariation(StaticBinaryOutputStatusVariation.Group10Var2);
        config.setIndex(11);
        config.setPointClass(PointClass.Class1);

    }

    @Test
    public void testCounterConfig() {

        VariableConfig.CounterConfig config = new VariableConfig.CounterConfig();

        config.setDeadband(12);
        config.setEventVariation(EventCounterVariation.Group22Var2);
        config.setStaticVariation(StaticCounterVariation.Group20Var2);
        config.setIndex(1011);
        config.setPointClass(PointClass.Class2);

        assertEquals(12, config.getDeadband());
        assertEquals(EventCounterVariation.Group22Var2, config.getEventVariation());
        assertEquals(StaticCounterVariation.Group20Var2, config.getStaticVariation());
        assertEquals(1011, config.getIndex());
        assertEquals(PointClass.Class2, config.getPointClass());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "deadband":
                        assertEquals(12, evt.getOldValue());
                        assertEquals(13, evt.getNewValue());
                        break;
                    case "eventVariation":
                        assertEquals(EventCounterVariation.Group22Var2, evt.getOldValue());
                        assertEquals(EventCounterVariation.Group22Var6, evt.getNewValue());
                        break;
                    case "staticVariation":
                        assertEquals(StaticCounterVariation.Group20Var2, evt.getOldValue());
                        assertEquals(StaticCounterVariation.Group20Var5, evt.getNewValue());
                        break;
                    case "index":
                        assertEquals(1011, evt.getOldValue());
                        assertEquals(1111, evt.getNewValue());
                        break;
                    case "pointClass":
                        assertEquals(PointClass.Class2, evt.getOldValue());
                        assertEquals(PointClass.Class3, evt.getNewValue());
                        break;
                    default:
                        fail("Unknown property name=" + evt.getPropertyName());
                }
            }
        });

        config.setDeadband(13);
        config.setEventVariation(EventCounterVariation.Group22Var6);
        config.setStaticVariation(StaticCounterVariation.Group20Var5);
        config.setIndex(1111);
        config.setPointClass(PointClass.Class3);

    }

    @Test
    public void testFrozenCounterConfig() {

        VariableConfig.FrozenCounterConfig config = new VariableConfig.FrozenCounterConfig();

        config.setDeadband(12);
        config.setEventVariation(EventFrozenCounterVariation.Group23Var2);
        config.setStaticVariation(StaticFrozenCounterVariation.Group21Var2);
        config.setIndex(1011);
        config.setPointClass(PointClass.Class2);

        assertEquals(12, config.getDeadband());
        assertEquals(EventFrozenCounterVariation.Group23Var2, config.getEventVariation());
        assertEquals(StaticFrozenCounterVariation.Group21Var2, config.getStaticVariation());
        assertEquals(1011, config.getIndex());
        assertEquals(PointClass.Class2, config.getPointClass());

        
        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case "deadband":
                        assertEquals(12, evt.getOldValue());
                        assertEquals(13, evt.getNewValue());
                        break;
                    case "eventVariation":
                        assertEquals(EventFrozenCounterVariation.Group23Var2, evt.getOldValue());
                        assertEquals(EventFrozenCounterVariation.Group23Var6, evt.getNewValue());
                        break;
                    case "staticVariation":
                        assertEquals(StaticFrozenCounterVariation.Group21Var2, evt.getOldValue());
                        assertEquals(StaticFrozenCounterVariation.Group21Var9, evt.getNewValue());
                        break;
                    case "index":
                        assertEquals(1011, evt.getOldValue());
                        assertEquals(1111, evt.getNewValue());
                        break;
                    case "pointClass":
                        assertEquals(PointClass.Class2, evt.getOldValue());
                        assertEquals(PointClass.Class3, evt.getNewValue());
                        break;
                    default:
                        fail("Unknown property name=" + evt.getPropertyName());
                }
            }
        });

        config.setDeadband(13);
        config.setEventVariation(EventFrozenCounterVariation.Group23Var6);
        config.setStaticVariation(StaticFrozenCounterVariation.Group21Var9);
        config.setIndex(1111);
        config.setPointClass(PointClass.Class3);

    }

    @Test
    public void testDoubleDigitalConfig() {

        VariableConfig.DoubleDigitalConfig config = new VariableConfig.DoubleDigitalConfig();
        

        config.setEventVariation(EventDoubleBinaryVariation.Group4Var2);
        config.setStaticVariation(StaticDoubleBinaryVariation.Group3Var2);
        config.setIndex(1);
        config.setPointClass(PointClass.Class1);

        
        assertEquals(EventDoubleBinaryVariation.Group4Var2, config.getEventVariation());
        assertEquals(StaticDoubleBinaryVariation.Group3Var2, config.getStaticVariation());
        assertEquals(1, config.getIndex());
        assertEquals(PointClass.Class1, config.getPointClass());

        
        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {

                    case "eventVariation":
                        assertEquals(EventDoubleBinaryVariation.Group4Var2, evt.getOldValue());
                        assertEquals(EventDoubleBinaryVariation.Group4Var3, evt.getNewValue());
                        break;
                    case "staticVariation":
                        assertEquals(StaticDoubleBinaryVariation.Group3Var2, evt.getOldValue());
                        assertEquals(StaticDoubleBinaryVariation.Group3Var2, evt.getNewValue());
                        break;
                    case "index":
                        assertEquals(1, evt.getOldValue());
                        assertEquals(2, evt.getNewValue());
                        break;
                    case "pointClass":
                        assertEquals(PointClass.Class1, evt.getOldValue());
                        assertEquals(PointClass.Class2, evt.getNewValue());
                        break;
                    default:
                        fail("Unknown property name=" + evt.getPropertyName());
                }
            }
        });

        config.setEventVariation(EventDoubleBinaryVariation.Group4Var3);
        config.setStaticVariation(StaticDoubleBinaryVariation.Group3Var2);
        config.setIndex(2);
        config.setPointClass(PointClass.Class2);

    }

}

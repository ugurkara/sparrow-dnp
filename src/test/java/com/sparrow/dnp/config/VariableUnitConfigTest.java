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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author KR INDUSTRIAL IT
 */
public class VariableUnitConfigTest {

    /**
     * Test of AnalogInputConfig
     */
    @Test
    public void testAnalogInputConfig() {
        VariableUnitConfig.AnalogInputs config = new VariableUnitConfig.AnalogInputs();
        config.setEventBufferSize(100);
        assertEquals(100, config.getEventBufferSize());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals(100, evt.getOldValue());
                assertEquals(110, evt.getNewValue());
            }
        });

        config.setEventBufferSize(110);
    }

    @Test
    public void testAnalogOutputConfig() {
        VariableUnitConfig.AnalogOutputs config = new VariableUnitConfig.AnalogOutputs();

        config.setEventBufferSize(120);
        assertEquals(120, config.getEventBufferSize());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals(120, evt.getOldValue());
                assertEquals(130, evt.getNewValue());
            }
        });

        config.setEventBufferSize(130);
    }

    @Test
    public void testDigitalInputConfig() {
        VariableUnitConfig.DigitalInputs config = new VariableUnitConfig.DigitalInputs();

        config.setEventBufferSize(140);
        assertEquals(140, config.getEventBufferSize());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals(140, evt.getOldValue());
                assertEquals(150, evt.getNewValue());
            }
        });

        config.setEventBufferSize(150);
    }

    @Test
    public void testDigitalOutputConfig() {
        VariableUnitConfig.DigitalOutputs config = new VariableUnitConfig.DigitalOutputs();

        config.setEventBufferSize(240);
        assertEquals(240, config.getEventBufferSize());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals(240, evt.getOldValue());
                assertEquals(250, evt.getNewValue());
            }
        });

        config.setEventBufferSize(250);
    }

    @Test
    public void testCounterConfig() {
        VariableUnitConfig.Counters config = new VariableUnitConfig.Counters();

        config.setEventBufferSize(340);
        assertEquals(340, config.getEventBufferSize());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals(340, evt.getOldValue());
                assertEquals(350, evt.getNewValue());
            }
        });

        config.setEventBufferSize(350);
    }

    @Test
    public void testFrozenCounterConfig() {
        VariableUnitConfig.FrozenCounters config = new VariableUnitConfig.FrozenCounters();
        config.setEventBufferSize(440);
        assertEquals(440, config.getEventBufferSize());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals(440, evt.getOldValue());
                assertEquals(450, evt.getNewValue());
            }
        });

        config.setEventBufferSize(450);

    }

    @Test
    public void testDoubleDigitalConfig() {
        VariableUnitConfig.DoubleDigitals config = new VariableUnitConfig.DoubleDigitals();

        config.setEventBufferSize(540);
        assertEquals(540, config.getEventBufferSize());

        config.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals(540, evt.getOldValue());
                assertEquals(550, evt.getNewValue());
            }
        });

        config.setEventBufferSize(550);
    }

}

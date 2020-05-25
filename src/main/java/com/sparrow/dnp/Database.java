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
package com.sparrow.dnp;

import java.util.ArrayList;
/**
 * @author ugurkara
 */
public class Database {

    private final ArrayList<MemoryUnit> items = new ArrayList<>();
    private final MemoryUnit.AnalogInputs analogInputMemoryUnit;
    private final MemoryUnit.AnalogOutputs analogOutputMemoryUnit;
    private final MemoryUnit.DigitalInputs digitalInputMemoryUnit;
    private final MemoryUnit.DigitalOutputs digitalOutputMemoryUnit;
    private final MemoryUnit.Counters counterMemoryUnit;
    private final MemoryUnit.FrozenCounters frozenCounterMemoryUnit;
    private final MemoryUnit.DoubleDigitals doubleDigitalMemoryUnit;

    public Database() {
        this.analogInputMemoryUnit = new MemoryUnit.AnalogInputs();
        this.analogOutputMemoryUnit = new MemoryUnit.AnalogOutputs();
        this.digitalInputMemoryUnit = new MemoryUnit.DigitalInputs();
        this.digitalOutputMemoryUnit = new MemoryUnit.DigitalOutputs();
        this.counterMemoryUnit = new MemoryUnit.Counters();
        this.frozenCounterMemoryUnit = new MemoryUnit.FrozenCounters();
        this.doubleDigitalMemoryUnit = new MemoryUnit.DoubleDigitals();
        this.items.add(analogInputMemoryUnit);
        this.items.add(analogOutputMemoryUnit);
        this.items.add(digitalInputMemoryUnit);
        this.items.add(digitalOutputMemoryUnit);
        this.items.add(counterMemoryUnit);
        this.items.add(frozenCounterMemoryUnit);
        this.items.add(doubleDigitalMemoryUnit);
    }

    public MemoryUnit.AnalogInputs getAnalogInputs() {
        return analogInputMemoryUnit;
    }

    public MemoryUnit.AnalogOutputs getAnalogOutputs() {
        return analogOutputMemoryUnit;
    }

    public MemoryUnit.Counters getCounters() {
        return counterMemoryUnit;
    }

    public MemoryUnit.DigitalInputs getDigitalInputs() {
        return digitalInputMemoryUnit;
    }

    public MemoryUnit.DigitalOutputs getDigitalOutputs() {
        return digitalOutputMemoryUnit;
    }
    public MemoryUnit.DoubleDigitals getDoubleDigitals() {
        return doubleDigitalMemoryUnit;
    }

    public MemoryUnit.FrozenCounters getFrozenCounters() {
        return frozenCounterMemoryUnit;
    }

    public ArrayList<MemoryUnit> getItems() {
        return items;
    }

}
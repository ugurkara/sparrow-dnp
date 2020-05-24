/* 
 * Copyright 2020 KR INDUSTRIAL IT.
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



import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

@XmlType(propOrder = {

    "name",

    "zoneOffset",

    "supportColdStart",

    "supportWarmStart",

    "linkConfig",

    "memoryUnits"})

@XmlSeeAlso({SlaveDeviceConfig.class, MasterDeviceConfig.class})

public class BaseDeviceConfig {



    private static int index = 1;

    private String name = getDefaultName() + index++;



    private int zoneOffset = 0;



    private boolean supportWarmStart = false;



    private boolean supportColdStart = false;



    protected String getDefaultName() {

        return "Device";

    }



    @XmlElement

    public String getName() {

        return name;

    }



    public void setName(String newValue) {

        String oldValue = this.name;

        this.name = newValue;

        this.pcs.firePropertyChange("name", oldValue, newValue);

    }







    @XmlElement

    public Integer getZoneOffset() {

        return zoneOffset;

    }



    public void setZoneOffset(Integer newValue) {

        Integer oldValue = this.zoneOffset;

        this.zoneOffset = newValue;

        this.pcs.firePropertyChange("zoneOffset", oldValue, newValue);

    }

    

    





    public Boolean isSupportColdStart() {

        return supportColdStart;

    }



    public void setSupportColdStart(Boolean newValue) {

        Boolean oldValue = this.supportColdStart;

        this.supportColdStart = newValue;

        this.pcs.firePropertyChange("supportColdStart", oldValue, newValue);

    }



    public Boolean isSupportWarmStart() {

        return supportWarmStart;

    }



    public void setSupportWarmStart(Boolean newValue) {

        Boolean oldValue = this.supportWarmStart;

        this.supportWarmStart = newValue;

        this.pcs.firePropertyChange("supportWarmStart", oldValue, newValue);

    }



    private LinkConfig linkConfig = new LinkConfig();



    @XmlElement(name = "link")

    final public LinkConfig getLinkConfig() {

        return linkConfig;

    }



    public void setLinkConfig(LinkConfig linkConfig) {

        this.linkConfig = linkConfig;

    }



    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        this.linkConfig.addPropertyChangeListener(listener);

        this.pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        this.linkConfig.removePropertyChangeListener(listener);

        this.pcs.removePropertyChangeListener(listener);

    }



    private final ArrayList<VariableUnitConfig> memoryUnits = new ArrayList<>();



    @XmlElement(name = "memoryUnit")

    public ArrayList<VariableUnitConfig> getMemoryUnits() {

        return memoryUnits;

    }



    public VariableUnitConfig getMemoryUnit(VariableType variableType) {



        switch (variableType) {

            case AnalogInput:

                return getAnalogInputs();

            case AnalogOutput:

                return getAnalogOutputs();

            case Counter:

                return getCounters();

            case DigitalInput:

                return getDigitalInputs();

            case DigitalOutput:

                return getDigitalOutputs();

            case DoubleDigital:

                return getDoubleDigitals();

            case FrozenCounter:

                return getFrozenCounters();

        }

        throw new InvalidParameterException("Unknown Variable Type" + variableType.name());

    }



    public VariableUnitConfig getAnalogInputs() {

        return getMemoryUnits().stream().filter(new Predicate<VariableUnitConfig>() {

            @Override

            public boolean test(VariableUnitConfig t) {

                return t.getType().equals(VariableType.AnalogInput);

            }

        }).findFirst().orElseGet(new Supplier<VariableUnitConfig>() {

            @Override

            public VariableUnitConfig get() {

                var v = new VariableUnitConfig.AnalogInputs();

                getMemoryUnits().add(v);

                return v;

            }

        });

    }



    public VariableUnitConfig getAnalogOutputs() {

        return getMemoryUnits().stream().filter(new Predicate<VariableUnitConfig>() {

            @Override

            public boolean test(VariableUnitConfig t) {

                return t.getType().equals(VariableType.AnalogOutput);

            }

        }).findFirst().orElseGet(new Supplier<VariableUnitConfig>() {

            @Override

            public VariableUnitConfig get() {

                var v = new VariableUnitConfig.AnalogOutputs();

                getMemoryUnits().add(v);

                return v;

            }

        });

    }



    public VariableUnitConfig getCounters() {

        return getMemoryUnits().stream().filter(new Predicate<VariableUnitConfig>() {

            @Override

            public boolean test(VariableUnitConfig t) {

                return t.getType().equals(VariableType.Counter);

            }

        }).findFirst().orElseGet(new Supplier<VariableUnitConfig>() {

            @Override

            public VariableUnitConfig get() {

                var v = new VariableUnitConfig.Counters();

                getMemoryUnits().add(v);

                return v;

            }

        });

    }



    public VariableUnitConfig getDigitalInputs() {



        return getMemoryUnits().stream().filter(new Predicate<VariableUnitConfig>() {

            @Override

            public boolean test(VariableUnitConfig t) {

                return t.getType().equals(VariableType.DigitalInput);

            }

        }).findFirst().orElseGet(new Supplier<VariableUnitConfig>() {

            @Override

            public VariableUnitConfig get() {

                var v = new VariableUnitConfig.DigitalInputs();

                getMemoryUnits().add(v);

                return v;

            }

        });

    }



    public VariableUnitConfig getDigitalOutputs() {



        return getMemoryUnits().stream().filter(new Predicate<VariableUnitConfig>() {

            @Override

            public boolean test(VariableUnitConfig t) {

                return t.getType().equals(VariableType.DigitalOutput);

            }

        }).findFirst().orElseGet(new Supplier<VariableUnitConfig>() {

            @Override

            public VariableUnitConfig get() {

                var v = new VariableUnitConfig.DigitalOutputs();

                getMemoryUnits().add(v);

                return v;

            }

        });

    }



    public VariableUnitConfig getDoubleDigitals() {



        return getMemoryUnits().stream().filter(new Predicate<VariableUnitConfig>() {

            @Override

            public boolean test(VariableUnitConfig t) {

                return t.getType().equals(VariableType.DoubleDigital);

            }

        }).findFirst().orElseGet(new Supplier<VariableUnitConfig>() {

            @Override

            public VariableUnitConfig get() {

                var v = new VariableUnitConfig.DoubleDigitals();

                getMemoryUnits().add(v);

                return v;

            }

        });

    }



    public VariableUnitConfig getFrozenCounters() {



        return getMemoryUnits().stream().filter(new Predicate<VariableUnitConfig>() {

            @Override

            public boolean test(VariableUnitConfig t) {

                return t.getType().equals(VariableType.FrozenCounter);

            }

        }).findFirst().orElseGet(new Supplier<VariableUnitConfig>() {

            @Override

            public VariableUnitConfig get() {

                var v = new VariableUnitConfig.FrozenCounters();

                getMemoryUnits().add(v);

                return v;

            }

        });



    }

}


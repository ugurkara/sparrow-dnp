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



import com.automatak.dnp3.ClassField;
import com.automatak.dnp3.enums.PointClass;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;



/**

 *

 *

 *

 * @author ugurkara

 *

 */

public class ClassFieldConfig {



    @XmlElement(name = "pointClass")
    private final ArrayList<PointClass> items = new ArrayList<>();

    

    public ArrayList<PointClass> getItems() {

        return items;

    }



    public ClassField build() {



        ClassField classField = ClassField.none();



        for (PointClass item : items) {

            classField.set(item, true);

        }

        

        return classField;



    }



    public Boolean isClass0() {

       return items.stream().filter(s -> s.equals(PointClass.Class0)).findFirst().isPresent();

    }



    public void setClass0(Boolean newValue) {

        

        Boolean oldValue = isClass0();

        if (newValue) {

            items.add(PointClass.Class0);

        } else {

            items.remove(PointClass.Class0);

        }

        this.pcs.firePropertyChange("class0", oldValue, newValue);



    }



    public Boolean isClass1() {

        return items.stream().filter(s -> s.equals(PointClass.Class1)).findFirst().isPresent();

    }



    public void setClass1(Boolean newValue) {



        Boolean oldValue = isClass1();

        if (newValue) {

            items.add(PointClass.Class1);

        } else {

            items.remove(PointClass.Class1);

        }



        this.pcs.firePropertyChange("class1", oldValue, newValue);



    }



    public Boolean isClass2() {

        return items.stream().filter(s -> s.equals(PointClass.Class2)).findFirst().isPresent();

    }



    public void setClass2(Boolean newValue) {



        Boolean oldValue = isClass2();

        if (newValue) {

            items.add(PointClass.Class2);

        } else {

            items.remove(PointClass.Class2);

        }

        this.pcs.firePropertyChange("class2", oldValue, newValue);

    }



    public Boolean isClass3() {

        return items.stream().filter(s -> s.equals(PointClass.Class3)).findFirst().isPresent();

    }



    public void setClass3(Boolean newValue) {

        

        Boolean oldValue = isClass3();

        

        if (newValue) {

            items.add(PointClass.Class3);

        } else {

            items.remove(PointClass.Class3);

        }

        this.pcs.firePropertyChange("class3", oldValue, newValue);



    }



    public static ClassFieldConfig allEvent() {



        ClassFieldConfig classFieldConfig = new ClassFieldConfig();

        classFieldConfig.getItems().add(PointClass.Class1);

        classFieldConfig.getItems().add(PointClass.Class2);

        classFieldConfig.getItems().add(PointClass.Class3);

        return classFieldConfig;



    }



    public static ClassFieldConfig all() {



        ClassFieldConfig classFieldConfig = new ClassFieldConfig();

        classFieldConfig.getItems().add(PointClass.Class0);

        classFieldConfig.getItems().add(PointClass.Class1);

        classFieldConfig.getItems().add(PointClass.Class2);

        classFieldConfig.getItems().add(PointClass.Class3);

        return classFieldConfig;



    }



    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);



    public void addPropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.addPropertyChangeListener(listener);

    }



    public void removePropertyChangeListener(PropertyChangeListener listener) {

        this.pcs.removePropertyChangeListener(listener);

    }



}


/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovysnack.enums

import org.testng.annotations.Test

class EnumBasicsTest {

    // --------------------------------------------------------
    /*
     * Simple enum demo
     */

    enum NumericEnums {
        ONE, TWO, THREE, FOUR, FIVE,
    }

    int numericEnumsToInt(NumericEnums e) {
        switch (e) {
            case NumericEnums.ONE:
                return 1;
            case NumericEnums.TWO:
                return 2;
            case NumericEnums.THREE:
                return 3;
            case NumericEnums.FOUR:
                return 4;
            case NumericEnums.FIVE:
                return 5;
            default:
                return 0;
        }
    }

    @Test
    public void numericEnums() {
        println("printing NumericEnums.values():")
        for (e in NumericEnums.values()) {
            println e.toString()
        }

        assert NumericEnums.valueOf("ONE") == NumericEnums.ONE

        assert numericEnumsToInt(NumericEnums.ONE) == 1
        assert numericEnumsToInt(NumericEnums.TWO) == 2
        assert numericEnumsToInt(NumericEnums.THREE) == 3
        assert numericEnumsToInt(NumericEnums.FOUR) == 4
        assert numericEnumsToInt(NumericEnums.FIVE) == 5
    }


    // --------------------------------------------------------
    /*
     * enum with Constructor and public method demo
     */

    enum ConstructableEnums {
        ABC(10, "abc"), DEF(20, "def"), GHI(30, "ghi"), JKL(40, "jkl")

        {
            println "initizlizer for ConstructableEnums"
        }

        final int num
        final String name

        ConstructableEnums(int _num, String _name) {
            println "constructor for ConstructableEnums :" + _num + ", " + _name
            this.num = _num
            this.name = _name
        }

        String getContent() {
            return String.format("num=[%d], name=[%s]", num, name)
        }

        {
            println "initizlizer for ConstructableEnums2"
        }
    }

    @Test
    public void constractableEnums() {
        println("printing ConstructableEnums.values():")
        for (e in ConstructableEnums.values()) {
            println(e.toString())
        }

        assert ConstructableEnums.valueOf("DEF") == ConstructableEnums.DEF
        assert ConstructableEnums.DEF.getContent() == "num=[20], name=[def]"
    }

    // --------------------------------------------------------
    /*
     * enum with abstract method and method override
     */

    enum EnumsWithMethod {
        MORNING("Good Morning") {
            @Override
            String hello(String yourName) {
                return greeting + ", " + yourName + ". zzz..."
            }
        },
        AFTERNOON("Good Afternoon") {
            @Override
            String hello(String yourName) {
                return greeting + ", " + yourName + ". tea or coffee ?"
            }

            @Override
            String getGreeting() {
                return super.getGreeting() + ", but sleepy..."
            }
        },
        EVENING("Good Evening") {
            @Override
            String hello(String yourName) {
                return greeting + ", " + yourName + ". sleep, sleep."
            }
        }

        {
            println "initizlizer for EnumsWithMethod"
        }

        final String greeting

        EnumsWithMethod(String _greeting) {
            println "constructor for EnumsWithMethod :" + _greeting
            this.greeting = _greeting
        }

        // DIFFER TO JAVA (1)
        //abstract String hello(String yourName)
        String hello(String yourName) {
            return "Groovy enum can't override super method."
        }

        String getGreeting() {
            return greeting
        }

        {
            println "initizlizer for EnumsWithMethod2"
        }
    }

    @Test
    public void enumsWithMethod() {
        // DIFFER TO JAVA (1)
        String expected = "Groovy enum can't override super method."
        assert EnumsWithMethod.MORNING.hello("foo") == expected
        assert EnumsWithMethod.AFTERNOON.hello("bar") == expected
        assert EnumsWithMethod.EVENING.hello("baz") == expected

        assert EnumsWithMethod.MORNING.getGreeting() == "Good Morning"

        // DIFFER TO JAVA (2)
        // Overriden methods by enum are ignored.
        assert EnumsWithMethod.AFTERNOON.getGreeting() != "Good Afternoon, but sleepy..."
        // super methods are called.
        assert EnumsWithMethod.AFTERNOON.getGreeting() == "Good Afternoon"

        assert EnumsWithMethod.EVENING.getGreeting() == "Good Evening"
    }

    // --------------------------------------------------------
    /*
     * enum can implements interface method
     */

    // DIFFER TO JAVA (3)
    // can't implements inner interface
    //    interface HelloInterface {
    //        public String sayHello(String yourName);
    //    }

    enum EnumWithInterface implements HelloInterface {
        ME_THEN_YOU("foo") {
            public String sayHello(String yourName) {
                return String.format("I'm %s, you're %s.", myName, yourName);
            }
        },
        YOU_THEN_ME("bar") {
            public String sayHello(String yourName) {
                return String.format("You're %s, I'm %s.", yourName, myName);
            }
        }

        {
            println "initizlizer for EnumWithInterface"
        }

        final String myName;

        /*
         * DIFFER TO JAVA (4)
         * Groovy:Can't have an abstract method in a non-abstract class.
         * The class 'groovysnack.enums.EnumBasicsTest$EnumWithInterface' must be 
         * declared abstract or the method 'java.lang.String sayHello(java.lang.String)' 
         * must be implemented.
         */
        String sayHello(String yourName) {
            return yourName
        }

        EnumWithInterface(String _myName) {
            println "constructor for EnumWithInterface :" + _myName
            myName = _myName;
        }

        {
            println "initizlizer for EnumWithInterface2"
        }
    }

    @Test
    public void enumWithInterface() {

        // DIFFER TO JAVA (5)
        // Overriden methods by enum are ignored.
        assert EnumWithInterface.ME_THEN_YOU.sayHello("abc") != "I'm foo, you're abc."
        // super methods are called.
        assert EnumWithInterface.ME_THEN_YOU.sayHello("abc") == "abc"

        assert EnumWithInterface.YOU_THEN_ME.sayHello("def") != "You're def, I'm bar."
        assert EnumWithInterface.YOU_THEN_ME.sayHello("def") == "def"
    }
}
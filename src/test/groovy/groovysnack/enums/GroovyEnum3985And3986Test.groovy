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

/**
 * @see http://jira.codehaus.org/browse/GROOVY-3985
 * @see http://jira.codehaus.org/browse/GROOVY-3986
 */
class GroovyEnum3985And3986Test {
    
    /*
     * GROOVY-3985
     * error in instance initializer for Enum element, without special method
     */
    private enum GroovyEnum3985 {
        ABC {
            {
                println("initializer for ABC");
                println(this.inspect());
                println(this.dump());
            }
        },
        DEF {
        },
        GHI {
            int ghi() {
                100
            }
            // must be diveded by line break.
            {
                println "initializer for GHI"
                println this.inspect()
                println this.dump()
            }
            int ghi2() {
                200
            }
        }
    }

    @Test
    public void GroovyEnum3985demo() {
        for (e in GroovyEnum3985.values()) {
            println e.toString()
        }
        assert GroovyEnum3985.valueOf("ABC") == GroovyEnum3985.ABC
    }

    /*
     * GROOVY-3986
     * can't omit toplevel parentheses in enum element method
     */
    private enum GroovyEnum3986 {
        XYZ {
            String toString() {
                println "xyz"
                return "xyz"
            }
        },
        IJK,
        LMN {
            String toString() {
                println("lmn")
                return "lmn"
            }
        }
    }

    @Test
    public void GroovyEnum3986demo() {
        for (e in GroovyEnum3986.values()) {
            println e.toString()
        }
        assert GroovyEnum3986.valueOf("XYZ") == GroovyEnum3986.XYZ
    }
}
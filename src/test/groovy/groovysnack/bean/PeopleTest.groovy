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
package groovysnack.bean

import static org.testng.Assert.*

import org.testng.annotations.Test

class PeopleTest
{
    @Test
    void basic() {
        def p1 = new People(groovyUser:true, age:20, name:'Jon', birthday:new Date())
        assert p1.groovyUser
        assert p1.age == 20
        assert p1.name == 'Jon'
        assert p1.birthday
    }
}

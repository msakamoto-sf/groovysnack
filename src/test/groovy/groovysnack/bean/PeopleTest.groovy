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
    void basic()
    {
        def p1 = new People(groovyUser:true, age:20, name:'Jon', birthday:new Date())
        assert p1.groovyUser
        assert p1.age == 20
        assert p1.name == 'Jon'
        assert p1.birthday
    }

    @Test
    void canonicalized()
    {
        // "@Canonical" generates "toString()"
        def p1 = new CanonicalizedPeople(age:20, groovyUser:true, name:'Bob')
        assert 'groovysnack.bean.CanonicalizedPeople(true, 20, Bob)' == p1.toString()

        // "@Canonical" generates "equals()" and default value constructors
        def p2 = new CanonicalizedPeople(age:10, groovyUser:true, name:'Jon')
        def p3 = new CanonicalizedPeople(age:10)
        assert !p3.groovyUser
        assert p3.name == null
        assert p2 != p3
        p3.groovyUser = true
        p3.name = 'Jon'
        assert p2 == p3

        // "@Canonical" generates "hashCode()"
        def peoples = [p1, p2, p3] as Set<CanonicalizedPeople>
        assert peoples.size() == 2
        assert peoples.name.join(',') == 'Bob,Jon'
    }

    @Test
    void immutabled()
    {
        def p1 = new ImmutablePeople(groovyUser:true, age:10, name:'Bob', friends:['Alice', 'Jon'])
        assert p1.groovyUser
        assert p1.age == 10
        assert p1.name == 'Bob'
        assert p1.friends == ['Alice', 'Jon']

        // "@Immutable" set bean properties immutable
        try
        {
            p1.name = 'BOBOB'
            assert false // Never reach this line
        } catch (ReadOnlyPropertyException e)
        {
            assert e.message == 'Cannot set readonly property: name for class: groovysnack.bean.ImmutablePeople'
        }
        try
        {
            p1.friends << 'Mary'
            assert false // Never reach this line
        } catch (UnsupportedOperationException e)
        {
        }

        // "@Immutable" generates "toString()"
        assert p1.toString() == 'groovysnack.bean.ImmutablePeople(true, 10, Bob, [Alice, Jon])'

        // "@Immutable" generates "equals()"
        def p2 = new ImmutablePeople(groovyUser:true, age:10, name:'Bob', friends:p1.friends)
        assert p1 == p2
    }
}

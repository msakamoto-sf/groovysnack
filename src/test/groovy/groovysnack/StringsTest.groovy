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
package groovysnack

import groovy.text.GStringTemplateEngine
import groovysnack.bean.CanonicalizedPeople

import org.testng.annotations.Test

/**
 * @see http://groovy.codehaus.org/Strings+and+GString
 * @see http://mrhaki.blogspot.jp/2009/08/groovy-goodness-string-strings-strings.html
 */
class StringsTest
{
    @Test
    void basicDeclaration()
    {
        def s1 = 'abc " def \\ ghi \' jkl'
        println s1
        def s2 = "abc \" def \\ ghi ' jkl"
        println s2
        assert s1 == s2

        def s3 = '''
'abc
"def
\\ghi
'''
        println s3
        def s4 = """
'abc
"def
\\ghi
"""
        println s4
        assert s3 == s4

        def s5 = '''\
'abc
"def
\\ghi'''
        println s5
        def s6 = """'abc
"def
\\ghi\
"""
        println s6
        assert s5 == s6
    }

    @Test
    void gstringDeclaration()
    {
        // single-quoted string is NOT GString
        def s1 = 'abc ${name} def'
        println s1

        def name = 'jon'
        def p1 = new CanonicalizedPeople(age:10, groovyUser:true, name:'Bob')
        def s2 = "abc ${p1.groovyUser}, ${p1.name} : $name"
        assert s2 == 'abc true, Bob : jon'

        // triple single-quoted multi-line string is NOT GString
        def s3 = '''
abc ${def} ghi
'''
        println s3

        def s4 = """\
abc
${p1.groovyUser}
${p1.age}\
"""
        def s5 = """\
abc
true
10\
"""
        assert s4 == s5
    }

    String delayGString(String name, int age, boolean bool)
    {
        return "name=$name, age=$age, bool=$bool"
    }

    //String delayGString2(GString gstr, String name, int age, boolean bool)
    //{
    //    return gstr.toString()
    //}

    @Test
    void delayedGString()
    {
        assert "name=abc, age=10, bool=false" == delayGString("abc", 10, false)

        // not work : "groovy.lang.MissingPropertyException: No such property: name for class: groovysnack.StringsTest" occur.
        //def gstr = "name=${-> name}, age=${-> age}, bool=${-> bool}"
        //assert "name=abc, age=10, bool=false" == delayGString2(gstr, "abc", 10, false)
    }

    @Test
    void GStringTemplateDemo()
    {
        def engine = new GStringTemplateEngine()
        def template = engine.createTemplate('name=$name, age=$age, bool=$bool')
        assert template.make([name: 'abc', age: 10, bool: false]).toString() == 'name=abc, age=10, bool=false'
    }
}

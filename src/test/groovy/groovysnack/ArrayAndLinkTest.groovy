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

import org.testng.annotations.Test

class ArrayAndLinkTest
{
    @Test
    void groovierArray()
    {
        def ar1 = [100, 200, 300]
        assert ar1.size() == 3
        assert ar1.contains(100)
        assert !ar1.contains(400)
        assert ar1.class.toString() == 'class java.util.ArrayList'
    }

    @Test
    void traditionalArray()
    {
        String[] ar1 = ['abc', 'def', 'ghi']
        assert ar1.size() == 3
        assert 'abcdefghi' == TraditionalArrayTool.joinStringArray(ar1)

        String[] ar2 = ['ABC', 'DEF', 'GHI'] as String[]
        assert 'ABCDEFGHI' == TraditionalArrayTool.joinStringArray(ar2)
    }
}

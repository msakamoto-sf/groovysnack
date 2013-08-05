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
 * EnumMap/EnumSet demos by test cases.
 *
 * @see http://www.atmarkit.co.jp/ait/articles/1103/03/news107.html
 * @author "Masahiko Sakamoto" <sakamoto.gsyc.3s@gmail.com>
 */
class EnumCollectionTest
{

    enum NumericEnums
    {
        ONE, TWO, THREE, FOUR, FIVE,
    }

    @Test
    public void enumMap()
    {
        Map<NumericEnums, String> numerics = new EnumMap<>(NumericEnums.class)
        numerics.put(NumericEnums.ONE, "one")
        numerics.put(NumericEnums.TWO, "two")
        numerics.put(NumericEnums.THREE, "three")
        numerics.put(NumericEnums.FOUR, "four")
        numerics.put(NumericEnums.FIVE, "five")

        assert numerics.get(NumericEnums.FOUR) == "four"
    }

    @Test
    public void enumSet()
    {
        Set<NumericEnums> s1 = EnumSet.of(NumericEnums.TWO, NumericEnums.THREE)
        assert s1.contains(NumericEnums.TWO)
        assert !s1.contains(NumericEnums.FOUR)

        Set<NumericEnums> s2 = EnumSet.noneOf(NumericEnums.class)
        assert s2.size() == 0

        Set<NumericEnums> s3 = EnumSet.range(NumericEnums.TWO,
                NumericEnums.FOUR);
        assert !s3.contains(NumericEnums.ONE)
        assert s3.contains(NumericEnums.TWO)
        assert s3.contains(NumericEnums.THREE)
        assert s3.contains(NumericEnums.FOUR)
        assert !s3.contains(NumericEnums.FIVE)
    }
}
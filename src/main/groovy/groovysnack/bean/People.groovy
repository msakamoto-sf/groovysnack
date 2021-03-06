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

import groovy.transform.*

class People
{
    Boolean groovyUser
    Integer age
    String name
    Date birthday
}

/**
 * @see http://mrhaki.blogspot.jp/2011/05/groovy-goodness-canonical-annotation-to.html
 */
@Canonical
class CanonicalizedPeople
{
    Boolean groovyUser
    Integer age
    String name
}

/**
 * @see http://mrhaki.blogspot.jp/2009/09/groovy-goodness-making-class-immutable.html
 */
@Immutable
class ImmutablePeople
{
    Boolean groovyUser
    Integer age
    String name
    Collection<String> friends
}


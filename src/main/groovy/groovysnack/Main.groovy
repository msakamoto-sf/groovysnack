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

import groovy.util.logging.*

@Slf4j
class Main {
    static void main(args) {
        println 'Hello, Groovy'
        log.trace('trace message')
        log.debug('debug message')
        log.info('info message')
        log.warn('warn message')
        log.error('error message')
    }
}


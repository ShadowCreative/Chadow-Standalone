/*
Copyright (c) 2018 ruskonert
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package io.github.shadowcreative.chadow.handler

/**
 * Handle can implement the class or code that you want to execute before
 * the object is initialized or the instance variable is created by the
 * Constructor. The plugin-based class that can manage this can be efficiently
 * managed by calling those functions. The Handler Parameter makes to pass in-
 * formation to the superclass or indirectly reference various values that can
 * be referenced.
 *
 * @since 0.1.0
 * @author ruskonert
 */
interface Handle
{
    /**
     * Executes when the implemented class is initialized or
     * invoked by a plugin-based class agent.
     *
     * @param handleInstance Any object that can be referenced
     * @return The value returned when the function executes successfully or
     *         separate return processing by code source implementation.
     */
    fun onInit(handleInstance : Any?) : Any?
}
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.rat.configuration;

import java.util.Map;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Readers {
    
    /*
     * --- JSON ---
     *  {
     *      families : {
     *          xxx: 'name',
     *          },
     *      licenses : [
     *      {
     *          family : 'xxx',
     *          notes: 'notes',
     *          spdx: 'spdx',
     *          fulltext: 'text',
     *          copyright: 'copyright',
     *          text = ['text','text2','text3'],
     *          }],
     *  }
     * 
     * --- RDF ---
     *
     * family:category [
     *   name='   '
     *   ] 
     *
     * license:id [
     *   family family:category
     *   notes=''
     *   spdx=''
     *   fulltext=''
     *   copyright=''
     *   text=''
     *   text=''
     *   text=''
     * ]
     *
     * --- XML ---
     * <config>
     * <family id='fxx' category='xxxxx'>
     *      name text
     * </family>
     * 
     * <license id="lxx' family='fxx' spdx='spdxtx'>
     *  <notes>notes text</notes>
     *  <fulltext>full text</fulltext>
     *  <copyright>copyright</copyright>
     *  <text>text</text>
     *  </license>
     *  </config>
     *  
     *  --- CONFIG ---
     * family.category.name=text
     * 
     * license.id.family=famid
     * license.id.notes=
     * license.id.fullText=
     * license.id.copyright=
     * license.id.text=
     * license.id.spdx=
     */

    private static Map<Format,Reader> readers;

    static {
        readers = new HashMap<>();
        readers.put( Format.XML, new XMLConfigurationReader());
    }

    public static Reader get(URL fileName) {
        return readers.get(Format.fromName(fileName.getFile()));
    }
    
    private Readers() {
        // do not instantiate
    }

    public static Reader get(File file) throws MalformedURLException {
        return get(file.toURI().toURL());
    }
    
    public static Reader get(String fileName) throws MalformedURLException {
        return get(new File(fileName));
    }
}

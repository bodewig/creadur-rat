/*
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 */
package org.apache.rat.analysis;

import org.apache.rat.configuration.builders.AllBuilder;
import org.apache.rat.configuration.builders.AnyBuilder;
import org.apache.rat.configuration.builders.CopyrightBuilder;
import org.apache.rat.configuration.builders.MatcherRefBuilder;
import org.apache.rat.configuration.builders.NotBuilder;
import org.apache.rat.configuration.builders.RegexBuilder;
import org.apache.rat.configuration.builders.SpdxBuilder;
import org.apache.rat.configuration.builders.TextBuilder;

/**
 * Matches text headers to known licenses.
 */
public interface IHeaderMatcher {

    /**
     * Get the identifier for this matcher.
     * 
     * @return the Identifier for this matcher.
     */
    String getId();

    /**
     * Resets this matches. Subsequent calls to {@link #match} will accumulate new
     * text.
     */
    void reset();

    /**
     * Matches the text accumulated to licenses. TODO probably a poor design choice
     * - hope to fix later
     * 
     * @param subject current document.
     * @param line next line of text, not null
     * 
     * @return whether the current line matched in the document.
     * 
     * @throws RatHeaderAnalysisException in case of internal RAT errors.
     */
    boolean matches(String line);
    
    @FunctionalInterface
    interface Builder {
        IHeaderMatcher build();

        static TextBuilder text() {
            return new TextBuilder();
        }

        static AnyBuilder any() {
            return new AnyBuilder();
        }

        static AllBuilder all() {
            return new AllBuilder();
        }

        static CopyrightBuilder copyright() {
            return new CopyrightBuilder();
        }

        static SpdxBuilder spdx() {
            return new SpdxBuilder();
        }

        static MatcherRefBuilder matcherRef() {
            return new MatcherRefBuilder();
        }

        static NotBuilder not() {
            return new NotBuilder();
        }

        static RegexBuilder regex() {
            return new RegexBuilder();
        }
    }
}

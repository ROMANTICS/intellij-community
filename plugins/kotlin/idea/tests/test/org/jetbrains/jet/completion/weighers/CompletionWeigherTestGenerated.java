/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.completion.weighers;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;
import java.util.regex.Pattern;
import org.jetbrains.jet.JetTestUtils;
import org.jetbrains.jet.test.InnerTestClasses;
import org.jetbrains.jet.test.TestMetadata;

import org.jetbrains.jet.completion.weighers.AbstractCompletionWeigherTest;

/** This class is generated by {@link org.jetbrains.jet.generators.tests.GenerateTests}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/completion/weighers")
public class CompletionWeigherTestGenerated extends AbstractCompletionWeigherTest {
    public void testAllFilesPresentInWeighers() throws Exception {
        JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("idea/testData/completion/weighers"), Pattern.compile("^([^\\.]+)\\.kt$"), true);
    }
    
    @TestMetadata("DeprecatedFun.kt")
    public void testDeprecatedFun() throws Exception {
        doTest("idea/testData/completion/weighers/DeprecatedFun.kt");
    }
    
    @TestMetadata("LocalFileBeforeImported.kt")
    public void testLocalFileBeforeImported() throws Exception {
        doTest("idea/testData/completion/weighers/LocalFileBeforeImported.kt");
    }
    
    @TestMetadata("LocalValuesAndParams.kt")
    public void testLocalValuesAndParams() throws Exception {
        doTest("idea/testData/completion/weighers/LocalValuesAndParams.kt");
    }
    
    @TestMetadata("LocalsBeforeKeywords.kt")
    public void testLocalsBeforeKeywords() throws Exception {
        doTest("idea/testData/completion/weighers/LocalsBeforeKeywords.kt");
    }
    
    @TestMetadata("LocalsPropertiesKeywords.kt")
    public void testLocalsPropertiesKeywords() throws Exception {
        doTest("idea/testData/completion/weighers/LocalsPropertiesKeywords.kt");
    }
    
    @TestMetadata("ParametersBeforeKeywords.kt")
    public void testParametersBeforeKeywords() throws Exception {
        doTest("idea/testData/completion/weighers/ParametersBeforeKeywords.kt");
    }
    
    @TestMetadata("PropertiesBeforeKeywords.kt")
    public void testPropertiesBeforeKeywords() throws Exception {
        doTest("idea/testData/completion/weighers/PropertiesBeforeKeywords.kt");
    }
    
    @TestMetadata("TemplatesAndKeywordsLast.kt")
    public void testTemplatesAndKeywordsLast() throws Exception {
        doTest("idea/testData/completion/weighers/TemplatesAndKeywordsLast.kt");
    }
    
}

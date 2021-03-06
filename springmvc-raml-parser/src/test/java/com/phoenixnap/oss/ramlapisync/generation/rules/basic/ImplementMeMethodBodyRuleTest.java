package com.phoenixnap.oss.ramlapisync.generation.rules.basic;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.phoenixnap.oss.ramlapisync.generation.CodeModelHelper;
import com.phoenixnap.oss.ramlapisync.generation.rules.AbstractRuleTestBase;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

/**
 * @author armin.weisser
 * @since 0.4.1
 */
public class ImplementMeMethodBodyRuleTest extends AbstractRuleTestBase {

    private ImplementMeMethodBodyRule rule = new ImplementMeMethodBodyRule();

    @Test
    public void applyMethodRule_shouldCreate_validMethodSignature_withBody() throws JClassAlreadyExistsException {

        JDefinedClass jClass = jCodeModel.rootPackage()._class("TestController");
        JMethod jMethod = jClass.method(JMod.PUBLIC, ResponseEntity.class, "getBase");
        jMethod = rule.apply(getEndpointMetadata(), CodeModelHelper.ext(jMethod, jClass.owner()));

        assertThat(jMethod.body().isEmpty(), is(false));
        assertThat(serializeModel(), containsString("return null; //TODO Autogenerated Method Stub. Implement me please."));
    }

}
